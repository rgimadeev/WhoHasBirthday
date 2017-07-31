package ru.whohasbirthday;
import com.google.gson.Gson;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Calendar;
import java.util.Date;
import static java.lang.Math.abs;

/**
 * Created by rgimadeev on 26.07.2017.
 */
public class UserRepository {
    private static final String FILENAME = "C:\\Users\\rgimadeev\\IdeaProjects\\WhoHasBirthday\\file.json";

    public void appendToJson(User user) {
        Gson gson = new Gson();
        ConsoleApp app = new ConsoleApp();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String read = reader.readLine();
            Store st = null;
            if (read != null) {
                st = gson.fromJson(read, Store.class);
            } else {
                st = new Store();
            }
            st.getPerson().add(user);
            String str = gson.toJson(st);
            FileWriter fileWriter = new FileWriter(FILENAME);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(str);
            bufferWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData(String name, String surname, String patrname, String birthdate) {
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String read = reader.readLine();
            Store st = null;

            if (read != null) {
                st = gson.fromJson(read, Store.class);
            } else {
                st = new Store();
            }
            for (int i = 0; st.getPerson().size() > i; i++) {
                User gu = st.getPerson().get(i);
                if (gu.getName().equals(name) || gu.getSurname().equals(surname) ||
                        gu.getPatrname().equals(patrname) || gu.getBirthdate().equals(birthdate)) {
                    System.out.println(gu.getName() + " " + gu.getSurname() + " " + gu.getPatrname() + ". День рождения: " + gu.getBirthdate());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBirthDays(Date bdfrom, Date bdto)  {
        Gson gson = new Gson();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String read = reader.readLine();
            Store st = null;
            if (read != null) {
                st = gson.fromJson(read, Store.class);
            } else {
                st = new Store();
            }
            for (int i = 0; i < st.getPerson().size(); i++) {
                User gu = st.getPerson().get(i);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//задаю формат даты
                String gud = st.getPerson().get(i).getBirthdate();
                Date date = formatter.parse(gud);
                String bdf = String.valueOf(bdfrom);//преобразование в String для сравнение с null
                String bdt = String.valueOf(bdto);//преобразование в String для сравнение с null
                if(bdf.equals("null") && bdt.equals("null")){
                    System.out.println(gu.getName() + " " + gu.getSurname() + " " + gu.getPatrname() + ". День рождения: " + gu.getBirthdate());
                }
                else if ( date.after(bdfrom) && date.before(bdto)) {
                    System.out.println(gu.getName() + " " + gu.getSurname() + " " + gu.getPatrname() + ". День рождения: " + gu.getBirthdate());
                }
            }
        }catch(ParseException ex) {
            System.out.println("Необходимо указать все параметры: beginDate, endDate");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ne){
            System.out.println("Необходимо указать все параметры: beginDate, endDate");
        }


    }
    public void printClosestBirthDays(int daysCount) {
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String read = reader.readLine();
            Store st = null;
            if (read != null) {
                st = gson.fromJson(read, Store.class);
            } else {
                st = new Store();
            }
            for (int i = 0; i < st.getPerson().size(); i++) {
                User gu = st.getPerson().get(i);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//задаю формат даты
                String gud = st.getPerson().get(i).getBirthdate();
                Date dt = formatter.parse(gud);//перевод строки gud в Date
                Date dt2 = new Date();
                Date currentDate=new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");//задаю формат даты для текущей даты
                String cd=dateFormat.format(currentDate);//перевод текущей даты в строку
                Date curDate=formatter.parse(cd);// перевод текущей даты в формате String в формат Date( для сравнения с датами пользователя)
                Calendar cal = Calendar.getInstance();
                cal.setTime(dt);//установление даты в календаре для вычисление первой даты(для кол-во оставшихся дней до дня рождения)
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(dt2);//установление даты в календаре для вычисления второй даты(для кол-ва оставшихся дней до дня рождения)
                long diff = cal.getTimeInMillis() - cal2.getTimeInMillis();
                long days =  (long)(diff / (24 * 60 * 60 * 1000));//миллисекунды / (24ч * 60мин * 60сек * 1000мс)
                Calendar cal3 = Calendar.getInstance();//для определения конечной даты
                cal3.setTime(dt2);
                cal3.add(Calendar.DAY_OF_MONTH,daysCount);
                Date enddt = cal3.getTime();
                if (curDate.equals(dt) ||(  curDate.after(dt)==curDate.equals(dt) && dt.before(enddt)))
                {
                    System.out.println(gu.getName() + " " + gu.getSurname() + " " + gu.getPatrname() + ". День рождения: " + gu.getBirthdate());
                    if (days == 0) {
                        System.out.println(" (День рождения сегодня)");}
                        else {
                    System.out.println(" (До дня рождения осталось " + days + " дн.) ");
                        }
                }
            }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
   } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
