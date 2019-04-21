package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.shellucas.models.Student;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Scanner;

public class GetCommand implements Command {
    
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public int execute(PersonHandler ph, Environment env, Database db) {
        System.out.print(" Enter key: ");
        int key = scanner.nextInt();
        Student s = (Student) ph.get(key, env, db);
        
        if (s != null)
            System.out.println(s.toString());
        
        return 0;
    }
}
