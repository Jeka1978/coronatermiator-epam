package com.epam.services;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private List<TypeConfigurator> typeConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        Set<Class<? extends ObjectConfigurator>> classes = context.getScanner().getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        
        Set<Class<? extends TypeConfigurator>> typeConfiguratorClasses = context.getScanner().getSubTypesOf(TypeConfigurator.class);
        for (Class<? extends TypeConfigurator> aClass : typeConfiguratorClasses) {
        	typeConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }


    public <T> T createObject(Class<T> type) throws Exception {

        T t = type.getDeclaredConstructor().newInstance();
        configure(t);

        invokeInit(type, t);
        
        t = configureType(type, t);

        return t;
    }

    private <T> void invokeInit(Class<T> type, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }


    private <T> void configure(T t) {
        configurators.forEach(configurator -> configurator.configure(t, context));
    }
    
    
    private <T> T configureType(Class<T> type, T t) {
    	T result = t;
    	for (TypeConfigurator configurator : typeConfigurators) {
    		result = configurator.configure(type, result, context);
		}
    	
    	return result;
    }
}
