package kint2.token;

public class Token {
	private String string;
	
	public Token(String str) {
		this.string = str;
	}
	
	public String getString() {
		return this.string;
	}
	
	public String toString() {
		return "Token<" + this.string + ">";
	}
}
