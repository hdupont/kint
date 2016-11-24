package kint2.exp;

public class ExpList {

	private Exp exp;
	private ExpList next;

	public ExpList(Exp exp, ExpList expList) {
		this.exp = exp;
		this.next = expList;
	}
	
	@Override
	public String toString() {
		String str = "ExpList: ";
		ExpList p = this; 
		while (p != null) {
			str += "" + p.exp + (p.next != null ? ", " : "");
			p = p.next;
		}
		return str;
	}

	public static void main(String[] args) {
		ExpList l;
		
		l = new ExpList(new ValExp(3), null);
		l = new ExpList(new ValExp(12), l);
		l = new ExpList(new ValExp(4), l);
		
		System.out.println(l);
	}
	
}
