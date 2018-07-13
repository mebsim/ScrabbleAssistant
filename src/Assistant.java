import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assistant {

	static List<String> list = new ArrayList<String>();
	static List<Word> correctWords = new ArrayList<Word>();
	
	public static List<Word> mergeSort(List<Word> original) {
		// Catch if only one letter in array
		if(original.size() <= 1) {
			return original;
		}
		
		// Find the two smaller arrays
		int middlePoint = ((original.size())/2);
		List<Word> L = mergeSort(original.subList(0, middlePoint)); // Left half
		List<Word> R = mergeSort(original.subList(middlePoint,original.size())); // Right half
		
		// Prepare for the sorting
		int LCount = 0;
		int RCount = 0;
		List<Word> sorted = new ArrayList<Word>();
		
		// Sort
		for(int i = 0; i < original.size(); i++) {
			// Catch if you've inserted all the values in one array
			if(LCount >= L.size()) {
				for(int j = RCount; j < R.size(); j++) {
					sorted.add(R.get(j));
				}
				break;
			}
			if(RCount >= R.size()) {
				for(int j = LCount; j < L.size(); j++) {
					sorted.add(L.get(j));
				}
				break;
			}
			
			// Compare and insert depending on which is smaller
			if(L.get(LCount).getValue() == R.get(RCount).getValue()) {
				sorted.add(L.get(LCount));
				sorted.add(R.get(RCount));
				LCount++;
				RCount++;
				i++;
			} else if(L.get(LCount).getValue() < R.get(RCount).getValue()) {
				sorted.add(R.get(RCount));
				RCount++;
			} else {
				sorted.add(L.get(LCount));
				LCount++;
			}
		}
		
		// Return sorted array
		return sorted;
	}
	
	public static void setDictionaryToArrayList() {
		try (BufferedReader br = new BufferedReader(new FileReader("Words.txt"))) {
	    	    String line;
	    	    while ((line = br.readLine()) != null) {
		    	    	line.toLowerCase();
		    	    list.add(line);
	    	    }
		} catch (Exception e) {}
	}
	
    public static boolean checkForWord(List<String> a, String word) {
    		int middlePoint = ((a.size())/2);
		String ofMiddle = a.get(middlePoint).toLowerCase();
		boolean found = false;
		if(ofMiddle.toLowerCase().compareTo(word) == 0) {
			//System.out.println("Found " + ofMiddle);
			found = true;
		} else if(a.size() == 1) {
			//System.out.println("Can't find it");
		} else if(ofMiddle.compareTo(word) > 0) {
			found = checkForWord(a.subList(0, middlePoint),word);
		} else {
			found = checkForWord(a.subList(middlePoint, a.size()),word);
		} 
		return found;
    }	
	
    static String[] allAlphabet = new String[] {"a","b","c","d","e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    
	public static void genWords(List<String> lettersDone, List<String> lettersRemaining) {
		// Check words
		String toCheck = "";
		for(String s:lettersDone) {
			toCheck += s;
		}
		if(toCheck != "" & checkForWord(list, toCheck.replaceAll("/", ""))) {
			correctWords.add(new Word(toCheck));
		}
		
		List<String> ltD = new ArrayList<String>(lettersDone);
		List<String> ltR = new ArrayList<String>(lettersRemaining);
		
		// Find more words
		for(int i = 0; i < lettersRemaining.size(); i++) {
			
			if(ltR.get(i).equals("*")) {
				//System.out.println("Random letter!");
				ltR.remove(i);
				for(int j = 0; j < 26; j++) {
					ltD.add(allAlphabet[j] + "/");
					genWords(ltD, ltR);
					ltD.remove(ltD.size()-1);
				}
				ltR.add(i, "*");
			} else {
				ltD.add(ltR.get(i));
				ltR.remove(i);
				genWords(ltD, ltR);
				ltR.add(i, ltD.get(ltD.size()-1));
                ltD.remove(ltD.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		setDictionaryToArrayList();
		
		List<String> letters = new ArrayList<String>();
		List<String> blank = new ArrayList<String>();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Input characters");
		while(true) {
			String a = scan.nextLine().toLowerCase();
			if(!a.equals("")) {
				letters.add(a);
			} else {
				break;
			}
		}
		
		genWords(blank,letters);
		for(Word e:mergeSort(correctWords)) {
			System.out.println(e.getWord() + ": " + e.getValue());
		}
		
		scan.close();
	}
	
}
