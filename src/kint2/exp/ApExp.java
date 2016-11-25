package kint2.exp;

import kint2.Env;

// Application, i.e. function call.
public class ApExp implements Exp {

	private String operator;
	private ExpList args;

	public ApExp(String operator, ExpList args) {
		// TODO Auto-generated constructor stub
		this.operator = operator;
		this.args = args;
	}

	@Override
	public int evaluate(Env env) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		return "ApExp<operator=" + this.operator + ", args=" + this.args + ">";
	}
}
