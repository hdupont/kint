package kint2.token;

import java.util.ArrayList;

public class Tokens {
	private ArrayList<Token> tokens = new ArrayList<Token>();
	
	// Utilisé par tokenize construire le token en cours (qui n'est pas une parenthèse)
	private boolean inStringToken = false;
	private String currentStringToken = "";
	
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
				addCurrentStringToken();
				
				input.deleteCharAt(i);
				continue;
			}
			else if (c == OPENING_PAREN || c == CLOSING_PAREN ) {
				addCurrentStringToken();
				
				addToken("" + c);
				input.deleteCharAt(i);
			}
			else {
				inStringToken = true;
				currentStringToken += c;
				input.deleteCharAt(i);
			}		
		}
	}
	
	public void tokenize(String string) {
		StringBuilder strb = new StringBuilder(string);
		this.tokenize(strb);
	}

	private void addCurrentStringToken() {
		if (currentStringToken != "") {
			addToken(currentStringToken);
			currentStringToken = "";
			inStringToken = false;
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
	
	public static void main(String[] args) {
		Tokens tokens = new Tokens();
		tokens.tokenize("((3))");
		System.out.println(tokens.toString());
	}
}
