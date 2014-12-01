package com.tcg.mlgpong.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.Game;
import com.tcg.mlgpong.MyCamera;
import com.tcg.mlgpong.managers.GameStateManager;
import com.tcg.mlgpong.managers.MyInput;

public class TitleState extends GameState {
	
	private float stateTime;
	
	private MyCamera cam;
	
	private float tX, tY, tW, tH;
	private float eX, eY, eW, eH;
	private float escX, escY, escW, escH;
	private float jX, jY, jH;
	
	private float a1X, a1Y, a1W, a1H, a2X, a2Y, a2W, a2H;
	private float a3X, a3Y, a3W, a3H, a4X, a4Y, a4W, a4H, a5X, a5Y, a5W, a5H;
	private float mlgX, mlgY, mlgW, mlgH;
	
	private String title, enter, escape, jose;

	public TitleState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		
		stateTime = 0;
		
		cam = new MyCamera(Game.SIZE);
		cam.translate(Game.CENTER);
		cam.update();

		setTitleValues();
		setTextValues();
		setAnimValues();
		
		Game.res.getMusic("sandstorm").play();
	}
	
	private void setAnimValues() {
		a1W = Game.getAnimationFrame(Game.snoop, stateTime).getRegionWidth() * .5f;
		a1H = Game.getAnimationFrame(Game.snoop, stateTime).getRegionHeight() * .5f;
		a1X = MathUtils.random((Game.SIZE.x - a1W));
		a1Y = MathUtils.random((Game.SIZE.y - a1H));
		
		a2W = Game.getAnimationFrame(Game.frog, stateTime).getRegionWidth() * .5f;
		a2H = Game.getAnimationFrame(Game.frog, stateTime).getRegionHeight() * .5f;
		a2X = MathUtils.random((Game.SIZE.x - a2W));
		a2Y = MathUtils.random((Game.SIZE.y - a2H));
		
		a3W = Game.getAnimationFrame(Game.shrek, stateTime).getRegionWidth() * .5f;
		a3H = Game.getAnimationFrame(Game.shrek, stateTime).getRegionHeight() * .5f;
		a3X = MathUtils.random((Game.SIZE.x - a3W));
		a3Y = MathUtils.random((Game.SIZE.y - a3H));
		
		a4W = Game.getAnimationFrame(Game.kid, stateTime).getRegionWidth() * .5f;
		a4H = Game.getAnimationFrame(Game.kid, stateTime).getRegionHeight() * .5f;
		a4X = MathUtils.random((Game.SIZE.x - a4W));
		a4Y = MathUtils.random((Game.SIZE.y - a4H));
		
		a5W = Game.getAnimationFrame(Game.spook, stateTime).getRegionWidth() * .5f;
		a5H = Game.getAnimationFrame(Game.spook, stateTime).getRegionHeight() * .5f;
		a5X = MathUtils.random((Game.SIZE.x - a5W));
		a5Y = MathUtils.random((Game.SIZE.y - a5H));
	}

	@Override
	public void handleInput() {
		if(MyInput.isPressed(MyInput.ENTER)) gsm.setState(gsm.PLAY);
		if(MyInput.isPressed(MyInput.ESCAPE)) Gdx.app.exit();

	}

	@Override
	public void update(float dt) {
		Game.flicker = false;
		setTitleValues();
		setTextValues();
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		stateTime += dt;
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		drawAnimations(sb, stateTime);
		drawText(sb);
		sb.end();
	}
	
	private void drawAnimations(SpriteBatch sb, float stateTime) {
		sb.draw(Game.getAnimationFrame(Game.snoop, stateTime), a1X, a1Y, a1W * .5f, a1H * .5f, a1W, a1H, 1, 1, MathUtils.random(360));
		sb.draw(Game.getAnimationFrame(Game.frog, stateTime), a2X, a2Y, a2W * .5f, a2H * .5f, a2W, a2H, 1, 1, MathUtils.random(360));
		sb.draw(Game.getAnimationFrame(Game.shrek, stateTime), a3X, a3Y, a3W * .5f, a3H * .5f, a3W, a3H, 1, 1, MathUtils.random(360));
		sb.draw(Game.getAnimationFrame(Game.kid, stateTime), a4X, a4Y, a4W * .5f, a4H * .5f, a4W, a4H, 1, 1, MathUtils.random(360));
		sb.draw(Game.getAnimationFrame(Game.spook, stateTime), a5X, a5Y, a5W * .5f, a5H * .5f, a5W, a5H, 1, 1, MathUtils.random(360));
	}
	
	private void setTitleValues() {
		float descent;
		descent = Game.res.getFont("splash").getDescent();
		title = "P0ng";
		mlgW = Game.getAnimationFrame(Game.mlg, stateTime).getRegionWidth();
		mlgH = Game.getAnimationFrame(Game.mlg, stateTime).getRegionHeight();
		tW = Game.res.getFont("splash").getBounds(title).width;
		tH = Game.res.getFont("splash").getBounds(title).height + descent;
		float totalW = mlgW + 20 + tW;
		mlgX = Game.CENTER.x - (totalW * .5f);
		mlgY = (Game.SIZE.y * .75f) - (mlgH * .5f);
		tX = mlgX + mlgW + 20;
		tY = (Game.SIZE.y * .75f) + (tH * .5f);
	}
	
	private void setTextValues() {
		enter = "Press 3nter to Begin";
		escape = "Press 3scape to 3xit";
		jose = "Created by Jose Rodriguez-Rivas";
		float descent;
		descent = Game.res.getFont("splash").getDescent();
		eW = Game.res.getFont("splash").getBounds(enter).width;
		eH = Game.res.getFont("splash").getBounds(enter).height - descent;
		eX = Game.CENTER.x - (eW * .5f);
		eY = Game.CENTER.y + (eH * .5f);
		escW = Game.res.getFont("splash").getBounds(escape).width;
		escH = Game.res.getFont("splash").getBounds(escape).height - descent;
		escX = Game.CENTER.x - (escW * .5f);
		escY = (Game.SIZE.y * .25f) + (escH * .5f);
		jH = Game.res.getFont("small").getBounds(jose).height - descent;
		jX = 0;
		jY = jH;
	}
	
	private void drawText(SpriteBatch sb) {
		sb.draw(Game.getAnimationFrame(Game.mlg, stateTime), mlgX, mlgY);
		Game.res.getFont("splash").draw(sb, title, tX, tY);
		Game.res.getFont("splash").draw(sb, enter, eX, eY);
		Game.res.getFont("splash").draw(sb, escape, escX, escY);
		Game.res.getFont("small").draw(sb, jose, jX, jY);
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
