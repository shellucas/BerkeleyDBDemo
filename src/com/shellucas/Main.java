package com.shellucas;

import com.shellucas.models.Student;
import com.sleepycat.je.*;

public class Main {

	private static final String dbLocation = "/data/je";
	private static final String dbName = "DemoDatabase";

	public static void main(String[] args) {
		DemoCLI demo = new DemoCLI();
		demo.start();
//    example();
	}
	
	private static void example() {
    Environment env = DatabaseHandler.createENV(dbLocation);
    Database db = DatabaseHandler.createDB(env, dbName);
    
    PersonHandler personHandler = new PersonHandler();
    
    System.out.println("----------INSERT----------");
    Student s1 = new Student(0, "Shelby", "Hendrickx", 24);
    Student studentInserted = (Student) personHandler.insert(s1, env, db);
    
    if (studentInserted != null) {
      System.out.printf("Student inserted: \n%s\n", studentInserted);
    }
    
    System.out.println("----------GET----------");
    Student p1 = (Student) personHandler.get(s1.getId(), env, db);
    
    if (p1 != null) {
      System.out.println(p1);
    }
    
    System.out.println("----------PUT----------");
    Student s2 = new Student(1, "Lucas", "van der Laan", 19);
    Student studentUpdated = (Student) personHandler.put(s2, env, db);
    
    if (studentUpdated != null) {
      System.out.printf("Student updated: \n%s\n", studentUpdated);
    }
    
    System.out.println("----------PUT----------");
    s1.setAge(19);
    s1.setFirstName("Lucas");
    s1.setLastName("van der Laan");
    studentUpdated = (Student) personHandler.put(s1, env, db);
    
    if (studentUpdated != null) {
      System.out.printf("Student updated: \n%s\n", studentUpdated);
    }
    
    System.out.println("----------DELETE----------");
    boolean deleted =  personHandler.delete(s1.getId(), env, db);
    
    if (deleted) {
      System.out.println(s1);
    }
    
    System.out.println("----------GET----------");
    p1 = (Student) personHandler.get(s1.getId(), env, db);
    
    if (p1 != null) {
      System.out.println(p1);
    }
  }
}
