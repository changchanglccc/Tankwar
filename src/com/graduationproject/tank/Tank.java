package com.graduationproject.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.*;//里面有个能产生随机数的类，随机数产生器


public class Tank {
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;

	private boolean live = true;// 给坦克也定义一个代表生死的量，一开始是活着的
	private BloodBar bb = new BloodBar(); 
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private int life = 100;// 给坦克生命值

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	TankClient tc;// 持有对象，再去引用

	private boolean good;// 区分坦克的好坏,需要用一个方法才能访问它，所以添加get方法，修改源代码

	public boolean isGood() {
		return good;
	}

	private int x, y;
	private int oldX, oldY;// 为了解决坦克撞墙黏住的问题，记录坦克上一步的位置

	private static Random r = new Random();// 有一个随机数产生器就行了，弄成static，大家共享

	private boolean bL = false, bU = false, bR = false, bD = false;

	
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;

	private int step = r.nextInt(12) + 3;// 让它最小值移动三步，最大值移动14步，而不是不动。准备动几步再换方向
	
	static Map<String, Image> imgs  = new HashMap<String, Image>();
	static{
		load();
	}
	
	/*
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	/**
	 * 存放
	 * 坦克图片，试用另一种语法
	 */
	/*	static {//静态代码区：当代码第一次进入内存的时候，首先执行的一段代码（最适合给一些变量做初始化工作）
		tankImages = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-l.gif")),	//Explode.class 是反射（reflection）Class这个类是描述一些已经编译好的class文件的信息
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-lu.gif")),	
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-u.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-ru.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-r.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-rd.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-d.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-ld.gif")),
				
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-l.png")),	//Explode.class 是反射（reflection）Class这个类是描述一些已经编译好的class文件的信息
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-lu.png")),	
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-u.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-ru.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-r.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-rd.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-d.png")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-ld.png"))
		};
		
		imgs.put("L", tankImages[0]);
		imgs.put("LU", tankImages[1]);
		imgs.put("U", tankImages[2]);
		imgs.put("RU", tankImages[3]);
		imgs.put("R", tankImages[4]);
		imgs.put("RD", tankImages[5]);
		imgs.put("D", tankImages[6]);
		imgs.put("LD", tankImages[7]);
		
		imgs.put("eL", tankImages[8]);
		imgs.put("eLU", tankImages[9]);
		imgs.put("eU", tankImages[10]);
		imgs.put("eRU", tankImages[11]);
		imgs.put("eR", tankImages[12]);
		imgs.put("eRD", tankImages[13]);
		imgs.put("eD", tankImages[14]);
		imgs.put("eLD", tankImages[15]);
		
	}*/
	
	public static final int WIDTH = 48;
	public static final int HEIGHT = 48;

	
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}

	public Tank(int x, int y, boolean good, Direction dir, TankClient tc) {// 这时候初始化坦克tc
		this(x, y, good);
		this.dir = dir;// 加上这个参数，因为有方向，坦克就会动
		this.tc = tc;
	}

	public void draw(Graphics g) {// 给他一个画笔，自己画坦克即可
		if (!live) {
			if (!good) {
				tc.tanks.remove(this);
			}
			return;// 若坦克死掉，则不画它了，自然也从屏幕消失了
		}
		
		if(good) bb.draw(g);//画血条

		
		
		if(good){
		switch(ptDir) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
		}
	}
		//弄敌人坦克类
		if(!good){
			switch(ptDir) {
			case L:
				g.drawImage(imgs.get("eL"), x, y, null);
				break;
			case LU:
				g.drawImage(imgs.get("eLU"), x, y, null);
				break;
			case U:
				g.drawImage(imgs.get("eU"), x, y, null);
				break;
			case RU:
				g.drawImage(imgs.get("eRU"), x, y, null);
				break;
			case R:
				g.drawImage(imgs.get("eR"), x, y, null);
				break;
			case RD:
				g.drawImage(imgs.get("eRD"), x, y, null);
				break;
			case D:
				g.drawImage(imgs.get("eD"), x, y, null);
				break;
			case LD:
				g.drawImage(imgs.get("eLD"), x, y, null);
				break;
			}
		}
		
		move();
	}

	void move() {
		// 每当move一下之前，记录上一步的位置
		this.oldX = x;
		this.oldY = y;

		switch (dir) {
		case L:
			x -= XSPEED;// 为了以后好修改，弄个常量SPEED，为了更好修改，弄成x和y两个方向的速度
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}

		if (this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}// 这之后不管坦克就会出界，所以从这改一下

		if (x < 0)
			x = 0;
		if (y < 25)
			y = 25;// 需要考虑标题栏的高度
		if (x + Tank.WIDTH > TankClient.GAME_WIDTH)
			x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if (y + Tank.HEIGHT > TankClient.GAME_HEIGHT)
			y = TankClient.GAME_HEIGHT - Tank.HEIGHT;

		if (!good) {
			Direction[] dirs = Direction.values();// 把八个方向弄成数组
			if (step == 0) {
				step = r.nextInt(12) + 3;// 生成一个新的随机数，才会转方向
				int rn = r.nextInt(dirs.length);// 坏蛋每动一下，随机给它方向让它动
				dir = dirs[rn];
			}
			step--;

			if (r.nextInt(40) > 38)
				this.fire();// 为了让敌人炮火不那么猛烈
		}

	}

	private void stay() {
		x = oldX;
		y = oldY;
	}

	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_F2:
			if(!this.live){
				this.live = true;
				this.life = 100;//把血再写回去
				break;
			}
		case KeyEvent.VK_LEFT:// 按下键改变方向
			bL = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		}
		locateDirection();
	}

	void locateDirection() {
		if (bL && !bU && !bR && !bD)
			dir = Direction.L;
		else if (bL && bU && !bR && !bD)
			dir = Direction.LU;// 不加else 就会继续判断下面几个if
		else if (!bL && bU && !bR && !bD)
			dir = Direction.U;
		else if (!bL && bU && bR && !bD)
			dir = Direction.RU;
		else if (!bL && !bU && bR && !bD)
			dir = Direction.R;
		else if (!bL && !bU && bR && bD)
			dir = Direction.RD;
		else if (!bL && !bU && !bR && bD)
			dir = Direction.D;
		else if (bL && !bU && !bR && bD)
			dir = Direction.LD;
		else if (!bL && !bU && !bR && !bD)
			dir = Direction.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_CONTROL:// 现在是抬起ctrl键之后，这样炮弹就不密集了
			fire();
			break;
		case KeyEvent.VK_LEFT:// 按下键改变方向
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_A:// 增加超级炮弹按钮
			superFire();
			break;
		}
		locateDirection();
	}

	public Missile fire() {// 坦克打出子弹，所以返回值是Missile
		if (!live)
			return null;
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
		Missile m = new Missile(x, y, good, ptDir, this.tc);// 坦克的x,y，代表坦克打炮弹时候的位置和方向
		tc.missiles.add(m);
		if(good)  Sound.sounds(Sound.FIRE);
		return m;
	}

	public Missile fire(Direction dir) {// 朝一个方向发炮弹
		if (!live)
			return null;
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
		Missile m = new Missile(x, y, good, dir, this.tc);// 坦克的x,y，代表坦克打炮弹时候的位置和方向
		tc.missiles.add(m);
		return m;
	}

	public Rectangle getRect() {// 给坦克也弄个方块~
		return new Rectangle(x, y, 35, 35);//这里的35是根据图片（有白边）去掉白边之后的实际大小。注意：tankImages[0].getHeight(null)这种取图片的方法，因为白边，所以放弃了，改为写死的状态
	}
/**
 * 撞墙
 * @param w 被撞的墙
 * @return 撞上了返回true，否则false
 */
	public boolean collidesWithWall(Wall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.stay();// 上一步和墙撞上，就回到上一步没和墙撞，然后换个换个方向继续跑
			return true;
		}
		return false;
	}

	public boolean collidesWithTanks(java.util.List<Tank> tanks) {// 解决坦克相互重合的问题
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {// 判断两辆是不同的坦克
				if (this.live && t.isLive()
						&& this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();// 两辆坦克都停住
					return true;// 撞到了
				}
			}
		}
		return false;// 未撞到
	}

	private void superFire() {// 设计超级炮弹，可以朝8个方向打炮弹
		Direction[] dirs = Direction.values();
		for (int i = 0; i < 8; i++) {
			fire(dirs[i]);// 各个方向打一发
		}
	}

	private class BloodBar {// tank血条应该是坦克的一部分，面向对象思维
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-15, WIDTH, 10);//fillRect()是实心的；这个是空心的，代表外面那个框
			int w = WIDTH * life/100;
			g.fillRect(x, y-15, w, 10);
			g.setColor(c);
		}
	}
	
	public boolean eat(Blood b){
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())){
			this.life=100;//代表坦克吃了血块就满血
			b.setLive(false);//让血块消失
			Sound.sounds(Sound.ADD);
			return true;
		}
		return false;
	}
	
	private static void load() {
		LoadImages.load(LoadImages.MYTANK, imgs);
		LoadImages.load(LoadImages.ENEMYTANK, imgs);
	}
	
}
