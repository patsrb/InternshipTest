package main;

import institution.University;
import institution.interlink.Internship;
import person.Student;
import reader.json;
import utilities.util;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class Application {
	public static List<University> universities;
	public static List<Internship> internships;
	public static List<Student> students;

	public static Type StudObjType = new TypeToken<List<Student>>() {}.getType();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// Deserialize students data from file
		//students = (List<Student>) json.deserialize("students.json", StudObjType);
		students = null;

		if (students == null) {
			students = new ArrayList<Student>();
			students.add(new Student("Andrew Kostenko", util.random(18, 25), "CH.U.I.")); //, "Interlink"
			students.add(new Student("Julia Veselkina", util.random(18, 25), "CH.U.I.")); //, "DLink"
			students.add(new Student("Maria Perechrest", util.random(18, 25), "CH.U.I."));
			students.add(new Student("Jacob Jacobson", util.random(18, 25), "CH.U.I."));
			students.add(new Student("David Davidson", util.random(18, 25), "CH.U.I."));
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

		int average = util.average(sum, students.size());
		if (internships != null) {
			for (Student student : students) {
				if (student.Internship == null && student.Knowledge.Level > average) {
					int rand = util.random(0, internships.size() - 1);
					student.Internship = internships.get(rand).Name;
					internships.get(rand).addStudent(student);
				}
			}
		}

		// Print out data specifics
		System.out.println("Students: " + students.size() 
				+ "\nAverage knowledge: " + average 
				+ "\nUniversities: " + (universities != null ? universities.size() : 0) 
				+ "\nInternships: " + (internships != null ? internships.size() : 0));

		// Print out Universities/Internships and their respective students		
		if (universities != null) universities.forEach((university) -> university.print());
		if (internships != null) internships.forEach((internship) -> internship.print());
		
		// Serialize resulting data to file
		json.serialize(students, "students.json", StudObjType);
		return;
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

}
