package com.epam.services;

/**
 * @author Evgeny Borisov
 */
public class CoronaDesinfector {


    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce(" надо выйти из помещения");
        policeman.getOutPeople();
        desinfect(room);
        announcer.announce(" Рискните вернуться, вроде короны не видно");


    }

    private void desinfect(Room room) {
        System.out.println("зачитывается молитва: 'корона изыди!' - молитва прочитана, вирус низвергнут в ад");
    }
}
