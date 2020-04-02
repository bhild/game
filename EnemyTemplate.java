package p01;

public abstract class EnemyTemplate {
	ConsoleStorage c = new ConsoleStorage();
	protected int hp = 0;
	protected int ac = 0;
	protected int move = 0;
	protected int attackMod = 0;
	protected int toHitMod = 0;
	protected int sides = 0;
	protected int maxRange = 0;
	protected int weakType = -1;
	protected int strongType = -1;
	protected int[] posT = new int[2];
	protected int num = -1;
	protected Attack a = new Attack();
	protected boolean dead = false;
	public int getAc() {
		return ac;
	}
	public int getMove() {
		return move;
	}
	public int getHp() {
		return hp;
	}
	public void takeDamage(int amount,int type) {
		if(strongType==type)amount=amount/2;
		if(weakType==type)amount=amount*2;
		 hp -= amount;
	}
	public void heal(int amount) {
		 hp += amount;
	}
	public int attackDie() {
		return toHitMod;
	}
	public int attackDmg() {
		return attackMod;
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
	public int getMaxRange() {
		return maxRange;
	}
	public int getWeakType() {
		return weakType;
	}
	public int getStrongType() {
		return strongType;
	}
	public boolean isDead() {
		return dead;
	}
	public int getNum() {
		return num;
	}
	public void moveE(int x, int y,int[][] pos) {
		int x1 = posT[0];
		int y1 = posT[1];
		for(int i = 0; i<x;i++) {
			for(int j=0;j<y;j++) {
				if(pos[i][j]==0&&a.getDist(posT[0], i, posT[1], j)<=move&&isValidLine(i, j, pos)) {
					x1=i;
					y1=j;
				}
			}
		}
		posT[0]=x1;
		posT[1]=y1;
		System.out.println(posT[0]+ "\t" + posT[1]);
	}
	public boolean isValidLine(int x, int y,int[][] pos) {
		int[] a = {this.posT[0],this.posT[1]};
		int[] b = {x,y};
		boolean out = true;
		if(a[0]==b[0]) {
			for(int i = this.posT[1];i<y;i++) {
				if(pos[x][i]>1) {
					out= false;
				}
			}
			for(int i = y;i<this.posT[1];i++) {
				if(pos[x][i]>1) {
					out= false;
				}
			}
		}else if (a[1]==b[1]) {
			for(int i = this.posT[0];i<x;i++) {
				if(pos[i][y]>1) {
					out= false;
				}
			}
			for(int i = x;i<this.posT[0];i++) {
				if(pos[i][y]>1) {
					out= false;
				}
			}
		}else {
			for(int i = this.posT[0];i<=x;i++) {
				for(int j = this.posT[1]; j<=y;j++) {
					int[] c = {i,j};
					if(onLine(a,b,c)&&pos[i][j]>1) {
						out= false;
					}
				}
			}	
			for(int i = x;i<this.posT[0];i++) {
				for(int j = y;j<this.posT[1];j++) {
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
	public int getSides() {
		return sides;
	}
}