package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.shellucas.models.Student;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

public class GetCommand implements Command {

    @Override
    public int execute(PersonHandler ph, Environment env, Database db) {
        System.out.print(" Enter key: ");
        int key = Integer.parseInt(System.console().readLine());
        Student s = (Student) ph.get(key, env, db);
        System.out.println(s.toString());
        return 0;
    }
}
