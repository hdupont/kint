package kint2;

public class Main {
	private static Input input = new Input();
	
	public static void main(String[] args) {
		//testStringBuilderReplace();
		
		boolean quittingtime = false;
		while (! quittingtime) {
			input.read();
//			if (input.matches("quit")) {
//				quittingtime = true;
//			}
		}
		input.showFarwellMessage();
	}
	
	public static void testStringBuilderReplace() {
		StringBuilder sb = new StringBuilder("abcdef");
		System.out.println(sb);
		int i = sb.indexOf("c");
		System.out.println(i);
		sb.replace(i, i+1, "x");
		System.out.println(sb);
	}
}
