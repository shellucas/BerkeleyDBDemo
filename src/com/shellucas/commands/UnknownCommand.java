package com.shellucas.commands;

public class UnknownCommand implements Command {
    @Override
    public int execute() {
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
