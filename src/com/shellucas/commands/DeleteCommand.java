package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Random;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public int execute(PersonHandler ph, Environment env, Database db) {
        System.out.print(" Enter key: ");
        int key = scanner.nextInt();
        
        boolean deleted = ph.delete(key, env, db);
        
        if (deleted) {
            System.out.println("Entry has been deleted");
        }
        
        return 0;
    }
}
