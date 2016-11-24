package kint2.token;

import java.util.ArrayList;

public class Tokens {
	private ArrayList<Token> tokens = new ArrayList<Token>();
	
	private final char   TABCODE = '\t';
	private final char   OPENING_PAREN = '(';
	private final char   CLOSING_PAREN = ')';
	
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
			else if (c == OPENING_PAREN || c == CLOSING_PAREN ) {
				addToken("" + c);
				input.deleteCharAt(i);
			}
			else {
				int firstBlank = input.indexOf(" ") != -1 ? input.indexOf(" ") : input.length();
				String tokenString = input.substring(0, firstBlank);
				if (tokenString.length() > 1 && tokenString.charAt(tokenString.length() - 1) == CLOSING_PAREN) {
					tokenString = tokenString.substring(0, tokenString.length() - 1);
				}
				addToken(tokenString);
				input.delete(0, tokenString.length());
			}		
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

	public Token readNextToken() {
		if (this.size() == 0) {
			throw new IndexOutOfBoundsException("No more tokens");
		}

		return this.tokens.remove(0);
	}
	
	public boolean match(String[] strings) {
		int length = strings.length;
		boolean match = true;
		for (int i = 0; i < length; i++) {
			if (! strings[i].equals(tokens.get(i).getString())) {
				match = false;
				break;
			}
		}
		return match;
	}
	
	public String toString() {
		return "Tokens[" + this.size() + "]: " + this.tokens;
	}
}
