package p01;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*; 

public class Driver extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private static Player p = new Player();	 
	ArrayList<EnemyTemplate> enemy1 = new ArrayList<EnemyTemplate>();
	private static WeaponList w = new WeaponList(p);
	private static SpellList s = new SpellList(p);
	private static Attack a = new Attack();
	private static final int CANVAS_WIDTH  = 1500;
	private static final int CANVAS_HEIGHT = 1000;
	private int[][] pos = new int[50][50];
	private DrawCanvas canvas;
	private JTextArea ta1 = new JTextArea(0,0);
	private JTextArea console = new JTextArea(0,0);
	private JScrollPane scroll = new JScrollPane(console);
	private boolean b = false;
	private int counter = 0;
	ConsoleStorage c = new ConsoleStorage();
	private void setup() {
		enemy1.add(new TestE());
		enemy1.add(new Mage());
		enemy1.add(new Fighter());
		enemy1.get(0).setX(8);
		enemy1.get(0).setY(8);
		enemy1.get(1).setX(9);
		enemy1.get(1).setY(9);
		enemy1.get(2).setX(10);
		enemy1.get(2).setY(10);
		for(int i = 0;i<enemy1.size();i++) {
			pos[enemy1.get(i).getX()][enemy1.get(i).getY()] = enemy1.get(i).getNum(); 
		}
	}
	public Driver() {
		setup();
		console.setEditable(false);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    pos[p.getX()][p.getY()] = 1;
		canvas = new DrawCanvas();   
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		ta1.setBounds(1000, 980, 500, 20);
		scroll.setBounds(1000, 0, 500, 980);
		this.add(scroll);
		this.add(ta1);
		Container cp = getContentPane();
		ta1.addKeyListener(this);
		cp.add(canvas);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		pack();  
		setTitle("Test");
		setVisible(true);
		c.setText("str, con, dex, int, wis bounus int in this order totaling of 20");
		console.setText(c.getText());
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Driver(); // Let the constructor do the job
			}
		});
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(b) {
			ta1.setText("");
			b=!b;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = 0;i<enemy1.size();i++) {
			pos[enemy1.get(i).getX()][enemy1.get(i).getY()] = enemy1.get(i).getNum(); 
			System.out.println(enemy1.get(i).getNum());
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(counter == 0) {
				Scanner sc = new Scanner(ta1.getText());
				if(ta1.getText().equalsIgnoreCase("even")) {
					p.strC(4);
					p.conC(4);
					p.dexC(4);
					p.intC(4);
					p.wisC(4);
					sc.close();
				}else {
					int str = 0;
					int dex = 0;
					int con = 0;
					int Int = 0;
					int wis = 0;
					int additions = 0;
					str = sc.nextInt();
					con = sc.nextInt();
					dex = sc.nextInt();
					Int = sc.nextInt();
					wis = sc.nextInt();
					additions = str+dex+con+Int+wis;
					while(additions!=20) {
						c.setText("\ninvalid amount re-enter str, con, dex, int, wis bounus int in this order totaling of 20");
						console.setText(c.getText());
						str = sc.nextInt();
						con = sc.nextInt();
						dex = sc.nextInt();
						Int = sc.nextInt();
						wis = sc.nextInt();
					}
					sc.close();
					p.strC(str);
					p.conC(con);
					p.dexC(dex);
					p.intC(Int);
					p.wisC(wis);					
				}
				p.updateHpAc();
				c.setText("\nstats mods are\nstr - " + p.strMod() + "\ncon - " + p.conMod() + "\ndex - " +p.dexMod() 
					+ "\nint - " +p.intMod() + "\nwis - " +p.wisMod() + "\nac - " + p.getAc() + "\nhp - " + p.getHp());
				counter++;
			}else if(ta1.getText().toLowerCase().contains("move")) {
				String temp = (p.getX()+1) +","+(p.getY()+1);
				String[] input = ta1.getText().replaceAll("[a-zA-Z ]", "").split(",");
				boolean isLine = p.isValidLine(Integer.parseInt(input[0])-1, Integer.parseInt(input[1])-1, pos);
				if(a.getDist(p.getX(),Integer.parseInt(input[0])-1, p.getY(), Integer.parseInt(input[1])-1) <= p.getMove()&& isLine&&pos[Integer.parseInt(input[0])-1][Integer.parseInt(input[1])-1]==0) {
					pos[Integer.parseInt(input[0])-1][Integer.parseInt(input[1])-1] = 1;
					pos[p.getX()][p.getY()] = 0;
					p.setX(Integer.parseInt(input[0])-1);
					p.setY(Integer.parseInt(input[1])-1);
					c.setText("\nplayer moved from "+temp+" to " + input[0] + "," + input[1]);
				}else if (a.getDist(p.getX(),Integer.parseInt(input[0])-1, p.getY(), Integer.parseInt(input[1])-1) > p.getMove()) {
					c.setText("\nPlayer cant move " + a.getDist(p.getX(),Integer.parseInt(input[0])-1, p.getY(), Integer.parseInt(input[1])-1)+" ft");
				}else if (isLine==false) {
					c.setText("\nPlayer can't move throught an occupyed square");
				} else {
					c.setText("\nPlayer cant occupy an taken square");
				}
			}else if(ta1.getText().toLowerCase().contains("attack")) {
				c.setText("\n"+ta1.getText().toLowerCase());
				Scanner sc = new Scanner(ta1.getText());
				sc.next();
				boolean isSpell = sc.nextBoolean();
				int id = sc.nextInt();
				int enemy = sc.nextInt();
				boolean loS = p.isValidLine(enemy1.get(enemy).getX(),enemy1.get(enemy).getY(), pos);
				w.setWeapon(id);
				s.setSpell(id);
				int type = -1;
				if(isSpell)type = s.getType();
				else { type = w.getType();}
				System.out.println(enemy1.get(enemy).getX()+"\t"+enemy1.get(enemy).getY()+"\t"+enemy1.get(enemy).getClass());

				if(loS) {
					enemy1.get(enemy).takeDamage(a.dmgP(p,enemy1.get(enemy), isSpell, w, s),type);
					c.setText("\nEnemy hp - " + enemy1.get(enemy).getHp());
				}else {
					c.setText("\nNo Line of sight");
				}
				sc.close();
			}else if(ta1.getText().toLowerCase().contains("range")) {
				int temp = Integer.parseInt(ta1.getText().replaceAll("[^0-9]",""));
				c.setText("\nShowing range " + temp);
				for(int i = 0; i<50;i++) {
					for(int j=0;j<50;j++) {
						if(pos[i][j]==0&&a.getDist(p.getX(), i, p.getY(), j)<=temp&&p.isValidLine(i, j, pos)) {
							pos[i][j]=-1;
						}
					}
				}
			}else if(ta1.getText().toLowerCase().contains("dist")) {
				String[] input = ta1.getText().replaceAll("[a-zA-Z ]", "").split(",");
				c.setText("\nPlayer is " + a.getDist(p.getX(), Integer.parseInt(input[0]),p.getY(), Integer.parseInt(input[1])) + " ft from " + input[0]+","+input[1]);
			}else if(ta1.getText().toLowerCase().contains("meove")) {
				enemy1.get(2).moveE(p.getX(),p.getY(),pos);
			}
			for (int i = 0; i < enemy1.size(); i++) {
				if(enemy1.get(i).isDead()) {
					pos[enemy1.get(i).getX()][enemy1.get(i).getY()] = 0;
				}
			}
			console.setText(c.getText());
			c.setText("\n");
			b=!b;
			this.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@SuppressWarnings("serial")
	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);  
			for(int i = 0; i<50;i++) {
				for(int j = 0; j<50;j++) {
					if(pos[i][j] == -1) {
						g.setColor(Color.yellow);
						g.fillRect(i*20, j*20, 20, 20);
						pos[i][j]=0;
					}
					if(pos[i][j] == 1) {
						g.setColor(Color.blue);
						g.fillRect(i*20, j*20, 20, 20);
					}else if(pos[i][j] == 2) {
						g.fillRect(i*20, j*20, 20, 20);
					}else if(pos[i][j] == 3) {
						g.setColor(Color.green);
						g.fillRect(i*20, j*20, 20, 20);
					}else if(pos[i][j] == 4) {
						g.setColor(Color.MAGENTA);
						g.fillRect(i*20, j*20, 20, 20);
					}else if(pos[i][j] == 5) {
						g.setColor(Color.MAGENTA);
						g.fillRect(i*20, j*20, 20, 20);
					}else if(pos[i][j] == 6) {
						g.setColor(Color.GRAY);
						g.fillRect(i*20, j*20, 20, 20);
					}
					g.setColor(Color.black);
					g.drawRect(i*20, j*20, 20, 20);
				}
			}
		}
	}
} 