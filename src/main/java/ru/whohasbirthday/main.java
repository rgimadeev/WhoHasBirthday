package ru.whohasbirthday;

import java.io.IOException;
import org.apache.commons.cli.ParseException;


public class main {


    public static void main(String[] args) throws ParseException, java.text.ParseException, IOException {

        ConsoleApp app=new ConsoleApp();
        System.out.println("Введите команду:");
        app.readConsole();

    }
}




