package kint2.exp;

import kint2.Env;
import kint2.token.Tokens;

public class ValExp implements Exp {
	private int num;
	
	public static boolean isIt(Tokens tokens) {
		return false;
	}
	
	public ValExp(int n) {
		this.num = n;
	}
	
	public int evaluate(Env env) {
		return this.num;
	}
	
	public String toString() {
		return "ValExp<" + num + ">";
	}
}
