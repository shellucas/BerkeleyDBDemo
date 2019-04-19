package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.shellucas.models.Student;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Random;

public class CreateCommand implements Command {
    @Override
    public int execute(PersonHandler ph, Environment env, Database db)
    {
        System.out.println(" Enter first name: ");
        String first = System.console().readLine();
        System.out.println(" Enter last name:");
        String last = System.console().readLine();
        System.out.println(" Enter age: ");
        int age = Integer.parseInt(System.console().readLine());
        Student s = new Student(new Random().nextInt(), first, last, age);
        Student res = (Student) ph.insert(s, env, db);
        return (res == null) ? -1 : 0;
    }
}
