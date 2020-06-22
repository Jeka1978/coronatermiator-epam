package com.epam.services;

import javax.annotation.PostConstruct;

/**
 * @author Evgeny Borisov
 */
@Deprecated
public class PolicemanImpl implements Policeman {

    @InjectByType
    private Reccomendator reccomendator;

    @PostConstruct
    public void init() {
        System.out.println(reccomendator.getClass());
    }

    @Override
    public void getOutPeople() {
        System.out.println("всё вон, пиф паф!!!");
    }
}
