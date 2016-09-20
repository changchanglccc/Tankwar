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
	private boolean Live = true;//子弹初始为活着
	
	private TankClient tc;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs={//定义imgs数组，用来存放子弹的图片，两种子弹，到时候分别调用就行
		tk.getImage(Missile.class.getClassLoader().getResource("images/missile/bomb.png")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missile/missile.png")),	
	};
		
	public Missile(int x, int y, Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	
	public Missile(int x, int y,  boolean good, Direction dir, TankClient tc){//方法三
		this(x,y,dir);
		this.good = good;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		if(!Live){//在画子弹前先判断子弹生死，死了就不画了，并且把它从容器中移除
			tc.missiles.remove(this);
			return;
		} 
		//分别定义好坏子弹：
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
	
	public boolean isLive() {//调用这个方法的时候就知道子弹的生死了
		return Live;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean hitTank(Tank t){//碰撞检测有辅助类Rectangle
		if(this.Live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()){//子弹的方块与坦克方块相交,并且要保证与活着的坦克相交,还要保证坦克和子弹同时都活着
			if(t.isGood()){//自己坦克被打中，生命值减20
				t.setLife(t.getLife()-20);
				if(t.getLife() <= 0){
					t.setLive(false);}
			} else {//敌人坦克被打中，直接死
				t.setLive(false);
			}
			this.Live = false;//3.子弹也应该死掉
			Explode e = new Explode(x, y, tc);//4.打到的时候，产生爆炸效果
			tc.explodes.add(e);
			if(t.isGood()&&t.isLive()){//好坦克刚被击打音效
				Sound.sounds(Sound.HIT);
			}else if(t.isGood()&&t.isLive()){//好坦克嗝屁的时候
				Sound.sounds(Sound.HEXPLODE);
			}else{//坏坦克嗝屁的时候
				Sound.sounds(Sound.EXPLODE);
			}	
			return true;//1.说明打中了，想办法让坦克消失
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
		if(this.Live && this.getRect().intersects(w.getRect())){//子弹活着且撞墙
			this.Live = false;
			//Sound.sounds(Sound.SHOT); 注意：现在墙还不完善，完善之后再加音效
			return true;//撞到了
		}
		return false;//未撞到
	}
	
}
