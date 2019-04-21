package com.shellucas.models;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import static com.sleepycat.persist.model.Relationship.*;
import com.sleepycat.persist.model.SecondaryKey;

import java.io.Serializable;
import java.util.StringJoiner;

@Entity
public abstract class Person implements Serializable {
	
	private static int GLOBALID = 0;
	
	@PrimaryKey
	private int id;
	
	private String lastName;
	
	private String firstName;
	
	private int age;
	
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		GLOBALID++;
		this.id = GLOBALID;
	}
	
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
