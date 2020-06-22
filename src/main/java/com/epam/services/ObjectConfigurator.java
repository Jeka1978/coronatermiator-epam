package com.epam.services;

/**
 * @author Evgeny Borisov
 */
public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
