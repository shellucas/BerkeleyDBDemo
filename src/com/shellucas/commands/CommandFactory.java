package com.shellucas.commands;

public class CommandFactory {
    public static Command getCommand(int cmd) {
        switch (cmd) {
            case 1: return new GetCommand();
            case 2: return new CreateCommand();
            case 3: return new UpdateCommand();
            case 4: return new DeleteCommand();
            default: return new UnknownCommand();
        }
    }
}
