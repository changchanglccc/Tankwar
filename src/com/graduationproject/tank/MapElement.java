package com.graduationproject.tank;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
public class MapElement {
	public static final int WALL_SIZE = 22;
	public static final int WALLS_SIZE= 44;
	public static final int PROP_SIZE = 30;
	public static final int WALL = 1;
	public static final int WATER = 2;
	public static final int STEEL = 3;
	public static final int STEELS = 4;
	public static final int GRASS = 5;
	public static final int SYMBOL = 6;
	public static final int DESTORY = 7;
	public static final int BOMB = 8;
	public static final int TIMER = 9;
	public static final int MINTANK = 10;
	boolean live = true;
	private static Image[] imgs;
	int x , y;
	int imageSub ;
	TankClient tc;
	private int size;
	//道具计时
	int time = 0;
	static {
		imgs = LoadImages.load(LoadImages.MAPS);
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public MapElement(int x, int y, int imageSub,TankClient tc) {
		this.x = x;
		this.y = y;
		this.imageSub = imageSub;
		this.tc = tc;
	}
		public void draw(Graphics g){
		if(this.time != 0){
			if(++time > 300){
				this.live = false;
			}
		}
		switch(imageSub){
		case WALL :
			g.drawImage(imgs[0], x, y, null);
			size = WALL_SIZE;
			break;
		case WATER :
			g.drawImage(imgs[1], x, y, null);
			size = WALLS_SIZE;
			break;
		case STEEL :
			g.drawImage(imgs[2], x, y, null);
			size = WALL_SIZE;
			break;
		case STEELS :
			g.drawImage(imgs[3], x, y, null);
			size = WALLS_SIZE;
			break;
		case GRASS :
			g.drawImage(imgs[4], x, y, null);
			size = WALLS_SIZE;
			break;
		case SYMBOL :
			g.drawImage(imgs[5], x, y, null);
			size = WALLS_SIZE;
			break;
		case DESTORY :
			g.drawImage(imgs[6], x, y, null);
			size = WALLS_SIZE;
			break;
		case BOMB :
			g.drawImage(imgs[7], x, y, null);
			size = PROP_SIZE;
			break;
		case TIMER :
			g.drawImage(imgs[8], x, y, null);
			size = PROP_SIZE;
			break;
		case MINTANK :
			g.drawImage(imgs[9], x, y, null);
			size = PROP_SIZE;
			break;
		}
	}
		public Rectangle getRec(){
		return new Rectangle(x,y,size,size);
		
	}
	}

