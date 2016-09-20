package com.graduationproject.tank;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Wall {
	int x, y, w, h;
	TankClient tc;
	/*
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs={//����imgs���飬�������ǽ���ͼƬ
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
	
	public Rectangle getRect(){//�����Ժ���ײ���  ���������rect������Ū�����࣬����ġ�
		return new Rectangle(x, y, w, h);
	}
	
	
}
