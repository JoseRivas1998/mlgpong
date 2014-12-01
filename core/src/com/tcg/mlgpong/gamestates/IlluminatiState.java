package com.tcg.mlgpong.gamestates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.Game;
import com.tcg.mlgpong.MyCamera;
import com.tcg.mlgpong.managers.GameStateManager;

public class IlluminatiState extends GameState {

	private float time, timer1, timer2, time1, timer3;
	private MyCamera cam;
	private float tX, tY, tW, tH, imgX, imgY, imgW, imgH;
	private Texture img;
	
	public IlluminatiState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		time = 0;
		time1 = 0;
		timer1 = 7.655f / 2.0f;
		timer2 = 7.655f;
		timer3 = 7.655f * 2; 
		cam = new MyCamera(Game.SIZE);
		cam.translate(Game.CENTER);
		cam.update();
		img = new Texture("entities/illuminati.png");
		Game.res.getMusic("xfiles").play();
	}

	@Override
	public void handleInput() {}

	@Override
	public void update(float dt) {
		Game.flicker = false;
		time += dt;
		if(time < timer1) {
			setTextValues("But Wait");
		} else if(time < timer2) {
			setTextValues("Did you see that?");
		} else {
			time1 += dt;
			setImageValues();
			if(time1 >= timer3) {
				gsm.setState(gsm.TITLE);
			}
		}
	}
	
	private void setTextValues(String text) {
		float descent = Game.res.getFont("splash").getDescent();
		tW = Game.res.getFont("splash").getBounds(text).width;
		tH = Game.res.getFont("splash").getBounds(text).height - descent;
		tX = Game.CENTER.x - (tW * .5f);
		tY = Game.CENTER.y + (tH * .5f);
	}
	
	private void setImageValues() {
		float factor = time1 / timer3;
		imgW = Game.SIZE.x * factor;
		imgH = Game.SIZE.y * factor;
		imgX = Game.CENTER.x - (imgW * .5f);
		imgY = Game.CENTER.y - (imgH * .5f);
	}

	private void drawText(SpriteBatch sb, String text) {
		Game.res.getFont("splash").draw(sb, text, tX, tY);
	}

	private void drawImg(SpriteBatch sb) {
		sb.draw(img, imgX, imgY, imgW, imgH);
	}
	
	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		if(time < timer1) {
			drawText(sb, "But Wait");
		} else if(time < timer2) {
			drawText(sb, "Did you see that?");
		} else {
			drawImg(sb);
			time1 += dt;
			if(time1 >= timer3) {
				gsm.setState(gsm.TITLE);
			}
		}
		sb.end();
	}

	@Override
	public void resize(Vector2 size) {
		cam.resize(size, true);
	}

	@Override
	public void dispose() {
		img.dispose();

	}

}
