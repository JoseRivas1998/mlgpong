package com.tcg.mlgpong.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.Game;
import com.tcg.mlgpong.MyCamera;
import com.tcg.mlgpong.managers.GameStateManager;
import com.tcg.mlgpong.managers.MyInput;

public class SplashState extends GameState {
	
	private float imgX, imgY, imgW, imgH, tX, tY, tW, tH, width, snoopX, snoopY, snoopW, snoopH, rotation;
	
	private TextureRegion currentFrame;
	private Animation anim;
	
	private MyCamera cam;
	
	String splash;
	
	float stateTime;
	
	float time, timer;

	public SplashState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		
		timer = 7.655f;
		time = 0;
		
		String path = "splash/";
		
		TextureRegion[] frames = {
				new TextureRegion(new Texture(path + "001.png")),
				new TextureRegion(new Texture(path + "002.png")),
				new TextureRegion(new Texture(path + "002.png")),
				new TextureRegion(new Texture(path + "003.png")),
				new TextureRegion(new Texture(path + "004.png")),
				new TextureRegion(new Texture(path + "005.png")),
				new TextureRegion(new Texture(path + "006.png")),
				new TextureRegion(new Texture(path + "007.png")),
				new TextureRegion(new Texture(path + "008.png")),
				new TextureRegion(new Texture(path + "009.png")),
				new TextureRegion(new Texture(path + "010.png")),
				new TextureRegion(new Texture(path + "011.png")),
				new TextureRegion(new Texture(path + "012.png")),
				new TextureRegion(new Texture(path + "013.png")),
				new TextureRegion(new Texture(path + "014.png")),
				new TextureRegion(new Texture(path + "015.png")),
				new TextureRegion(new Texture(path + "016.png")),
		};
		
		anim = new Animation(.15f, frames);

		currentFrame = frames[0];
		
		stateTime = 0;
		
		cam = new MyCamera(Game.SIZE);
		cam.translate(Game.CENTER);
		cam.update();
		
		splash = "Tiny Country Games Presents";
		
		Game.res.getSound("xfiles").play();
	}

	@Override
	public void handleInput() {
		if(MyInput.anyKeyDown()) {
			gsm.setState(gsm.TITLE);
		}

	}

	@Override
	public void update(float dt) {
		imgW = currentFrame.getRegionWidth();
		imgH = currentFrame.getRegionHeight();
		imgY = Game.CENTER.y - (imgH * .5f);
		tW = Game.res.getFont("splash").getBounds(splash).width;
		tH = Game.res.getFont("splash").getBounds(splash).height - Game.res.getFont("main").getDescent();
		width = imgW + 50 + tW;
		imgX = Game.CENTER.x - (width * .5f);
		tX = imgX + imgW + 50;
		tY = Game.CENTER.y + (tH * .5f);
		time += dt;
		if(time >= timer) {
			gsm.setState(gsm.TITLE);
		}
		snoopW = Game.SIZE.x;
		snoopH = Game.SIZE.y;
		snoopX = Game.CENTER.x - (snoopW * .5f);
		snoopY = Game.CENTER.y - (snoopH * .5f);
		rotation = 360 * (time / timer);
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		stateTime += dt;
		currentFrame = anim.getKeyFrame(stateTime, true);
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		sb.setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
		sb.draw(Game.getAnimationFrame(Game.snoop, stateTime), snoopX, snoopY, snoopX + (snoopW * .5f), snoopY + (snoopH * .5f), snoopW, snoopH, 1, 1, rotation);
		sb.setColor(Color.WHITE);
		sb.draw(currentFrame, imgX, imgY);
//		Game.res.getFont("splash").setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
		Game.res.getFont("splash").draw(sb, splash, tX, tY);
		sb.end();

	}

	@Override
	public void resize(Vector2 size) {
		cam.resize(size, true);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
