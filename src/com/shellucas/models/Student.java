package com.shellucas.models;

public class Student extends Person {
	
	public Student(String firstName, String lastName, int age) {
		super(firstName, lastName, age);
	}
	
	public Student(int id, String firstName, String lastName, int age) {
		super(id, firstName, lastName, age);
	}
}
