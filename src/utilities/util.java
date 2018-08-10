package utilities;

public class util {
	public static int Random(int min, int max) {
		if (min == max || min > max) {
			return min;
		}
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}
	
	public static int Average(int sum, int count) {
		return sum / count;
	}
}
