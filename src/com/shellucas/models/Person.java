package com.shellucas.Models;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

import java.io.Serializable;
import java.util.StringJoiner;

@Entity
public abstract class Person implements Serializable {
	
	@PrimaryKey
	private int id;
	
	private String firstName;
	private String lastName;
	private int age;

	public Person(int id, String firstName, String lastName, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
						.add("id=" + id)
						.add("firstName='" + firstName + "'")
						.add("lastName='" + lastName + "'")
						.add("age=" + age)
						.toString();
	}
}