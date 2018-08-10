package utilities;

public class util {
	public static int random(int min, int max) {
		if (min == max || min > max) {
			return min;
		}
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}
	
	public static int average(int sum, int count) {
		return sum / count;
	}
}
