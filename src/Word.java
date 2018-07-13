import java.util.Arrays;

public class Word {

	private String word;
	private int value;
	private String[] zeroPoint = new String[] {"*"};
	private String[] onePoint = new String[] {"a","e","i","o","u","l","n","s","t","r"};
	private String[] twoPoint = new String[] {"d","g"};
	private String[] threePoint = new String[] {"b","c","m","p"};
	private String[] fourPoint = new String[] {"f","h","v","w","y"};
	private String[] fivePoint = new String[] {"k"};
	private String[] eightPoint = new String[] {"j","x"};
	private String[] tenPoint = new String[] {"q","z"};
	
	public Word(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	
	private void calculateValue() {
		/*(1 point)-A, E, I, O, U, L, N, S, T, R
		(2 points)-D, G
		(3 points)-B, C, M, P
		(4 points)-F, H, V, W, Y
		(5 points)-K
		(8 points)- J, X
		(10 points)-Q, Z*/
		
		// System.out.println(word);
		for(int i = 0; i < word.length(); i++) {
			// System.out.println(word.charAt(i));
			if(Arrays.asList(zeroPoint).contains(Character.toString(word.charAt(i))) || Character.toString(word.charAt((i+1)%word.length())).equals("/")) {
				value += 0;
				// System.out.println("No points");
			} else if(Arrays.asList(onePoint).contains(Character.toString(word.charAt(i)))) {
				value ++;
				// System.out.println("One points");
			} else if(Arrays.asList(twoPoint).contains(Character.toString(word.charAt(i)))) {
				value += 2;
				// System.out.println("Two points");
			} else if(Arrays.asList(threePoint).contains(Character.toString(word.charAt(i)))) {
				value += 3;
				// System.out.println("Three points");
			} else if(Arrays.asList(fourPoint).contains(Character.toString(word.charAt(i)))) {
				value += 4;
				// System.out.println("Four points");
			} else if(Arrays.asList(fivePoint).contains(Character.toString(word.charAt(i)))) {
				value += 5;
				// System.out.println("Five points");
			} else if(Arrays.asList(eightPoint).contains(Character.toString(word.charAt(i)))) {
				value += 8;
				// System.out.println("Eight points");
			} else if(Arrays.asList(tenPoint).contains(Character.toString(word.charAt(i)))){
				value += 10;
				// System.out.println("Ten points");
			}
		}
		
	}
	
	public int getValue() {
		if(value == 0) {
			calculateValue();
			return value;
		}
		return value;
	}
	
	
}
