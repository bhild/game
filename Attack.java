package p01;

public class Attack {
	ConsoleStorage c = new ConsoleStorage();
	public int dmgP(Player p,EnemyTemplate e,boolean isSpell,WeaponList w, SpellList s) {
		int maxRange = 0;
		if(isSpell) {
			c.setText("\nYou used the " + s.getIdName() + " spell");
			maxRange = s.getRange();
		}
		else {
			c.setText("\nYou used a " + w.getIdName());
			maxRange = w.getRange();
		}
		c.setText(" dist = " + getDist(p.getX(), e.getX(), p.getY(), e.getY())+" max range = " + maxRange);
		if(maxRange>=getDist(p.getX(), e.getX(), p.getY(), e.getY())) {
			int roll = 	0;
			if(isSpell) {
				roll = s.getRoll();
			}else {
				roll = w.getRoll();
			}
			if(roll>=1000000) {
				c.setText("\nguaranteed");
			}else {
				c.setText("\n"+roll+"");
			}
			if(roll >= e.getAc()) {
				c.setText(" - hit");
				if(isSpell) {
					int dmg = s.getDmg();
					c.setText("\ndmg roll - "+dmg);
					return dmg;
				}else {
					int dmg = w.getDmg();
					c.setText("\ndmg roll - "+dmg);
					return dmg;
				}
			}
			c.setText(" - miss");
			return 0;
		}
		c.setText("\nTarget out of Range");
		return 0;
	}
	public int eAttack(EnemyTemplate e, Player p) {
		int maxRange = e.getMaxRange();
		c.setText("\ndist = " + getDist(p.getX(), e.getX(), p.getY(), e.getY())+" max range = " + maxRange);
		if(maxRange>=getDist(p.getX(), e.getX(), p.getY(), e.getY())) {
			Roll roll = new Roll(20);
			int a = roll.getRoll()+e.attackDie();
			c.setText("\nEnemy Rolled - " + a);
			if(p.getAc()<=a) {
				roll.setSides(e.getSides());
				a = roll.getRoll()+e.attackDmg();
				c.setText("\nYou got hit\nYou took " + a + " damage");
				return a;
			}
			c.setText("\nEnemy missed");
			return 0;
		}
		c.setText("\nEnemy out of range");
		return 0;
	}
	public double getDist(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow((x1-x2)*c.SQUARESIZE, 2)+Math.pow((y1-y2)*c.SQUARESIZE, 2));
	}
}
