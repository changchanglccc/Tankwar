package com.graduationproject.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.*;//�����и��ܲ�����������࣬�����������


public class Tank {
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;

	private boolean live = true;// ��̹��Ҳ����һ����������������һ��ʼ�ǻ��ŵ�
	private BloodBar bb = new BloodBar(); 
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private int life = 100;// ��̹������ֵ

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	TankClient tc;// ���ж�����ȥ����

	private boolean good;// ����̹�˵ĺû�,��Ҫ��һ���������ܷ��������������get�������޸�Դ����

	public boolean isGood() {
		return good;
	}

	private int x, y;
	private int oldX, oldY;// Ϊ�˽��̹��ײǽ�ס�����⣬��¼̹����һ����λ��

	private static Random r = new Random();// ��һ������������������ˣ�Ū��static����ҹ���

	private boolean bL = false, bU = false, bR = false, bD = false;

	
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;

	private int step = r.nextInt(12) + 3;// ������Сֵ�ƶ����������ֵ�ƶ�14���������ǲ�����׼���������ٻ�����
	
	static Map<String, Image> imgs  = new HashMap<String, Image>();
	static{
		load();
	}
	
	/*
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	/**
	 * ���
	 * ̹��ͼƬ��������һ���﷨
	 */
	/*	static {//��̬���������������һ�ν����ڴ��ʱ������ִ�е�һ�δ��루���ʺϸ�һЩ��������ʼ��������
		tankImages = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-l.gif")),	//Explode.class �Ƿ��䣨reflection��Class�����������һЩ�Ѿ�����õ�class�ļ�����Ϣ
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-lu.gif")),	
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-u.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-ru.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-r.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-rd.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-d.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/hero/tank-ld.gif")),
				
				tk.getImage(Tank.class.getClassLoader().getResource("images/tank/enemy/tank-l.png")),	//Explode.class �Ƿ��䣨reflection��Class�����������һЩ�Ѿ�����õ�class�ļ�����Ϣ
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

	public Tank(int x, int y, boolean good, Direction dir, TankClient tc) {// ��ʱ���ʼ��̹��tc
		this(x, y, good);
		this.dir = dir;// ���������������Ϊ�з���̹�˾ͻᶯ
		this.tc = tc;
	}

	public void draw(Graphics g) {// ����һ�����ʣ��Լ���̹�˼���
		if (!live) {
			if (!good) {
				tc.tanks.remove(this);
			}
			return;// ��̹���������򲻻����ˣ���ȻҲ����Ļ��ʧ��
		}
		
		if(good) bb.draw(g);//��Ѫ��

		
		
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
		//Ū����̹����
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
		// ÿ��moveһ��֮ǰ����¼��һ����λ��
		this.oldX = x;
		this.oldY = y;

		switch (dir) {
		case L:
			x -= XSPEED;// Ϊ���Ժ���޸ģ�Ū������SPEED��Ϊ�˸����޸ģ�Ū��x��y����������ٶ�
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
		}// ��֮�󲻹�̹�˾ͻ���磬���Դ����һ��

		if (x < 0)
			x = 0;
		if (y < 25)
			y = 25;// ��Ҫ���Ǳ������ĸ߶�
		if (x + Tank.WIDTH > TankClient.GAME_WIDTH)
			x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if (y + Tank.HEIGHT > TankClient.GAME_HEIGHT)
			y = TankClient.GAME_HEIGHT - Tank.HEIGHT;

		if (!good) {
			Direction[] dirs = Direction.values();// �Ѱ˸�����Ū������
			if (step == 0) {
				step = r.nextInt(12) + 3;// ����һ���µ���������Ż�ת����
				int rn = r.nextInt(dirs.length);// ����ÿ��һ�£������������������
				dir = dirs[rn];
			}
			step--;

			if (r.nextInt(40) > 38)
				this.fire();// Ϊ���õ����ڻ���ô����
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
				this.life = 100;//��Ѫ��д��ȥ
				break;
			}
		case KeyEvent.VK_LEFT:// ���¼��ı䷽��
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
			dir = Direction.LU;// ����else �ͻ�����ж����漸��if
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
		case KeyEvent.VK_CONTROL:// ������̧��ctrl��֮�������ڵ��Ͳ��ܼ���
			fire();
			break;
		case KeyEvent.VK_LEFT:// ���¼��ı䷽��
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
		case KeyEvent.VK_A:// ���ӳ����ڵ���ť
			superFire();
			break;
		}
		locateDirection();
	}

	public Missile fire() {// ̹�˴���ӵ������Է���ֵ��Missile
		if (!live)
			return null;
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
		Missile m = new Missile(x, y, good, ptDir, this.tc);// ̹�˵�x,y������̹�˴��ڵ�ʱ���λ�úͷ���
		tc.missiles.add(m);
		if(good)  Sound.sounds(Sound.FIRE);
		return m;
	}

	public Missile fire(Direction dir) {// ��һ�������ڵ�
		if (!live)
			return null;
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
		Missile m = new Missile(x, y, good, dir, this.tc);// ̹�˵�x,y������̹�˴��ڵ�ʱ���λ�úͷ���
		tc.missiles.add(m);
		return m;
	}

	public Rectangle getRect() {// ��̹��ҲŪ������~
		return new Rectangle(x, y, 35, 35);//�����35�Ǹ���ͼƬ���аױߣ�ȥ���ױ�֮���ʵ�ʴ�С��ע�⣺tankImages[0].getHeight(null)����ȡͼƬ�ķ�������Ϊ�ױߣ����Է����ˣ���Ϊд����״̬
	}
/**
 * ײǽ
 * @param w ��ײ��ǽ
 * @return ײ���˷���true������false
 */
	public boolean collidesWithWall(Wall w) {
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.stay();// ��һ����ǽײ�ϣ��ͻص���һ��û��ǽײ��Ȼ�󻻸��������������
			return true;
		}
		return false;
	}

	public boolean collidesWithTanks(java.util.List<Tank> tanks) {// ���̹���໥�غϵ�����
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {// �ж������ǲ�ͬ��̹��
				if (this.live && t.isLive()
						&& this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();// ����̹�˶�ͣס
					return true;// ײ����
				}
			}
		}
		return false;// δײ��
	}

	private void superFire() {// ��Ƴ����ڵ������Գ�8��������ڵ�
		Direction[] dirs = Direction.values();
		for (int i = 0; i < 8; i++) {
			fire(dirs[i]);// ���������һ��
		}
	}

	private class BloodBar {// tankѪ��Ӧ����̹�˵�һ���֣��������˼ά
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-15, WIDTH, 10);//fillRect()��ʵ�ĵģ�����ǿ��ĵģ����������Ǹ���
			int w = WIDTH * life/100;
			g.fillRect(x, y-15, w, 10);
			g.setColor(c);
		}
	}
	
	public boolean eat(Blood b){
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())){
			this.life=100;//����̹�˳���Ѫ�����Ѫ
			b.setLive(false);//��Ѫ����ʧ
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
