package person;

import person.consciousness.Knowledge;

public class Student {
	public String Name;
	public int Age;
	public String University;
	public String Internship;
	public Knowledge Knowledge;
	
    public Student(String name, int age, String university, Knowledge knowledge) {
        Name = name;
        Age = age;
        University = university;
        Knowledge = knowledge;
    }
    
    public Student(String name, int age, String university) {
        this(name, age, university, new Knowledge());
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
    /*
    @Override
    public String toString() {
        return "[name=" + Name + ", age=" + Age +", university=" + University + ", internship=" + Internship + "]";
    }
    */
}