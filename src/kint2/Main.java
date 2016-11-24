package kint2;

import kint2.exp.Exp;
import kint2.token.Tokens;

public class Main {
		
	public static void main(String[] args) {
		IO io = new IO();
		Tokens tokens = new Tokens();
		Env emptyEnv = new Env();
		
		boolean quittingtime = false;
		while (! quittingtime) {

			// On génère les tokens à partir de l'input.
			StringBuilder balancedString = io.read();
			tokens.tokenize(balancedString);
			
			// On fait ce qu'il faut en fonction des tokens.
			if (tokens.match(new String[]{"quit"})) {
				quittingtime = true;
			}
			else if (tokens.match(new String[]{"(", "define"})) {
				System.out.println("main - DEFINE");
			}
			else {
				Exp exp = Parser.parseExp(tokens);
				io.prValue(exp.evaluate(emptyEnv));
			}
		}
		io.showFarwellMessage();
	}
}
