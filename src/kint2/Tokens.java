package kint2;

import java.util.ArrayList;

public class Tokens {
	private ArrayList<Token> tokens = new ArrayList<Token>();
	
	private final char   TABCODE = '\t';
	
	//  (   er   (  fd   dfsd )  fsdf   )    (  drff df  h  )  
	public void tokenize(StringBuilder input) {
		// On supprime les blancs en début et en fin d'input.
		trim(input);
		
		// On tokenize.
		int i = 0;
		while(input.length() > 0) {
			char c = input.charAt(i); 
			
			if  (c == ' ' || c == TABCODE) {
				input.deleteCharAt(i);
				continue;
			}
			else if (c == '(' || c == ')' ) {
				addToken("" + c);
				input.deleteCharAt(i);
			}
			else {
				int firstBlank = input.indexOf(" ") != -1 ? input.indexOf(" ") : input.length();
				String tokenString = input.substring(0, firstBlank);
				addToken(tokenString);
				input.delete(0, tokenString.length());
			}		
			
			System.out.println(this.toString());
		}
	}
	
	private void addToken(String str) {
		this.tokens.add(new Token(str));
	}
	
	private void trim(StringBuilder input) {
		String str = input.toString();
		input = new StringBuilder(str.trim());
	}
	
	public int size() {
		return this.tokens.size();
	}
	
	public String getTokenString(int i) {
		if (i >= this.size()) {
			throw new IndexOutOfBoundsException("Tokens");
		}

		return this.tokens.get(i).getString();
	}
	
	public String toString() {
		return "Tokens: " + this.tokens;
	}
}
