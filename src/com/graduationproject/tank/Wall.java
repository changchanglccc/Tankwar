package com.graduationproject.tank;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Wall {
	int x, y, w, h;
	TankClient tc;
	/*
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs={//定义imgs数组，用来存放墙体的图片
		tk.getImage(Missile.class.getClassLoader().getResource("images/map/steels.jpg")),
		//tk.getImage(Missile.class.getClassLoader().getResource("images//map/steels.jpg")),	
	};*/
	
	public Wall(int x, int y, int w, int h, TankClient tc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
		
	public void draw(Graphics g){
		//g.fillRect(x, y, w, h);	
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		//g.drawImage(imgs[0], x, y, null);
	}
	
	public Rectangle getRect(){//方便以后碰撞检测  【都有这个rect，可以弄个父类，抽象的】
		return new Rectangle(x, y, w, h);
	}
	
	
}
