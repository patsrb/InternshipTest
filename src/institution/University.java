package institution;

import models.DataModel;

public class University extends DataModel {
	public University(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return "University: " + this.Name + ", Students: " + this.getStudents();
	}
}
