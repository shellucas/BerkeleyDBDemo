package com.shellucas.commands;

public class CommandFactory {
    public static Command getCommand(int cmd) {
        switch (cmd) {
            case 1: return new CreateCommand();
            case 2: return new GetCommand();
            case 3: return new UpdateCommand();
            case 4: return new DeleteCommand();
            default: return new UnknownCommand();
        }
    }
    
    public static String getMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("Commands:\n");
        builder.append("1: Create\n");
        builder.append("2: Read\n");
        builder.append("3: Update\n");
        builder.append("4: Delete\n");
        return builder.toString();
    }
}
