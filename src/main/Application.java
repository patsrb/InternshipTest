package main;

import institution.University;
import institution.interlink.Internship;
import person.Student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Application {
	public static List<University> universities;
	public static List<Internship> internships;
	public static List<Student> students;
	
	public static String ProgramFolder = System.getProperty("user.dir");
	public static Type StudObjType = new TypeToken<List<Student>>(){}.getType();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException  {
    	students = (List<Student>) ReadFromFile("students.json", StudObjType);
    	
    	if (students == null) {
        	students = new ArrayList<Student>();
        	students.add(new Student("Andrew Kostenko", RanRandInt(18, 25), "CH.U.I.", "Interlink"));
            students.add(new Student("Julia Veselkina", RanRandInt(18, 25), "CH.U.I.", "DLink"));
            students.add(new Student("Maria Perechrest", RanRandInt(18, 25), "CH.U.I."));
            students.add(new Student("Jacob Jacobson", RanRandInt(18, 25), "CH.U.I."));
            students.add(new Student("David Davidson", RanRandInt(18, 25), "CH.U.I."));
    	}
    	
        int sum = 0;
        for (Student stu : students) {
        	if (!CheckUniversity(stu.University)) {
        		universities.add(new University(stu.University));
        	}
        	
        	if (stu.Internship != null && !CheckInternship(stu.Internship)) {
        		internships.add(new Internship(stu.Internship));
        	}
        	
        	if (stu.Internship != null) {
        		AddToInternshipObject(stu);
        	}
        	
        	sum += stu.Knowledge.Level;
        }
        
        if (universities != null) {
        	for (Student student : students) {
        		if (student.University != null) {
        			for (University university : universities) {
        				if (university.Name.equals(student.University)) {
        					university.addStudent(student);
        				}
        			}
        		}
        	}
        }
        
        int average = CalculateAverage(sum, students.size());
        if (internships != null) {
            for (Student student : students) {
            	if (student.Internship == null && student.Knowledge.Level > average) {
            		int rand = RanRandInt(0, internships.size() - 1);
            		student.Internship = internships.get(rand).Name;
            		internships.get(rand).addStudent(student);
            	}
            }
        }
        
        System.out.println("Students: " + students.size() + "\nAverage knowledge: " + average + "\nUniversities: " + universities.size() + "\nInternships: " + internships.size());
        
        for (University university : universities) {
        	System.out.println("University: " + university.Name + ", Students: " + university.getStudents());
        }
        
        if (internships != null) {
		    for (Internship internship : internships) {
		    	System.out.println("Internship: " + internship.Name + ", Students: " + internship.getStudents());
		    }
        }
        
        SaveToFile(students, "students.json", StudObjType);
    	return;
    }
    
    private static int CalculateAverage(int sum, int studentCount) {
    	return sum / studentCount;
    }
    
    private static void AddToInternshipObject(Student stud) {
    	if (internships == null) {
    		internships = new ArrayList<Internship>();
    	}
    	
    	for (Internship item : internships) {
    		if (item.Name == stud.Internship) {
    			item.addStudent(stud);
    		}
    	}
    }
    
    private static boolean CheckUniversity(String str) {
    	if (universities == null) {
    		universities = new ArrayList<University>();
    	}
    	
    	for (University item : universities) {
    		if (item.Name.equals(str)) {
				return true;
    		}
    	}
    	return false;
    }
    
    private static boolean CheckInternship(String str) {
    	if (internships == null) {
    		internships = new ArrayList<Internship>();
    	}
    	
    	for (Internship item : internships) {
    		if (item.Name.equals(str)) {
    			return true;
    		}	
    	}
    	return false;
    }
    
    private static Object ReadFromFile(String filename, Type type) throws IOException {
    	try (BufferedReader br = new BufferedReader(new FileReader(ProgramFolder + "\\" + filename))) {
    		return new Gson().fromJson(br, type);
		}
    	catch (FileNotFoundException e) {
    		return null;
    	}
    }

    @SuppressWarnings("unchecked")
	private static void SaveToFile(Object json, String filename, Type type) throws IOException {
    	try (Writer writer = new FileWriter(ProgramFolder + "\\" + filename)) {
    	    new GsonBuilder().create().toJson((List<Object>)json, type, writer);
    	}
    }
    
    public static int RanRandInt(int min, int max) {
    	if (min == max || min > max) {
    		return min;
    	}
        return (int)((Math.random()*((max-min)+1))+min);
    }
}
