package com.epam.services;

/**
 * 
 * @author Mykhaylo Kozyaryk
 */
public interface TypeConfigurator {

	<T> T configure(Class<T> type, T t, ApplicationContext context);
}
