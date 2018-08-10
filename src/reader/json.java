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

public class json {
	private static String ProgramFolder = System.getProperty("user.dir");
	
	public static Object ReadFromFile(String filename, Type type) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(ProgramFolder + "\\" + filename))) {
			return new Gson().fromJson(br, type);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static void SaveToFile(Object json, String filename, Type type) throws IOException {
		try (Writer writer = new FileWriter(ProgramFolder + "\\" + filename)) {
			new GsonBuilder().create().toJson((List<Object>) json, type, writer);
		}
	}
}
