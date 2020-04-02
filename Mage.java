package p01;

public class Mage extends EnemyTemplate{
	public Mage() {
		super();
		hp = 10;
		ac = 15;
		move = 30;
		attackMod = 5;
		toHitMod = 5;
		maxRange = 120;
		posT[0] = 6;
		posT[1] = 6;
		sides = 4;
		weakType = c.SLASHING;
		strongType = c.FORCE;
		num = 4;
	}

}
