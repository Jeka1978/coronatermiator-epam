package com.epam.services;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class Start {
    @SneakyThrows
    public static void main(String[] args) {
        HashMap<Class, Class> map = new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class));
        ApplicationContext context = ApplicationRunner.run("com.epam", map);
        context.getObject(CoronaDesinfector.class).start(new Room());
    }
}
