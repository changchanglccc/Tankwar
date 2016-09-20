package com.graduationproject.tank;

import java.awt.*;

public class Blood {
	int x, y, w, h;
	TankClient tc;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image bloodImage = null;
	
	int step = 0;
	private boolean live = true;
	
	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	private int[][] pos ={//血块运动轨迹的点
		{350,300},{360,300},{375,275},{400,200},{360,270},{365,290},{340,280},
		{324,310},{330,300},{350,275},{410,230},{390,270},{365,290},{340,300}
					};
	
	public Blood(){
		x = pos[0][0];
		y = pos[0][1];
		w = h = 15;
	}
	
	public void draw(Graphics g){
		if(!live) return;
		/*Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		*/
		
		bloodImage = tk.getImage(Blood.class.getClassLoader().getResource("images/blood/blood.gif"));
		g.drawImage(bloodImage, x, y, null);
		
		move();
	}
	
	public void move(){
		step++;
		if(step == pos.length){//step加满后回零
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
}
