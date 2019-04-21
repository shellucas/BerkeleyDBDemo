package com.shellucas.commands;

public class CommandFactory {
    public static Command getCommand(int cmd) {
        switch (cmd) {
            case 0: System.exit(0);
            case 1: return new CreateCommand();
            case 2: return new GetCommand();
            case 3: return new UpdateCommand();
            case 4: return new DeleteCommand();
            default: return new UnknownCommand();
        }
    }
    
    public static String getMenu() {
        return "Commands:\n" +
                "0: Exit\n" +
                "1: Create\n" +
                "2: Read\n" +
                "3: Update\n" +
                "4: Delete";
    }
}
