package kint2;

public class Main {
	private static Input input = new Input();
	
	public static void main(String[] args) {
		//testStringBuilderReplace();
		
		boolean quittingtime = false;
		while (! quittingtime) {
			input.read();
			if (input.isQuit()) {
				quittingtime = true;
			}
			else if (input.isDefine()) {
				System.out.println("DEFINE time");
			}
		}
		input.showFarwellMessage();
	}
}
