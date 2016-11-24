package kint2.exp;

import java.util.ArrayList;

public class ExpList {

	private ArrayList<Exp> exps = new ArrayList<Exp>();
	
	public void addExp(Exp e) {
		exps.add(e);
	}
	
	public String toString() {
		return "ExpList[" + this.exps.size() + "]: " + this.exps;
	}

}
