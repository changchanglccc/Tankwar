package com.graduationproject.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;



public class Missile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	
	int x,y;
	Direction dir;
	
	private boolean good;
	private boolean Live = true;//�ӵ���ʼΪ����
	
	private TankClient tc;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs={//����imgs���飬��������ӵ���ͼƬ�������ӵ�����ʱ��ֱ���þ���
		tk.getImage(Missile.class.getClassLoader().getResource("images/missile/bomb.png")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missile/missile.png")),	
	};
		
	public Missile(int x, int y, Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	
	public Missile(int x, int y,  boolean good, Direction dir, TankClient tc){//������
		this(x,y,dir);
		this.good = good;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		if(!Live){//�ڻ��ӵ�ǰ���ж��ӵ����������˾Ͳ����ˣ����Ұ������������Ƴ�
			tc.missiles.remove(this);
			return;
		} 
		//�ֱ���û��ӵ���
		if(good) g.drawImage(imgs[0], x, y, null);
		else g.drawImage(imgs[1], x, y, null);
		
		move();
	}
	private void move() {
		switch(dir){
		case L:
			x -= XSPEED;
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
		
		if(x<0 || y<0 || x>TankClient.GAME_WIDTH || y>TankClient.GAME_HEIGHT){
			Live = false;
			tc.missiles.remove(this);
		}
		
	}
	
	public boolean isLive() {//�������������ʱ���֪���ӵ���������
		return Live;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean hitTank(Tank t){//��ײ����и�����Rectangle
		if(this.Live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()){//�ӵ��ķ�����̹�˷����ཻ,����Ҫ��֤����ŵ�̹���ཻ,��Ҫ��֤̹�˺��ӵ�ͬʱ������
			if(t.isGood()){//�Լ�̹�˱����У�����ֵ��20
				t.setLife(t.getLife()-20);
				if(t.getLife() <= 0){
					t.setLive(false);}
			} else {//����̹�˱����У�ֱ����
				t.setLive(false);
			}
			this.Live = false;//3.�ӵ�ҲӦ������
			Explode e = new Explode(x, y, tc);//4.�򵽵�ʱ�򣬲�����ըЧ��
			tc.explodes.add(e);
			if(t.isGood()&&t.isLive()){//��̹�˸ձ�������Ч
				Sound.sounds(Sound.HIT);
			}else if(t.isGood()&&t.isLive()){//��̹����ƨ��ʱ��
				Sound.sounds(Sound.HEXPLODE);
			}else{//��̹����ƨ��ʱ��
				Sound.sounds(Sound.EXPLODE);
			}	
			return true;//1.˵�������ˣ���취��̹����ʧ
		}
		return false;
	}
	
	public boolean hitTanks(List<Tank> tanks){
		for(int i=0; i<tanks.size(); i++){
			if(hitTank(tanks.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall w){
		if(this.Live && this.getRect().intersects(w.getRect())){//�ӵ�������ײǽ
			this.Live = false;
			//Sound.sounds(Sound.SHOT); ע�⣺����ǽ�������ƣ�����֮���ټ���Ч
			return true;//ײ����
		}
		return false;//δײ��
	}
	
}
