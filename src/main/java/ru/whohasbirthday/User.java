package ru.whohasbirthday;

import java.util.Date;

/**
 * Created by rgimadeev on 26.07.2017.
 */
public class User extends Date {
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatrname() {
        return patrname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    String name;
     String surname;
     String patrname;
     String birthdate;

    public User(String name, String surname, String patrname, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.patrname = patrname;
        this.birthdate =birthdate;

    }

}




