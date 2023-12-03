package com.plusdev.eduesy.db;

import com.plusdev.eduesy.model.*;
import com.plusdev.eduesy.util.security.PasswordManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {

    public static ArrayList<User> userTable = new ArrayList<>();
    public static ArrayList<Student> studentTable = new ArrayList<>();

    public static ArrayList<Teacher> teacherTable = new ArrayList<>();
    public static ArrayList<Program> programTable = new ArrayList<>();
    public static ArrayList<Intake> intakeTable = new ArrayList<>();
    public static ArrayList<Registration> registrationsTable = new ArrayList<>();

    static {
        userTable.add(
                new User("Gihan","Bandara","g@gmail.com",new PasswordManager().encode("12345"))

        );
        teacherTable.add(
                new Teacher("T-1","Nirosha","Colombo","0775257232")
        );

    }

}
