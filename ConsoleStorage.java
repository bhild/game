package p01;

public class ConsoleStorage {
	private static String consoleText = "";
	public final int SQUARESIZE = 5;
	public final int SLASHING = 0;
	public final int PEIRCING = 1;
	public final int BLUNT = 2;
	public final int FIRE = 3;
	public final int ICE = 4;
	public final int LIGHTNING = 10;
	public final int FORCE = 5;
	public final int TYPE_1 = 7;
	public final int TYPE_2 = 8;

	public void setText(String in) {
		consoleText += in;
	}
	public String getText() {
		return consoleText;
	}
}