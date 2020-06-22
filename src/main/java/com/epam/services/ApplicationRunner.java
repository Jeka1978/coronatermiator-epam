package com.epam.services;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class ApplicationRunner {
    public static ApplicationContext run(String packageToscan, Map<Class, Class> ifcToImplClass) {
        ApplicationContext context = new ApplicationContext(packageToscan, ifcToImplClass);
        return context;
    }
}
