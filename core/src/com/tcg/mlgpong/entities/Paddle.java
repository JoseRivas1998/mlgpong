package com.tcg.mlgpong.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.tcg.mlgpong.Game;

public abstract class Paddle extends Entity {

	protected int side;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	protected float yVel;
	
	protected Texture tex;
	
	public Paddle(float width, float height, int side, String texPath) {
		float x, y;
		this.side = side;
		x = 0;
		y = Game.CENTER.y - (height * .5f);
		yVel = 0;
		bounds = new Rectangle(x, y, width, height);
		tex = new Texture(texPath);
		resetX(width);
		init();
	}
	
	@Override
	protected void init() {}

	@Override
	public void draw(ShapeRenderer sr, SpriteBatch sb, float dt) {
		sb.draw(tex, getX(), getY(), getWidth(), getHeight());
	}
	
	public void update() {
		behave();
		bounds.y += yVel;
		if(bounds.y > Game.SIZE.y - bounds.height) {
			bounds.y = Game.SIZE.y - bounds.height;
		}
		if(bounds.y < 0) {
			bounds.y = 0;
		}
	}

	public int getSide() {
		return side;
	}

	public abstract void behave();
	
	public void resetX(float width) {
		if(this.side == Paddle.LEFT) {
			bounds.x = (Game.SIZE.x * .15f) - (width * .5f);
		}
		if(this.side == Paddle.RIGHT) {
			bounds.x = (Game.SIZE.x * .85f) - (width * .5f);
		}
	}
	
	public void dispose() {
		tex.dispose();
	}
	
}
