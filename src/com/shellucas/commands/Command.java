package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

public interface Command {
    int execute(PersonHandler ph, Environment env, Database db);
}
