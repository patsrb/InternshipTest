package main;

import institution.University;
import institution.interlink.Internship;
import models.DataModel;
import person.Student;
import person.consciousness.Knowledge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Application {
	public static List<University> universities;
	public static List<Internship> internships;
	
	public static List<Student> students;
	
	public static String ProgramFolder = System.getProperty("user.dir");
	
	public static Type UnivObjType = new TypeToken<List<University>>(){}.getType();
	public static Type InternObjType = new TypeToken<List<Internship>>(){}.getType();
	
	public static Type StudObjType = new TypeToken<List<Student>>(){}.getType();
	
    public static void main(String[] args) throws IOException {
       	/*universities = (List<University>) ReadFromFile("universities.json", UnivObjType);
       	internships = (List<Internship>) ReadFromFile("internships.json", InternObjType);
       	
       	if (universities == null)
       	{
       		universities = new ArrayList<University>();
       		
       		University university = new University("CH.U.I.");
       		university.addStudent(new Student("Andrew Kostenko"));
            university.addStudent(new Student("Julia Veselkina"));
            university.addStudent(new Student("Maria Perechrest"));
            
            universities.add(university);
            
            SaveToFile(universities, "universities.json", UnivObjType);
       	}
       	
       	if (internships == null)
       	{
       		internships = new ArrayList<Internship>();
       		
       		Internship internship = new Internship("Interlink");
       		internship.addStudent(new Student("Julia Veselkina"));
            internship.addStudent(new Student("Maria Perechrest"));
            
            internships.add(internship);
            
            SaveToFile(internships, "internships.json", InternObjType);
       	}*/
    	
    	students = new ArrayList<Student>();
    	students.add(new Student("Andrew Kostenko", RanRandInt(18, 25), "CH.U.I."));
        students.add(new Student("Julia Veselkina", RanRandInt(18, 25), "CH.U.I."));
        students.add(new Student("Maria Perechrest", RanRandInt(18, 25), "CH.U.I."));
        students.add(new Student("Jacob Jacobson", RanRandInt(18, 25), "CH.U.I."));
        students.add(new Student("David Davidson", RanRandInt(18, 25), "CH.U.I."));
        
    	return;
    }
    
    private static Object ReadFromFile(String filename, Type type) throws IOException
    {
    	try (BufferedReader br = new BufferedReader(new FileReader(ProgramFolder + "\\" + filename))) {
    		return new Gson().fromJson(br, type);
		}
    	catch (FileNotFoundException e) {
    		return null;
    	}
    }

    private static void SaveToFile(Object json, String filename, Type type) throws IOException
    {
    	try (Writer writer = new FileWriter(ProgramFolder + "\\" + filename)) {
    	    new GsonBuilder().create().toJson((List<Object>)json, type, writer);
    	}
    }
    
    public static int RanRandInt(double min, double max){
        return (int)((Math.random()*((max-min)+1))+min);
    }
}
