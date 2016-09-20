package com.graduationproject.tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explode {
	int x, y;
	private boolean live = true;
	
	private TankClient tc;//弄个大管家管理
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();//通过这个工具包里的方法把硬盘上的东西拿过来
	//ToolKit有一些操作可以去访问一些有关操作系统的但是不适合java访问的东西

	private static Image[] imgs={//定义imgs数组，用来存放爆炸的图片,static这样不用每new一个explode就从内存拽一次图片
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/1.png")),	//Explode.class 是反射（reflection）Class这个类是描述一些已经编译好的class文件的信息
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/2.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/3.png")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/4.png")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/5.png")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/6.png")),	

		/*
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/1.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/2.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/3.png")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/4.png")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/5.png")),
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/6.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/7.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/8.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/9.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/10.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/11.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/12.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/13.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/14.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/15.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/16.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/17.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/18.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/19.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/20.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/21.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/22.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/23.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/24.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/25.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/26.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/27.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/28.png")),	
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/big/29.png"))		
	*/
	};
	int step = 0;
	
	private static boolean init = false;
	
	public Explode(int x, int y, TankClient tc){
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		
		if(!init) {//解决第一次打中时没有爆炸效果的问题。第一次打中时，异步装载，图还没弄过来，所以现在手动弄上
			for (int i = 0; i < imgs.length; i++) {
				g.drawImage(imgs[i], -100, -100, null);
			}			
			init = true;
		}
		
		if(!live){ 
			tc.explodes.remove(this);
			return;
		}
		
		if(step == imgs.length){
			live = false;
			step = 0;
			return;
		}
		
		g.drawImage(imgs[step], x, y, null);
		step++;
	}

	
}
