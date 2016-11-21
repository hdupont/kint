package kint2;


import java.util.ArrayList;
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
	private ArrayList<String> tokens = new ArrayList<String>();
		
	private final String FIRST_PROMPT = "-> ";
	private final String SECOND_PROMPT = "> ";
	private final String FAREWELL_MESSAGE = "Farewell, my dear friend :)";
	private final String COMMENTCHAR = ";";
	private final char   TABCODE = '\t';
	
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
		tokenize();
	}
	
	
	//  (   er   (  fd   dfsd )  fsdf   )    (  drff df    )  
	private void tokenize() {
		// On supprime les blancs en début et en fin d'input.
		trim();
		
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
			
			System.out.println("tokens<" + tokens + ">");
		}
	}
	
	private void trim() {
		String str = input.toString();
		input = new StringBuilder(str.trim());
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
