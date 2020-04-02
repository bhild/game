package p01;

public class WeaponList{
	private int type = -1;
	private int dmgRoll = -1;
	private String name = "";
	private Roll dmg;
	private Roll toHit;
	private int hitMod = 0;
	private Player p;
	private int range = 0;
	ConsoleStorage c = new ConsoleStorage();
	public WeaponList(Player p) {
		this.p = p;
	}
	public void setWeapon(int id) {
		if(id == 0)dagger();
		else if(id == 1)spear();
		else if(id == 2)sword();
		else if(id == 3)club();

	}
	public void dagger() {
		dmg = new Roll(4);
		name = "dagger";
		dmgRoll =  dmg.getRoll()+p.dexMod();
		range = c.SQUARESIZE;
		hitMod = p.dexMod();
		type=c.PEIRCING;
	}
	public void spear() {
		dmg = new Roll(6);
		name = "spear";
		dmgRoll = dmg.getRoll()+p.strMod();
		range = c.SQUARESIZE*2;
		hitMod = p.dexMod();
		type=c.PEIRCING;
	}
	public void sword() {
		dmg = new Roll(8);
		name = "sword";
		dmgRoll = dmg.getRoll()+p.strMod();
		range = c.SQUARESIZE;
		hitMod = p.dexMod();
		type=c.SLASHING;
	}
	public void club() {
		dmg = new Roll(6);
		name = "club";
		dmgRoll = dmg.getRoll()+p.strMod();
		range = c.SQUARESIZE;
		hitMod = p.dexMod();
		type=c.BLUNT;
	}
	public int getDmg() {
		return dmgRoll;
	}
	public int getRoll() {
		toHit = new Roll(20);
		return toHit.getRoll()+hitMod;
	}
	public String getIdName() {
		return name;
	}
	public int getRange() {
		return range;
	}
	public int getType() {
		return type;
	}
}