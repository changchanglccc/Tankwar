package com.graduationproject.tank;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Map;
public class LoadImages {
	public static final int MYTANK = 1;
	public static final int ENEMYTANK = 2;
	public static final int MISSILE= 3;//还写在类中
	public static final int EXPLODE = 5;
	public static final int MAPS = 6;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	public static void load(int style, Map<String, Image> imgs) {
		switch (style) {
		case LoadImages.MYTANK:
			images = new Image[] {
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-l.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-lu.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-u.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-ru.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-r.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-rd.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-d.gif")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/tank/hero/tank-ld.gif")), };
			imgs.put("L", images[0]);
			imgs.put("LU", images[1]);
			imgs.put("U", images[2]);
			imgs.put("RU", images[3]);
			imgs.put("R", images[4]);
			imgs.put("RD", images[5]);
			imgs.put("D", images[6]);
			imgs.put("LD", images[7]);
			break;
		case LoadImages.ENEMYTANK:
			images = new Image[] {
				/*	tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/1/1-l.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/1/1-u.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/1/1-r.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/1/1-d.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH), */
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-l.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-lu.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-u.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-ru.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-r.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-rd.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-d.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/2/2-ld.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
		/*			tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/3/3-l.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/3/3-u.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/3/3-r.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/3/3-d.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/4/4-l.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/4/4-u.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/4/4-r.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/4/4-d.png")).getScaledInstance(
							Tank.WIDTH, Tank.HEIGHT,
							Image.SCALE_SMOOTH), */
							};
			imgs.put("eL", images[0]);
			imgs.put("eLU", images[1]);
			imgs.put("eU", images[2]);
			imgs.put("eRU", images[3]);
			imgs.put("eR", images[4]);
			imgs.put("eRD", images[5]);
			imgs.put("eD", images[6]);
			imgs.put("eLD", images[7]);
			break;
		case LoadImages.MISSILE://missile写到missile类中吧。。
			images = new Image[] {//可以合并
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/missile/bomb.png")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/missile/missile.png")),
					};
			//imgs.put("L", images[0]);//暂时不知这种如何表示
			//imgs.put("LU", images[1]);
			break;
		}
	}
	public static Image[] load(int type) {
		switch (type) {
		case LoadImages.EXPLODE:
			images = new Image[] {
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/explosion/1.png")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/explosion/2.png")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/explosion/3.png")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/explosion/4.png")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/explosion/5.png")),
					tk.getImage(LoadImages.class.getClassLoader().getResource(
							"images/explosion/6.png")), };
			break;
		case LoadImages.MAPS:
			images = new Image[] {
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/wall.jpg")).getScaledInstance(
							MapElement.WALL_SIZE, MapElement.WALL_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/sea.jpg")).getScaledInstance(
							MapElement.WALLS_SIZE, MapElement.WALLS_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/steel.jpg")).getScaledInstance(
							MapElement.WALL_SIZE, MapElement.WALL_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/steels.jpg")).getScaledInstance(
							MapElement.WALLS_SIZE, MapElement.WALLS_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/grass.jpg")).getScaledInstance(
							MapElement.WALLS_SIZE, MapElement.WALLS_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/bomb.gif")).getScaledInstance(
							MapElement.PROP_SIZE, MapElement.PROP_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(
							LoadImages.class.getClassLoader().getResource(
									"images/map/timer.gif")).getScaledInstance(
							MapElement.PROP_SIZE, MapElement.PROP_SIZE,
							Image.SCALE_SMOOTH),
				    tk.getImage(//???
							LoadImages.class.getClassLoader().getResource(
									"images/map/home.PNG")).getScaledInstance(
							MapElement.WALLS_SIZE, MapElement.WALLS_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(//time消失时候的
							LoadImages.class.getClassLoader().getResource(
									"images/map/destory.jpg")).getScaledInstance(
							MapElement.WALLS_SIZE, MapElement.WALLS_SIZE,
							Image.SCALE_SMOOTH),
					tk.getImage(//最后统计的时候的小坦克
							LoadImages.class.getClassLoader().getResource(
									"images/tank/enemy/1/1-u.gif")).getScaledInstance(
							MapElement.PROP_SIZE, MapElement.PROP_SIZE,
							Image.SCALE_SMOOTH),
				 };
			break;
		}
		return images;
	}
	public static Image[] load() {
		images = new Image[] {
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/map/firstpicture.jpg")).getScaledInstance(
						TankClient.GAME_WIDTH,
						TankClient.GAME_HEIGHT, Image.SCALE_SMOOTH),
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/map/over.jpg")).getScaledInstance(
						TankClient.GAME_WIDTH, TankClient.GAME_HEIGHT,
						Image.SCALE_SMOOTH),
				tk.getImage(//???
						LoadImages.class.getClassLoader().getResource(
								"images/choose.gif")).getScaledInstance(35, 35,
						Image.SCALE_SMOOTH),
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/map/p1tankU.gif")).getScaledInstance(50,
						50, Image.SCALE_SMOOTH),
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/map/p2tankU.gif")).getScaledInstance(50,
						50, Image.SCALE_SMOOTH),
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/tank/enemy/1/1-u.png")).getScaledInstance(35,
						35, Image.SCALE_SMOOTH),
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/tank/enemy/2/2-u.png")).getScaledInstance(35,
						35, Image.SCALE_SMOOTH),
				tk.getImage(
						LoadImages.class.getClassLoader().getResource(
								"images/tank/enemy/3/3-u.png")).getScaledInstance(35,
						35, Image.SCALE_SMOOTH),
				tk.getImage(//"images/acount4.gif",看看是啥图
						LoadImages.class.getClassLoader().getResource(
								"images/tank/enemy/4/4-u.png")).getScaledInstance(35,
						35, Image.SCALE_SMOOTH), };
		return images;
	}
}

