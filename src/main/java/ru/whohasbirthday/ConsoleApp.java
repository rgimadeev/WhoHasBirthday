package ru.whohasbirthday;

/**
 * Created by rgimadeev on 26.07.2017.
 */

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class ConsoleApp {
      String method;
    public void readConsole() throws IOException, ParseException {
        Scanner scn = new Scanner(System.in);
        UserRepository ur =new UserRepository();
        method=scn.next();
        switch(method){
            case "writeToJson" :
                User u=new User(scn.next(),scn.next(),scn.next(),scn.next());
                ur.appendToJson(u);break;
            case "printData" :
                ur.printData(scn.next(),scn.next(),scn.next(),scn.next());break;
            case "printBirthDays":
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    String dtStr = scn.next();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
                    String dtStr2 = scn.next();
                    Date dt=null;
                    Date dt2=null;
                    if (!"null".equals(dtStr)) {
                         dt = sdf.parse(dtStr);
                    }
                    if(!"null".equals(dtStr2)) {
                        dt2 = sdf.parse(dtStr2);
                    }
                    ur.printBirthDays(dt,dt2);
                } catch (ParseException e) {
                    System.out.println("Необходимо указать все параметры: beginDate, endDate");
                }
                break;
            case "printClosestBirthDays":
                ur.printClosestBirthDays(scn.nextInt());
                break;
            default:
                System.out.println("Введите одну из команд:\n writeToJson\n printData\n printBirthDays \n printClosestBirthDays ");break;
        }

    }

}




