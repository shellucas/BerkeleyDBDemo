package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.shellucas.models.Student;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Random;
import java.util.Scanner;

public class CreateCommand implements Command {
  
  private Scanner scanner = new Scanner(System.in);
  
  @Override
  public int execute(PersonHandler ph, Environment env, Database db)
  {
    System.out.println(" Enter first name: ");
    String first = scanner.nextLine();
    System.out.println(" Enter last name:");
    String last = scanner.nextLine();
    System.out.println(" Enter age: ");
    int age = scanner.nextInt();
    Student s = new Student(first, last, age);
    Student res = (Student) ph.insert(s, env, db);
    
    if (res != null)
      System.out.println(res);
    return 0;
  }
}
