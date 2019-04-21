package com.shellucas.commands;

import com.shellucas.PersonHandler;
import com.shellucas.models.Student;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Scanner;

public class UpdateCommand implements Command {
  
  private Scanner scanner = new Scanner(System.in);
  
  @Override
  public int execute(PersonHandler ph, Environment env, Database db)
  {
    System.out.println("Enter key: ");
    int key = Integer.parseInt(scanner.nextLine());
    
    Student result = (Student) ph.get(key, env, db);
  
    if (result == null) {
      return 0;
    }
    
    Student copy = new Student(result.getId(), result.getFirstName(), result.getLastName(), result.getAge());
    
    System.out.println(result);
    
    int input = -1;
    while (input != 0) {
      System.out.println("What do you want to change?");
      System.out.println("1: First Name");
      System.out.println("2: Last Name");
      System.out.println("3: Age");
      System.out.println("0: Done");
      input = Integer.parseInt(scanner.nextLine());
      switch (input) {
        case 1:
          System.out.println("Enter the first name: ");
          result.setFirstName(scanner.nextLine());
          break;
        case 2:
          System.out.println("Enter the last name: ");
          result.setLastName(scanner.nextLine());
          break;
        case 3:
          System.out.println("Enter the age: ");
          result.setAge(Integer.parseInt(scanner.nextLine()));
          break;
      }
    }
    
    if (result.getFirstName().equals(copy.getFirstName()) && result.getLastName().equals(copy.getLastName()) && result.getAge() == copy.getAge()) {
      System.out.println("Nothing was changed!");
      return 0;
    }
    
    Student res = (Student) ph.put(result, env, db);
    
    if (res != null)
      System.out.println(res);
    return 0;
  }
}
