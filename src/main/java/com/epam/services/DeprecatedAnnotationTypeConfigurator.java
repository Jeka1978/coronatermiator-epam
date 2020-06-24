package com.epam.services;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author Mykhaylo Kozyaryk
 */
public class DeprecatedAnnotationTypeConfigurator implements TypeConfigurator {

	@Override
	public <T> T configure(Class<T> type, T t, ApplicationContext context) {
		if (type.isAnnotationPresent(Deprecated.class)) {
			return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), type.getInterfaces(), new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("The class " + t.getClass() + " is deprecated. Don't use Deprecated classes!");
					return method.invoke(t, args);
				}
			});
		}

		return t;
	}

}
