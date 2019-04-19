package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

public class UpdateCommand implements Command {
    @Override
    public int execute(PersonHandler ph, Environment env, Database db) {


        return 0;
    }
}
