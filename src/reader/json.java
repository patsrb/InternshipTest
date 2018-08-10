package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import person.Student;

public class json {
	private static String ProgramFolder = System.getProperty("user.dir");
	
	public static Object deserializeList(String filename) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(ProgramFolder + "\\" + filename))) {
			return new Gson().fromJson(br, new TypeToken<List<Student>>() {}.getType());
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public static Object deserializeList(String filename, Type type) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(ProgramFolder + "\\" + filename))) {
			return new Gson().fromJson(br, type);
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void serializeList(Object json, String filename) throws IOException {
		try (Writer writer = new FileWriter(ProgramFolder + "\\" + filename)) {
			new GsonBuilder().create().toJson((List<Object>) json, new TypeToken<List<Student>>() {}.getType(), writer);
		}
	}

	@SuppressWarnings("unchecked")
	public static void serializeList(Object json, String filename, Type type) throws IOException {
		try (Writer writer = new FileWriter(ProgramFolder + "\\" + filename)) {
			new GsonBuilder().create().toJson((List<Object>) json, type, writer);
		}
	}
}
