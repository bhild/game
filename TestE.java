package p01;

public class TestE extends EnemyTemplate{
	public TestE() {
		super();
		hp = 10;
		ac = 15;
		move = 30;
		attackMod = 5;
		toHitMod = 5;
		maxRange = 120;
		posT[0] = 49;
		posT[1] = 6;
		sides = 4;
		strongType = c.SLASHING;
		weakType = c.FORCE;
		num = 6;
	}

}