package kint2.token;

import static org.junit.Assert.*;

import org.junit.Test;

import kint2.token.Tokens;

public class TokensTest {
	
	private String tokenizeToString(String str) {
		Tokens tokens = new Tokens();
		tokens.tokenize(str);
		return tokens.toString();
	}
	
	@Test
	public void testTokenize() {				
		tokenizeToString("((3))");
		assertEquals("tokenise", "Tokens[5]: [Token<(>, Token<(>, Token<3>, Token<)>, Token<)>]", tokenizeToString("((3))"));
	}

}
