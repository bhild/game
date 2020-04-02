package p01;

public class SpellList {
	private int type = -1;
	private int dmgRoll = -1;
	private String name = "";
	private Roll dmg;
	private Roll toHit;
	private int hitMod = 0;
	private Player p;
	private int range = 0;
	ConsoleStorage c = new ConsoleStorage();
	public SpellList(Player p) {
		this.p = p;
	}
	public void setSpell(int id) {
		if(id == 0)missle();
		else if(id == 1)fire();
		else if(id == 2)ice();
		else if(id == 3)lightning();
	}
	public void missle() {
		dmg = new Roll(4);
		name = "missle";
		dmgRoll =  dmg.getRoll()+p.intMod();
		range = 120;
		hitMod = 10000000;
		type=c.FORCE;
	}
	public void fire() {
		dmg = new Roll(8);
		name = "fire";
		dmgRoll = dmg.getRoll()+p.intMod();
		range = 120;
		hitMod = p.dexMod();
		type=c.FIRE;
	}
	public void ice() {
		dmg = new Roll(8);
		name = "ice";
		dmgRoll = dmg.getRoll()+p.intMod();
		range = 120;
		hitMod = p.dexMod();
		type=c.ICE;
	}
	public void lightning() {
		dmg = new Roll(6);
		name = "lighning";
		dmgRoll = dmg.getRoll()+p.intMod();
		range = 120;
		hitMod = p.dexMod();
		type=c.ICE;
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