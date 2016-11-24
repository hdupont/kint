package kint2.exp;

import kint2.Env;

public class VarExp implements Exp {

	private String variable;
	
	public VarExp(String variable) {
		this.variable = variable;
	}
	
	@Override
	public int evaluate(Env env) {
		return 0;
	}

	public String toString() {
		return "VarExp<" + variable + ">";
	}
}
