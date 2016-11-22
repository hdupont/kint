package kint2;

import java.util.ArrayList;

public class Tokens {
	private ArrayList<String> tokens = new ArrayList<String>();
	private final char   TABCODE = '\t';
	
	//  (   er   (  fd   dfsd )  fsdf   )    (  drff df    )  
	public void tokenize(StringBuilder input) {
		// On supprime les blancs en début et en fin d'input.
		trim(input);
		
		int i = 0;
		while(input.length() > 0) {
			char c = input.charAt(i); 
			
			if  (c == ' ' || c == TABCODE) {
				input.deleteCharAt(i);
				continue;
			}
			else if (c == '(' || c == ')' ) {
				tokens.add("" + c);
				input.deleteCharAt(i);
			}
			else {
				int firstBlank = input.indexOf(" ") != -1 ? input.indexOf(" ") : input.length();
				String token = input.substring(0, firstBlank);
				tokens.add(token);
				input.delete(0, token.length());
			}		
			
			System.out.println(this.toString());
		}
	}
	
	private void trim(StringBuilder input) {
		String str = input.toString();
		input = new StringBuilder(str.trim());
	}
	
	private String join() {
		String join = "";
		for (String token : tokens) {
			join += "<" + token + "> ";
		}
		return join;
	}
	
	public String toString() {
		return join();
	}
}
