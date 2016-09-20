package com.graduationproject.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
/**
 * 这个类的作用是坦克游戏的主窗口
 * @author LiChong
 *
 */
public class TankClient extends Frame{
	/**
	 * 整个坦克游戏宽度
	 */
	public static final int GAME_WIDTH=800;
	/**
	 * 整个坦克游戏高度
	 */
	public static final int GAME_HEIGHT=600;	
	
	Tank myTank = new Tank(50,50,true,Direction.STOP,this);//好蛋相应改动,让我的坦克一开始不动
	//Tank enemyTank = new Tank(100,100,false,this);//弄个坏蛋
	
	Explode e = new Explode(70,70,this);
	
	Wall w1 = new Wall(100,200,20,150,this), w2 = new Wall(300,100,300,20,this);//把墙画出来
	
	List<Explode> explodes = new ArrayList<Explode>();//因为同时可能多个地方爆炸
	List<Missile> missiles = new ArrayList<Missile>();//用容器装多发炮弹
	List<Tank> tanks = new ArrayList<Tank>();
	
	Image offScreenImage = null;//定义那张虚拟图片（双缓冲）
	
	Blood b = new Blood();
	//public static final Font iFont = new Font("宋体", Font.PLAIN, 12);
	//public static final Color iColor = Color.YELLOW;
	/*
	private void drawGameInfo( Graphics g ) {
		String str = "missiles count"+(missiles.size())+
		" explodes count"+ explodes.size()+" tanks count"+ tanks.size()
		+" tanks life"+ myTank.getLife();
		g.setFont(iFont);
		g.setColor(iColor);
		g.drawString(str,10,0);
	}*/
	
	public void paint(Graphics g) {//窗口重画时会自动调用这个方法，x向右，y向下递增
		/*
		 * 指明子弹-爆炸-坦克的数量
		 * 以及坦克的生命值
		 */
		
		g.drawString("missiles count"+ missiles.size(), 10, 50);
		g.drawString("explodes count"+ explodes.size(), 10, 70);
		g.drawString("tanks count"+ tanks.size(), 10, 90);
		g.drawString("tanks life"+ myTank.getLife(), 10, 110);
		
		
		
		if(tanks.size() <= 0){//如果敌人死光，就加新的敌人进来
			for(int i=0; i<5 ; i++){//敌人坦克数量
				tanks.add(new Tank(50 + 40*(i+1), 50, false, Direction.D, this));//现在让敌人坦克统一向下动
			}
		}
		
		for(int i=0;i<missiles.size();i++){
			Missile m = missiles.get(i);
			//if(!m.isLive())missiles.remove(m);//如果子弹死去，则从容器中移除，解决内存可能耗尽的问题
			//方法二，弄一个线程，一段时间清理一次
			//方法三，
			//m.hitTank(enemyTank);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitWall(w1);//调用hitWall()子弹不能穿墙了,哈哈
			m.hitWall(w2);
			m.draw(g);
		}
		//e.draw(g);这句用来测试刚开始的爆炸效果
		for(int i=0;i<explodes.size();i++){
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for(int i=0; i<tanks.size(); i++){
			Tank t = tanks.get(i);
			t.collidesWithWall(w1);//坏坦克也不能穿墙了
			t.collidesWithWall(w2);
			t.collidesWithTanks(tanks);
			t.draw(g);
		}
		
		myTank.collidesWithWall(w1);//这时我的坦克也不能穿墙了
		myTank.collidesWithWall(w2);
		myTank.collidesWithTanks(tanks);//我的坦克也不能碾压敌人了
		
		myTank.draw(g);
		myTank.eat(b);
		w1.draw(g);//画出墙来
		w2.draw(g);
		b.draw(g);
	}
	
	public void update(Graphics g) {//就是paint里面那支画笔，现在从中间截住它
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}//把所有东西都花在这张虚拟图片上，一下子全贴上去，以解决闪烁问题，还需要这张图的画笔
		Graphics gOffScreen = offScreenImage.getGraphics();//拿背后图片的画笔
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);//小窍门：alt+/键可以给提示
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}//需要刷背景，不然就是一条粗线。。。

/**
 * 本方法显示坦克主窗口
 */
	public void lauchFrame(){
		
		for(int i=0; i<10 ; i++){//敌人坦克数量
			tanks.add(new Tank(50 + 40*(i+1), 50, false, Direction.D, this));//现在让敌人坦克统一向下动
		}
		
		this.setLocation(400, 300);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");//改变标题栏的文字
		this.addWindowListener(new WindowAdapter(){//窗口可以关闭了
			public void windowClosing(WindowEvent e) {
				System.exit(0);//表示正常的退出
			}			
		});//匿名类
		this.setResizable(false);//不让窗口改变大小
		this.setBackground(Color.BLACK);//背景色为绿
		
		this.addKeyListener(new KeyMonitor());
		setVisible(true);
		
		new Thread(new PaintThread()).start();
	}
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		Sound.sounds(Sound.START);
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable{
		public void run(){//使用内部类，让他每隔一段时间重画一次,且只为TankClient服务
			while(true){
				repaint();//内部调用paint方法(先update方法再paint方法)，自己没有就去调父类的，或者爷爷。。的
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					e.printStackTrace();	
			}
		  }
     }
  }
	
	private class KeyMonitor extends KeyAdapter{//键盘监听，用于控制坦克，用内部类来实现，方便访问各项
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			//System.out.println("ok");//先验证键盘监听有没有加上去，一点一点来,循序渐进
			myTank.KeyPressed(e);
			}
		}	
	}


	
