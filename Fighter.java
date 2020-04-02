package p01;

public class Fighter extends EnemyTemplate{
	public Fighter() {
		super();
		hp = 30;
		ac = 17;
		move = 30;
		attackMod = 5;
		toHitMod = 5;
		maxRange = c.SQUARESIZE;
		posT[0] = 5;
		posT[1] = 5;
		sides = 10;
		weakType = c.ICE;
		strongType = c.BLUNT;
		num = 3;
	}
}
