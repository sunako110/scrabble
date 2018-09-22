package scramble;

import java.util.Random;

public class test {
	public static void main(String[] args) {
		for(int i = 0; i<10; i++) {
			Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		System.out.println(c);
		}
		
	}
}
