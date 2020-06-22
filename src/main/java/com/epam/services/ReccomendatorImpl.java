package com.epam.services;

/**
 * @author Evgeny Borisov
 */
@Singleton
@Deprecated
public class ReccomendatorImpl implements Reccomendator {

    @InjectProperty("alcohol")
    private String drinkName;

    public ReccomendatorImpl() {
        System.out.println("reccomendator was created");
    }

    @Override
    public void recommend() {
        System.out.println("drink "+drinkName);
    }
}
