package models;

import java.util.ArrayList;
import java.util.List;

import person.Student;

public abstract class DataModel {
	public String Name;
	private List<Student> list;

    public DataModel(String name) {
    	Name = name;
    	list = new ArrayList<Student>();
    }
    
    private boolean containsStudent(Student s) {
    	for (Student student : list) {
    		if (student.Name == s.Name) return true;
    	}
    	return false;
    }

    public void setStudent(Student student) {
    	if (!containsStudent(student)) {
    		list.add(student);
    	} else {
    		System.out.println("Student with the name '" + student.Name + "' already exists!");
    	}
    }

    public void addStudent(Student student) {
    	list.add(student);
    }
    
    public void remStudent(Student student) {
    	list.remove(student);
    }

    // todo: replace iterative logic with yield return
    public List<String> getStudents() { 
    	List<String> tmp = new ArrayList<String>();
    	for (Student student : list) {
    		tmp.add(student.Name);
    	}
    	return tmp;
    }
}
