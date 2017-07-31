package ru.whohasbirthday;

import java.util.ArrayList;

/**
 * Created by rgimadeev on 27.07.2017.
 */
public class Store {
    ArrayList<User> person=new ArrayList<User>();

    public ArrayList<User> getPerson() {
        return person;
    }
    public void setPerson(ArrayList<User> person) {
        this.person = person;
    }
}
