package com.graduationproject.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
/**
 * 游戏帮助菜单
 * @author LiChong
 *
 */
public class GameHelp {
		
	public static final String[] HELP = new String[]{
		"帮助菜单: ",
		"J - 发射普通炮弹",
		"K - 发射大型炮弹",
		"L - 红色勾魂弹",
		"P - 暂停游戏",
		"ESC - 返回游戏主界面",
		"H - 进入/退出 帮助菜单",
		"M - 绠�槗/澶嶆潅妯″紡鍒囨崲"
	};
	
	private Image image = null;
	private int w;
	private int h;
	private boolean show;
	
	public GameHelp( int w, int h ) {
		this.w = w;
		this.h = h;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public void setVisible( boolean show ) {
		this.show = show;
	}
	
	public boolean isVisible() {
		return show;
	}

	public Image getHelpImage() {//以图片的方式来显示菜单
		if ( image == null ) {
			BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = img.createGraphics();
			img = g.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
			g.dispose();
			g = img.createGraphics();
			
			g.setColor(new Color(0, 0, 0, 80));		//set the background

			g.fillRoundRect(0, 0, w, h, 5, 5);
			
			Font f = new Font("宋体", Font.PLAIN, 14);
			g.setFont(f);
			g.setColor(Color.WHITE);
			int x_off = 20, y_off = 25;
			for ( int j = 0; j < HELP.length; j++ ) {
				g.drawString(HELP[j], x_off, y_off + j * (f.getSize() + 5));
			}
			
			g.dispose();
			image = img;
		}
		
		return image;
	}
}
