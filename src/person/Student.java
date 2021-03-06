package person;

import person.consciousness.Knowledge;
import utilities.util;

public class Student {
	public String Name;
	public int Age;
	public String University;
	public String Internship;
	public Knowledge Knowledge;

	public Student(String name, int age, String university, Knowledge knowledge, String internship) {
		Name = name;
		Age = age;
		University = university;
		Knowledge = knowledge;
		Internship = internship;
	}

	public Student(String name, int age, String university, String internship) {
		this(name, age, university, new Knowledge(util.random(1, 10)), internship);
	}

	public Student(String name, int age, String university) {
		this(name, age, university, new Knowledge(util.random(1, 10)), null);
	}

	public String getName() {
		return Name;
	}

	public void setKnowledge(Knowledge knowledge) {
		Knowledge = knowledge;
	}

	public int getKnowledge() {
		return Knowledge.Level;
	}

	@Override
	public String toString() {
		return "[Name: " + Name + ", Age: " + Age + ", Knowledge: " + Knowledge.Level + "]";
	}
	
	public void print() {
		System.out.println(this.toString());
	}
}
