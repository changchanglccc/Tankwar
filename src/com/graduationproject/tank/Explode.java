package com.graduationproject.tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explode {
	int x, y;
	private boolean live = true;
	
	private TankClient tc;//Ū����ܼҹ���
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();//ͨ��������߰���ķ�����Ӳ���ϵĶ����ù���
	//ToolKit��һЩ��������ȥ����һЩ�йز���ϵͳ�ĵ��ǲ��ʺ�java���ʵĶ���

	private static Image[] imgs={//����imgs���飬������ű�ը��ͼƬ,static��������ÿnewһ��explode�ʹ��ڴ�קһ��ͼƬ
		tk.getImage(Explode.class.getClassLoader().getResource("images/explosion/1.png")),	//Explode.class �Ƿ��䣨reflection��Class�����������һЩ�Ѿ�����õ�class�ļ�����Ϣ
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
		
		if(!init) {//�����һ�δ���ʱû�б�ըЧ�������⡣��һ�δ���ʱ���첽װ�أ�ͼ��ûŪ���������������ֶ�Ū��
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
