package com.epam.services;

import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Evgeny Borisov
 */
public class ApplicationContext {
    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Reflections scanner;
    private JavaConfig config;
    private ObjectFactory factory;

    public ApplicationContext(String packageToScan, Map<Class, Class> ifc2Impl) {
        scanner = new Reflections(packageToScan);
        config = new JavaConfig(scanner, ifc2Impl);
        factory = new ObjectFactory(this);
    }


    @SneakyThrows
    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }


        Class<T> implClass = resolveImpl(type);
        T t = factory.createObject(implClass);


        if(implClass.isAnnotationPresent(Singleton.class)){
            cache.put(type, t);
        }

        return t;




    }
    private <T> Class<T> resolveImpl(Class<T> type) {
        if (type.isInterface()) {
            type = (Class<T>) config.getImplClass(type);
        }
        return type;
    }
}





