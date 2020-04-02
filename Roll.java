package p01;

import java.util.Random;

public class Roll 
{ 
	private int sides = 0;
	public Roll(int sides) {
		this.sides = sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public int getRoll() {
		Random out = new Random();
		return  out.nextInt(sides)+1;
	}
}