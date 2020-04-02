package p01;

public class Proficiencies {
	private boolean acProf = false;
	private boolean spellProf = false;
	private boolean rangedProf = false;
	private boolean meleeProf = false;
	private int profBonus = 2;
	public int acProf() {
		if(acProf)return profBonus;
		return 0;
	}
	public void setAcProf(boolean acProf) {
		this.acProf = acProf;
	}
	public int spellProf() {
		if(spellProf)return profBonus;
		return 0;
	}
	public void setSpellProf(boolean spellProf) {
		this.spellProf = spellProf;
	}
	public int rangedProf() {
		if(rangedProf)return profBonus;
		return 0;
	}
	public void setRangedProf(boolean rangedProf) {
		this.rangedProf = rangedProf;
	}
	public int meleeProf() {
		if(meleeProf)return profBonus;
		return 0;
	}
	public void setMeleeProf(boolean meleeProf) {
		this.meleeProf = meleeProf;
	}
	public void setProfBonus(int profBonus) {
		this.profBonus = profBonus;
	}
	
}
