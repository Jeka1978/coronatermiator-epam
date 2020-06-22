package com.epam.services;

/**
 * @author Evgeny Borisov
 */
public class ConsoleAnnouncer implements Announcer {

    @InjectByType
    private Reccomendator reccomendator;

    @Override
    public void announce(String message) {
        System.out.println(message);
        reccomendator.recommend();
    }
}
