package com.shellucas;

import com.shellucas.commands.Command;
import com.shellucas.commands.CommandFactory;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Scanner;

public class DemoCLI {
  
  private static final String dbLocation = "/data/je";
  private static final String dbName = "DemoDatabase";
  private Scanner scanner;
  
  private PersonHandler personHandler;
  private Environment env;
  private Database db;
  
  DemoCLI() {
    initEnvironment();
    personHandler = new PersonHandler();
    scanner = new Scanner(System.in);
  }
  
  private void initEnvironment() {
    env = DatabaseHandler.createENV(dbLocation);
    db = DatabaseHandler.createDB(env, dbName);
  }
  
  public void start() {
    System.out.println("%%%%%%%%%%%% BerkleyDB DEMO %%%%%%%%%%%%");
    boolean isRunning = true;
    while (isRunning) {
      System.out.println(CommandFactory.getMenu());
      Command cmd = CommandFactory.getCommand(read());
      int result = cmd.execute(personHandler, env, db);
      if (result == -1) isRunning = false;
    }
  }
  
  private int read() {
    System.out.print("> ");
    return scanner.nextInt();
  }
  
}
