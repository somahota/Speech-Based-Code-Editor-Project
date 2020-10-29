import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextToCommands {
	
	
	// Stores the words the user has said, oldest at front of list
	List<String> words;
	
	// Store key words elsewhere
	Map<String, String> keywords = new HashMap<String, String>();
	
	// int waitTime = ?;    
	// If words has first part of a command/keyword, how long wait until 
	// ex: words = (declare)
	
	
	// Note, probably want a mode where all words are joined capital
	// ex: "ArrayList", "String"
	
	
	public TextToCommands() {
		words = new ArrayList<String>();
		initializeKeyWords();
	}
	
	/**
	 * Initializes key words to look for.
	 * If detected, these words will be translated into their
	 * corresponding symbols and added where the user's cursor is.
	 */
	private void initializeKeyWords() {
		keywords.put("space", " ");
		keywords.put("open parenthesis", "(");
		keywords.put("close parenthesis", ")");
		keywords.put("plus", "+");
		keywords.put("minus", "-");
		keywords.put("divide", "/");
		keywords.put("multiply", "*");
		keywords.put("mod", "%");
		keywords.put("colon", ":");
		keywords.put("semicolon", ";");
		keywords.put("tab", "	");
		keywords.put("quote", "'");
		keywords.put("double", "\"");
		keywords.put("comment", "//");
		keywords.put("?", "<");
		keywords.put("?", ">");
		
	}
	
	public void addWord(String w) {
		words.add(w);
	}
	
	// TODO: might want something that makes all capitol
	// ex: MAX_SIZE
	
	/**
	 * 
	 * @param s, in the form "this, is, a, var"
	 * @return	the input list as a var name, ex: "thisIsAVar"
	 */
	private String getVariableString(List<String> s) {
		if (s.size() == 0) {
			return "";
		}
		
		String result = "";
		
		result = result.concat(s.get(0).toLowerCase());
		for (int i = 1; i < s.size(); i++) {
			String current = s.get(i).toLowerCase();
			current  = current.substring(0, 1).toUpperCase() + current.substring(1, current.length());
			result = result.concat(current);
		}
		
		return result;
	}
	
	// TODO: What to do about close matches? misinterpretations?
	
	// Process the current words into commands
	// TODO: When to call  this?
	private void process() {
		// TODO: upper/lower casing
		
		for (int i = 0; i < words.size(); i++) {
			
			String command = "placeholder'";
			switch (command) {
				case "up" :
					// Move cursor up one line
					return;
				case "down":
					// Move cursor down one line
					return;
				case "end line":
					// Move cursor to end of line
					return;
				case "start line":
					// Move cursor to start of line
					return;
				case "end file":
					// Move Cursor to end of file
					return;
				case "start file":
					// Move cursor to start of file
					return;
				case "right":
					// Move cursor one word right
					return;
				case "left":
					// Move cursor one word left
					return;
				case "declare var":
					// Concatenate everything until "end declare var"
					// probably should just be var, because you would use this
					// when ever referencing this var not just declaring
					return;
				default:
					// Check if it is a keyword
					if (keywords.containsKey(command)) {
						// writeOut(keywords.get(command))
					} else {
						// Just write out the word as is if nothing after
						// writeOut(command) 
					}
			}
		}
	}
	
	
	
	

}
