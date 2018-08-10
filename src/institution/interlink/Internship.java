package institution.interlink;

import models.DataModel;

public class Internship extends DataModel {
	public Internship(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return "Internship: " + this.Name + ", Students: " + this.getStudents();
	}
}
