package kint2;


import java.util.Scanner;

import kint2.token.Tokens;


public class IO {
	private StringBuilder input = new StringBuilder("");
	private Tokens tokens;
		
	private final String FIRST_PROMPT = "-> ";
	private final String SECOND_PROMPT = "> ";
	
	private final String FAREWELL_MESSAGE = "Farewell, my dear friend :)";
	
	private final String COMMENTCHAR = ";";
	
	private final String OPEN_PAREN = "(";
	private final String DEFINE = "define";
	
	
	public Scanner scanner = new Scanner(System.in);
	
	public StringBuilder read() {
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
		
		return input;
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
	
	public boolean isDefine() {
		return this.tokens.getTokenString(0).equals(OPEN_PAREN)
				&& this.tokens.getTokenString(1).equals(DEFINE);
	}
	
	public void showFarwellMessage() {
		System.out.print(FAREWELL_MESSAGE);
	}
	
	public String toString() {
		return "Input:" + this.input.toString();
	}
	
	public void prValue(int number) {
		System.out.println(number);
	}
}
