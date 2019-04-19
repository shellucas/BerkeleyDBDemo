package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

public class UnknownCommand implements Command {
    @Override
    public int execute(PersonHandler ph, Environment env, Database db) {
        System.out.println("Command not found");
        printMenu();
        return 0;
    }

    private void printMenu() {
        System.out.println("Menu:\n" +
                " [1] Get student" +
                " [2] Create new student\n" +
                " [3] Update student\n" +
                " [4] Delete student");
    }
}
