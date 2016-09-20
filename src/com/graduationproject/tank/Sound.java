package com.graduationproject.tank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Sound {
	private static AudioDataStream ads;
	private static AudioStream as;
	private static FileInputStream fis;
	public static final int ADD = 1;
	public static final int FIRE = 2;
	public static final int SUPFIRE = 3;
	public static final int HIT = 4;
	public static final int START = 5;
	public static final int EXPLODE = 6;
	public static final int HEXPLODE = 7;
	public static final int SHOT = 8;	
	private static AudioData hitData = null;
	private static AudioData shotData = null;	
	private static AudioData fireData = null;	
	private static AudioData supfireData = null;
	private static AudioData startData = null;
	private static AudioData addData = null;
	private static AudioData explodeData = null;
	private static AudioData hexplodeData = null;
	public static void sounds(int key){
		switch(key){
		case Sound.ADD :
			add();
			break;
		case Sound.EXPLODE :
			e_explode();
			break;
		case Sound.HEXPLODE :
			h_explode();
			break;
		case Sound.FIRE :
			fire();
			break;
		case Sound.HIT :
			hit();
			break;
		case Sound.SHOT :
			shot();
			break;
		case Sound.START :
			start();
			break;
		}
	}
	private static void hit(){
		try {
			if(hitData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/hit.wav"));
				as = new AudioStream(fis);
				hitData = as.getData();
			}
			ads = new AudioDataStream(hitData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void shot(){
		try {
			if(shotData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/shot.wav"));
				as = new AudioStream(fis);
				shotData = as.getData();
			}
			ads = new AudioDataStream(shotData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void fire(){
		try {
			if(fireData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/fire.wav"));
				as = new AudioStream(fis);
				fireData = as.getData();
			}
			ads = new AudioDataStream(fireData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private static void supfire(){
		try {
			if(fireData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/superfire.wav"));
				as = new AudioStream(fis);
				supfireData = as.getData();
			}
			ads = new AudioDataStream(supfireData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static void start() {
		try {
			if(startData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/start.wav"));
				as = new AudioStream(fis);
				startData = as.getData();
			}
			ads = new AudioDataStream(startData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void add() {
		try {
			if(addData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/add.wav"));
				as = new AudioStream(fis);
				addData = as.getData();
			}
			ads = new AudioDataStream(addData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void e_explode(){
		try {
			if(explodeData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/e-explosion.wav"));
				as = new AudioStream(fis);
				explodeData = as.getData();
			}
			ads = new AudioDataStream(explodeData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void h_explode(){
		try {
			if(hexplodeData == null){
				fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/sounds/h-explosion.wav"));
				as = new AudioStream(fis);
				hexplodeData = as.getData();
			}
			ads = new AudioDataStream(hexplodeData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void destory(){
		try {
			if(ads != null){
				ads.close();
			}
			if(as != null){
				as.close();
			}
			if(fis != null){
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

