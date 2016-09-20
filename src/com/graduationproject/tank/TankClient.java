package com.graduationproject.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
/**
 * ������������̹����Ϸ��������
 * @author LiChong
 *
 */
public class TankClient extends Frame{
	/**
	 * ����̹����Ϸ���
	 */
	public static final int GAME_WIDTH=800;
	/**
	 * ����̹����Ϸ�߶�
	 */
	public static final int GAME_HEIGHT=600;	
	
	Tank myTank = new Tank(50,50,true,Direction.STOP,this);//�õ���Ӧ�Ķ�,���ҵ�̹��һ��ʼ����
	//Tank enemyTank = new Tank(100,100,false,this);//Ū������
	
	Explode e = new Explode(70,70,this);
	
	Wall w1 = new Wall(100,200,20,150,this), w2 = new Wall(300,100,300,20,this);//��ǽ������
	
	List<Explode> explodes = new ArrayList<Explode>();//��Ϊͬʱ���ܶ���ط���ը
	List<Missile> missiles = new ArrayList<Missile>();//������װ�෢�ڵ�
	List<Tank> tanks = new ArrayList<Tank>();
	
	Image offScreenImage = null;//������������ͼƬ��˫���壩
	
	Blood b = new Blood();
	//public static final Font iFont = new Font("����", Font.PLAIN, 12);
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
	
	public void paint(Graphics g) {//�����ػ�ʱ���Զ��������������x���ң�y���µ���
		/*
		 * ָ���ӵ�-��ը-̹�˵�����
		 * �Լ�̹�˵�����ֵ
		 */
		
		g.drawString("missiles count"+ missiles.size(), 10, 50);
		g.drawString("explodes count"+ explodes.size(), 10, 70);
		g.drawString("tanks count"+ tanks.size(), 10, 90);
		g.drawString("tanks life"+ myTank.getLife(), 10, 110);
		
		
		
		if(tanks.size() <= 0){//����������⣬�ͼ��µĵ��˽���
			for(int i=0; i<5 ; i++){//����̹������
				tanks.add(new Tank(50 + 40*(i+1), 50, false, Direction.D, this));//�����õ���̹��ͳһ���¶�
			}
		}
		
		for(int i=0;i<missiles.size();i++){
			Missile m = missiles.get(i);
			//if(!m.isLive())missiles.remove(m);//����ӵ���ȥ������������Ƴ�������ڴ���ܺľ�������
			//��������Ūһ���̣߳�һ��ʱ������һ��
			//��������
			//m.hitTank(enemyTank);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitWall(w1);//����hitWall()�ӵ����ܴ�ǽ��,����
			m.hitWall(w2);
			m.draw(g);
		}
		//e.draw(g);����������Ըտ�ʼ�ı�ըЧ��
		for(int i=0;i<explodes.size();i++){
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for(int i=0; i<tanks.size(); i++){
			Tank t = tanks.get(i);
			t.collidesWithWall(w1);//��̹��Ҳ���ܴ�ǽ��
			t.collidesWithWall(w2);
			t.collidesWithTanks(tanks);
			t.draw(g);
		}
		
		myTank.collidesWithWall(w1);//��ʱ�ҵ�̹��Ҳ���ܴ�ǽ��
		myTank.collidesWithWall(w2);
		myTank.collidesWithTanks(tanks);//�ҵ�̹��Ҳ������ѹ������
		
		myTank.draw(g);
		myTank.eat(b);
		w1.draw(g);//����ǽ��
		w2.draw(g);
		b.draw(g);
	}
	
	public void update(Graphics g) {//����paint������֧���ʣ����ڴ��м��ס��
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}//�����ж�����������������ͼƬ�ϣ�һ����ȫ����ȥ���Խ����˸���⣬����Ҫ����ͼ�Ļ���
		Graphics gOffScreen = offScreenImage.getGraphics();//�ñ���ͼƬ�Ļ���
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);//С���ţ�alt+/�����Ը���ʾ
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}//��Ҫˢ��������Ȼ����һ�����ߡ�����

/**
 * ��������ʾ̹��������
 */
	public void lauchFrame(){
		
		for(int i=0; i<10 ; i++){//����̹������
			tanks.add(new Tank(50 + 40*(i+1), 50, false, Direction.D, this));//�����õ���̹��ͳһ���¶�
		}
		
		this.setLocation(400, 300);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");//�ı������������
		this.addWindowListener(new WindowAdapter(){//���ڿ��Թر���
			public void windowClosing(WindowEvent e) {
				System.exit(0);//��ʾ�������˳�
			}			
		});//������
		this.setResizable(false);//���ô��ڸı��С
		this.setBackground(Color.BLACK);//����ɫΪ��
		
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
		public void run(){//ʹ���ڲ��࣬����ÿ��һ��ʱ���ػ�һ��,��ֻΪTankClient����
			while(true){
				repaint();//�ڲ�����paint����(��update������paint����)���Լ�û�о�ȥ������ģ�����үү������
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					e.printStackTrace();	
			}
		  }
     }
  }
	
	private class KeyMonitor extends KeyAdapter{//���̼��������ڿ���̹�ˣ����ڲ�����ʵ�֣�������ʸ���
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			//System.out.println("ok");//����֤���̼�����û�м���ȥ��һ��һ����,ѭ�򽥽�
			myTank.KeyPressed(e);
			}
		}	
	}


	
