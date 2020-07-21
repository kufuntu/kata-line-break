package textumbruch;

import java.util.ArrayList;
import java.util.List;

public class Textumbruch {
	

	public static void main(String[] args) {
		String inputText = "Es blaut die Nacht,\n" + 
				"die Sternlein blinken,\n" + 
				"Schneeflöcklein leis hernieder sinken.";
		
		System.out.println(formatText(inputText, 14));
	}

	private static String formatText(String text, int maxRowLength) {
		// is a regex like [^\s]{1,maxRowLength} clean code?
		
		String[] words = text.split("\\s");
		List<String> splitWords = splitLongWords(words, maxRowLength);

		StringBuilder sb = new StringBuilder();
		String line = splitWords.remove(0);
	
		while (!splitWords.isEmpty()) {
			String word = splitWords.remove(0);
			int newLineLength = line.length() + word.length() + 1;
			
			if (newLineLength > maxRowLength) {
				sb.append(line);
				sb.append('\n');
				line = word;
			} else {
				line += " " + word;
			}
		}
		
		sb.append(line);
		
		return sb.toString();
	}
	
	private static List<String> splitLongWords(String[] words, int maxRowLength) {
		List<String> splitWords = new ArrayList<>();
		
		for (String word : words) {
			while (word.length() > maxRowLength) {
				String part = word.substring(0, maxRowLength);
				splitWords.add(part);
				word = word.substring(maxRowLength);
			}
			splitWords.add(word);
		}
		
		return splitWords;
	}
	
}
