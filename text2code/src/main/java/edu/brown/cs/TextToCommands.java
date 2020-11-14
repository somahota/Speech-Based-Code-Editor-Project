package edu.brown.cs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class TextToCommands {


	// Store key words
	Map<String, String> keywords = new HashMap<String, String>();
	Set<String> commands = new HashSet<String>();
	int maxCommandLength;


	public TextToCommands() {
		initializeKeyWords();
		initializeCommands();
	}

	/**
	 * Initializes key words to look for.
	 * If detected, these words will be translated into their
	 * corresponding symbols and added where the user's cursor is.
	 * (Additional things can be done here)
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
		keywords.put("less than ", "<");
		keywords.put("greater than", ">");
	}

  /**
	  Initialize a list of commands
		(can be updated/changed later)
	*/
	private void initializeCommands() {
		// TODO: add more, just testing with a few for now
		commands.add("up");
		commands.add("down");
		commands.add("next line");

		int maxLength = -1;
		for (String s : commands) {
			int size = s.split(" ").length;
			if (size > maxLength) {
				maxLength = size;
			}
		}
		this.maxCommandLength = maxLength;
	}


	/**
	 *
	 * @param s, in the form "this", "is", "a", "var"
	 * @return	the input list in form "thisIsAVar"
	 */
	public String getVariableString(List<String> s) {
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


	/**
	 * @param s, in the form "this", "is", "a", "var"
	 * @return the  input list in the form "THIS_IS_A_VAR"
	 */
	public String getAllCapsString(List<String> s) {

		String result = "";
		for (int i = 0; i < s.size(); i++) {
			result = result.concat(s.get(i).toUpperCase());
			if (i != s.size() - 1) {
				result = result.concat("_");
			}
		}
		return result;
	}


	/**
	* @param s: input list in form "this", "is", "a", "var"
	* @return: the input in the form "ThisIsAVar"
	*/
	public String getNormalCase(List<String> s) {
		String result = "";
		for (int i = 0; i < s.size(); i++) {
			String current = s.get(i).toLowerCase();
			current  = current.substring(0, 1).toUpperCase() + current.substring(1, current.length());
			result = result.concat(current);
		}

		return result;
	}

  /**
	 Checks if the beginning of s matches to a command,
	 if there is a match, returns a list of the strings in this command
	 if not, null
	*/
	public List<String> matchCommand(List<String> s) {
		for (int i = this.maxCommandLength; i > 1; i--) {
			List<String> current = new ArrayList<String>();
			String currString = "";
			int upperBound = Integer.min(i, s.size());
			for (int j = 0; j < i; j++) {
				current.add(s.get(j));
				currString += s.get(j);
				if (j != i - 1) {
					currString += " ";
				}
			}
			System.out.println("||||||||||||" + currString);
			// Check for match
			if (this.commands.contains(currString)) {
				return current;
			}
		}
		return null; // no match
	}


  // Very simple processor, just writes all the words to the screen
	// no commands
	public void simpleProcess(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
			// TODO: Plugin.writeout(words.get(i))
			System.out.println(words.get(i));
		}
	}

	public String listToString(List<String> s) {
		String res = "";
		for (int i = 0; i < s.size(); i++) {
			res += s.get(i);
			if (i != s.size() - 1) {
				res += " ";
			}
		}
		return res;
	}


	// Process the current words into commands
	// Called at end of sentence
	private void process(List<String> words) {

		while (words.size() > 0) {
			// iterate over the words, matching for commands
			List<String> command = matchCommand(words);

			// Remove these words
			for (int i = 0; i < command.size(); i++) {
				words.remove(0);
			}

			String current  = (command != null) ? listToString(command) : words.get(0);
			// Remove first word if no command was found
			if (command == null) {
				words.remove(0);
			}

			switch (current) {
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
					// Concatenation methods
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
