package person.consciousness;

import main.Application;

public class Knowledge {
	public int Level = 0;
	
	public Knowledge() {
		Level = Application.RanRandInt(1, 10);
	}
	
    public Knowledge(int level) {
        Level = level;
    }
}
