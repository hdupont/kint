package kint2;

import kint2.exp.ApExp;
import kint2.exp.Exp;
import kint2.exp.ExpList;
import kint2.exp.ValExp;
import kint2.exp.VarExp;
import kint2.token.Tokens;

// <input>      ::= <expression> | <fundef>
// <fundef>     ::= "(" "define" <name> <arglist> <expression> ")"
// <arglist>    ::= "(" <variable>* ")"
// <expression> ::= <value>
//                | <variable>
//                | "(" "if" <expression> <expression> <expression> ")" 
//                | "(" "while" <expression> <expression> ")"	
//                | "(" "set" <variable> <expression> ")"
//                | "(" "begin" <expression>+ ")"
//                | "(" "optr" <expression>* ")"
// <optr>       ::= "function" | <value-op>
// <value>      ::= <integer>
// <value-op>   ::= "+" | "-" | "*" | "/" | "=" | "<" | ">" | "print"
// <function>   ::= <name>
// <variable>   ::= <name>
// <integer>    ::= Une séquence de chiffres pouvant être précédé d'un signe moins.
// <name>       ::= Une séquence de caractères.
public class Parser {
	
	public static Exp parseExp(Tokens tokens) {
		System.out.println("parseExp - " + tokens);
		Exp exp = null;
		// APEXP
		if (tokens.match(new String[]{"("})) {
			tokens.readNextToken();
			String name = parseName(tokens);
			ExpList expList = parseEL(tokens);
			exp = new ApExp(name, expList);
		}
		else if (isNumber(tokens)) {
			exp = new ValExp(parseVal(tokens)); // ValExp
		}
		else {
			String name = parseName(tokens);
			exp = new VarExp(name);
		}
		System.out.println(exp);
		return exp;
	}
	
	private static Integer parseVal(Tokens tokens) {
		System.out.println("parseVal - " + tokens);
		String str = tokens.readNextToken().getString();
		return Integer.parseInt(str);
	}
	
	private static String parseName(Tokens tokens) {
		System.out.println("parseName - " + tokens);
		return tokens.readNextToken().getString();
	}
	
	// (set (y 1 2) x (z 1 2 3) )
	// (set x (y 1 2) (z 1 2 3))
	private static ExpList parseEL(Tokens tokens) {
		if (tokens.match(new String[]{")"})) {
			tokens.readNextToken();
			return null;
		}
		else {
			Exp exp = parseExp(tokens);
			ExpList el = parseEL(tokens);
			return new ExpList(exp, el);
		}
	}
	
	private static boolean isNumber(Tokens tokens) {
		boolean res = false;
		
		if (tokens.size() > 0) {
			res = false;
		}
		
		String token = tokens.getTokenString(0);
				
		try {
			Integer.parseInt(token);
			res = true; // Pas d'exception donc c'est un entier.
		}
		catch (NumberFormatException nfe) {
			// Ce n'est pas un nombre, on va simplement retourner false.
		}
		
		
		return res;
	}
}
