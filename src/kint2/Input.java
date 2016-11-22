package kint2;


import java.util.Scanner;

// <fundef>     ::= "(" "define" <name> <arglist> <expression> ")"
// <arglist>    ::= "(" name* ")"
// <expression> ::= <value>
//                | <name>
//                | "(" "if" <expression> <expression> <expression> ")" 
//                | "(" "while" <expression> <expression> ")"	
//                | "(" "set" <name> <expression> ")"
//                | "begin" <expression>+ ")"
//                | "optr" <expression>* ")"
// <name>       ::= "une séquence de caractère"
public class Input {
	private StringBuilder input = new StringBuilder("");
	
		
	private final String FIRST_PROMPT = "-> ";
	private final String SECOND_PROMPT = "> ";
	private final String FAREWELL_MESSAGE = "Farewell, my dear friend :)";
	private final String COMMENTCHAR = ";";
	
	
	public Scanner scanner = new Scanner(System.in);

	public void DEGUB() {
		System.out.println("DEBUG<" + this + ">");
	}
	
	public void read() {
		// Tant que les parenthèses ne sont pas bien balancées.
		//     On lit une ligne.
		// 	   On en supprime les commentaires.
		String prompt = FIRST_PROMPT;
		boolean balanced = false;
		while (! balanced) {
			System.out.print(prompt);
			input.append(scanner.nextLine());
			removeComments();
			balanced = checkParens();
			prompt = SECOND_PROMPT;
		}
		
		DEGUB();
		
		// Une fois les parenthèses balancées, on tokenize.
		Tokens tokens = new Tokens();
		tokens.tokenize(input);
	}
		
	private void removeComments() {
		int commentsStart = this.input.indexOf(COMMENTCHAR);
		if (commentsStart > 0) {
			input.delete(commentsStart, input.length());
		}
	}
	
	private boolean checkParens() {
		int parentcnt = 0;
		int index = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(index) == '(') {
				parentcnt++;
			}
			if (input.charAt(index) == ')') {
				parentcnt--;
			}
			index++;
		}
		return parentcnt == 0;
	}
	
	public void showFarwellMessage() {
		System.out.print(FAREWELL_MESSAGE);
	}
	
	public String toString() {
		return this.input.toString();
	}
}
