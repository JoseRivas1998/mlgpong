package com.tcg.mlgpong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.managers.Content;
import com.tcg.mlgpong.managers.GameStateManager;
import com.tcg.mlgpong.managers.MyControllerProcessor;
import com.tcg.mlgpong.managers.MyInput;
import com.tcg.mlgpong.managers.MyInputProcessor;

public class Game extends ApplicationAdapter {
	
	private GameStateManager gsm;
	
	public static Vector2 SIZE, CENTER;
	
	public static Animation snoop, frog, mlg, shrek, kid, spook, nuke;
	
	public static Content res;
	
	public float time;
	public int frames, fps;
	
	private Texture t;
	
	@Override
	public void create () {
		
		int width, height;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		SIZE = new Vector2(width, height);
		CENTER = new Vector2(width * .5f, height * .5f);
		
		createStaticAnimations();
		
		res = new Content();
		
		//normal sound
		res.loadSound("sound", "airporn.ogg", "airporn");
		res.loadSound("sound", "airhorn.ogg", "airhorn");
		res.loadSound("sound", "SPOOKY.ogg", "xfiles");
		res.loadSound("sound", "HITMARKER.ogg", "hitmarker");
		res.loadSound("sound", "nuke.ogg", "nuke");
		res.loadSound("sound", "explode.ogg", "explode");
		
		//happy sound
		res.loadHappySound("sound", "airporn.ogg");
		res.loadHappySound("sound", "getnoscoped.ogg");
		res.loadHappySound("sound", "wombo.ogg");
		res.loadHappySound("sound", "wow.ogg");
		res.loadHappySound("sound", "danmwow.ogg");
		res.loadHappySound("sound", "camera.ogg");
		res.loadHappySound("sound", "trickshot.ogg");
		res.loadHappySound("sound", "scary.ogg");
		res.loadHappySound("sound", "smokeweed.ogg");
		
		//sad sound
		res.loadSadSound("sound", "2SAD4ME.ogg");
		res.loadSadSound("sound", "whatchasay.ogg");

		res.loadMusic("music", "sandstorm.ogg", "sandstorm", true);		
		res.loadMusic("music", "dankstorm.ogg", "dankstorm", true);		
		res.loadMusic("music", "sanic.ogg", "sanic", true);
		res.loadMusic("music", "whatchasay.ogg", "whatchasay", true);		
		res.loadMusic("music", "allstar.ogg", "allstar", true);
		
		res.loadBitmapFont("font", "faucet.ttf", "main", 56, Color.WHITE);
		res.loadBitmapFont("font", "faucet.ttf", "splash", 72, Color.WHITE);
		
		gsm = new GameStateManager();
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		Gdx.input.setCursorCatched(true);
		
		Controllers.addListener(new MyControllerProcessor());
	}
	
	public static TextureRegion getAnimationFrame(Animation anim, float stateTime) {
		return anim.getKeyFrame(stateTime, true);
	}
	
	private void createStaticAnimations() {
		String path = "snoop/frame_";
		t = null;
		TextureRegion[] snoop = new TextureRegion[58];
		for(int i = 0; i < snoop.length; i++) {
			if(i < 10) {
				t = new Texture(path + "00" + i + ".gif");
			} else {
				t = new Texture(path + "0" + i + ".gif");
			}
			snoop[i] = new TextureRegion(t);
		}
		path = "frog/frame_00";
		TextureRegion frog[] = new TextureRegion[10];
		for(int i = 0; i < frog.length; i++) {
			t = new Texture(path + i + ".gif");
			frog[i] = new TextureRegion(t);
		}
		path = "shrek/frame_0";
		TextureRegion[] shrek = new TextureRegion[20];
		for(int i = 0; i < shrek.length; i++) {
			if(i < 10) {
				t = new Texture(path + "0" + i + ".gif");
			} else {
				t = new Texture(path + i + ".gif");
			}
			shrek[i] = new TextureRegion(t);
		}
		path = "1kid/frame_0";
		TextureRegion[] kid = new TextureRegion[28];
		for(int i = 0; i < kid.length; i++) {
			if(i < 10) {
				t = new Texture(path + "0" + i + ".gif");
			} else {
				t = new Texture(path + i + ".gif");
			}
			kid[i] = new TextureRegion(t);
		}
		path = "skellyman/frame_0";
		TextureRegion[] spook = new TextureRegion[5];
		for(int i = 0; i < spook.length; i++) {
			if(i < 10) {
				t = new Texture(path + "0" + i + ".gif");
			} else {
				t = new Texture(path + i + ".gif");
			}
			spook[i] = new TextureRegion(t);
		}
		path = "mlg/frame_0";
		TextureRegion[] mlg = new TextureRegion[29];
		for(int i = 0; i < mlg.length; i++) {
			if(i < 10) {
				t = new Texture(path + "0" + i + ".gif");
			} else {
				t = new Texture(path + i + ".gif");
			}
			mlg[i] = new TextureRegion(t);
		}
		path = "nuke/frame_0";
		TextureRegion[] nuke = new TextureRegion[17];
		for(int i = 7; i < nuke.length + 7; i++) {
			if(i < 10) {
				t = new Texture(path + "0" + i + ".gif");
			} else {
				t = new Texture(path + i + ".gif");
			}
			nuke[i - 7] = new TextureRegion(t);
		}
		
		Game.snoop = new Animation(.05f, snoop);
		Game.mlg = new Animation(.1f, mlg);
		Game.frog = new Animation(.05f, frog);
		Game.shrek = new Animation(.1f, shrek);
		Game.kid = new Animation(.1f, kid);
		Game.spook = new Animation(.125f, spook);
		Game.nuke = new Animation(.1f, nuke);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		frames++;
		float dt = Gdx.graphics.getDeltaTime();
		time += dt;
		if(time >= 1) {
			fps = frames;
			frames = 0;
			time = 0;
		}
		Gdx.graphics.setTitle("MLG Pong | " + fps + " fps");
		gsm.handleInput();
		gsm.update(dt);
		gsm.draw(dt);
		
		MyInput.update();
	}

	@Override
	public void resize(int width, int height) {
		Game.SIZE.set(width, height);
		Game.CENTER.set(width * .5f, height * .5f);
		gsm.resize(Game.SIZE);
	}

	@Override
	public void dispose() {
		gsm.dispose();
		res.removeAll();
		t.dispose();
	}
	
}
