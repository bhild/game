package p01;



public class Player {
	//private int exp = 0;
	//private int lvl = 1;
	private int hp = 10;
	private int ac = 10;
	private int move = 30;
	private int dex = 10;
	private int str = 10;
	private int Int = 10;
	private int wis = 10;
	private int con = 10;
	public int oddModW = 0;
	public int oddModS = 0;
	private int[] posT = {0,0};
	public Player(){}
	public int getAc() {
		return ac;
	}
	public int getMove() {
		return move;
	}
	public int isDex(boolean isDex) {
		if(isDex) return 0;
		return 1;
	}
	public void takeDamage(int amount) {
		 hp = getHp() - amount;
	}
	public void heal(int amount) {
		 hp = getHp() + amount;
	}
	public void dexC(int amount) {
		 dex += amount;
	}
	public void strC(int amount) {
		 str += amount;
	}
	public void conC(int amount) {
		 con += amount;
	}
	public void intC(int amount) {
		 Int += amount;
	}
	public void wisC(int amount) {
		 wis += amount;
	}
	//public void win(int exp) {
	//	this.exp += exp;
	//}
	public int dexMod() {
		int i = 0;
		int c = -2;
		int temp = dex;
		if(dex<10) c = 2;
		while((temp>10&&c==-2)||(temp<10&&c==2)) {
			temp += c;
			i++;
		}
		return i;
	}
	public int strMod() {
		int i = 0;
		int c = -2;
		int temp = str;
		if(str<10) c = 2;
		while((temp>10&&c==-2)||(temp<10&&c==2)) {
			temp += c;
			i++;
		}
		return i;
	}
	public int conMod() {
		int i = 0;
		int c = -2;
		int temp = con;
		if(con<10) c = 2;
		while((temp>10&&c==-2)||(temp<10&&c==2)) {
			temp += c;
			i++;
		}
		return i;
	}
	public int intMod() {
		int i = 0;
		int c = -2;
		int temp = Int;
		if(Int<10) c = 2;
		while((temp>10&&c==-2)||(temp<10&&c==2)) {
			temp += c;
			i++;
		}
		return i;
	}
	public int wisMod() {
		int i = 0;
		int c = -2;
		int temp = wis;
		if(wis<10) c = 2;
		while((temp>10&&c==-2)||(temp<10&&c==2)) {
			temp += c;
			i++;
		}
		return i;
	}
	public int getHp() {
		return hp;
	}
	public void updateHpAc() {
		hp+=conMod();
		ac+=dexMod();
		
	}
	public int getX() {
		return posT[0];
	}
	public int getY() {
		return posT[1];
	}
	public void setX(int x) {
		posT[0] = x;
	}
	public void setY(int y) {
		posT[1] = y;
	}
	public boolean isValidLine(int x, int y,int[][] pos) {
		int[] a = {posT[0],posT[1]};
		int[] b = {x,y};
		boolean out = true;
		if(a[0]==b[0]) {
			for(int i = posT[1];i<y;i++) {
				if(pos[x][i]>1) {
					out= false;
				}
			}
			for(int i = y;i<posT[1];i++) {
				if(pos[x][i]>1) {
					out= false;
				}
			}
		}else if (a[1]==b[1]) {
			for(int i = posT[0];i<x;i++) {
				if(pos[i][y]>1) {
					out= false;
				}
			}
			for(int i = x;i<posT[0];i++) {
				if(pos[i][y]>1) {
					out= false;
				}
			}
		}else {
			for(int i = posT[0];i<=x;i++) {
				for(int j = posT[1]; j<=y;j++) {
					int[] c = {i,j};
					if(onLine(a,b,c)&&pos[i][j]>1) {
						out= false;
					}
				}
			}	
			for(int i = x;i<posT[0];i++) {
				for(int j = y;j<posT[1];j++) {
					int[] c = {i,j};
					if(onLine(a,b,c)&&pos[i][j]>1) {
						out= false;
					}
				}
			}	
		}
		return out;
	}
	public boolean onLine(int[] a,int[] b, int[] c) {
		int crossproduct = (c[1] - a[1]) * (b[0] - a[0]) - (c[0] - a[0]) * (b[1] - a[1]);
		if (Math.abs(crossproduct) != 0) {	
			return false;
		}
		int dotproduct = (c[0] - a[0]) * (b[0] - a[0]) + (c[1] - a[1])*(b[1] - a[1]);
		if(dotproduct < 0){
			return false;
		}
		int squaredlengthba = (b[0] - a[0])*(b[0] - a[0]) + (b[1] - a[1])*(b[1] - a[1]);
		if(dotproduct > squaredlengthba){
			return false;
		}
		return true;   
	}
}