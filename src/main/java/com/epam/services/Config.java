package com.epam.services;

/**
 * @author Evgeny Borisov
 */
public interface Config {
    <T> Class<? extends T>  getImplClass(Class<T> type);
}
