package com.tcg.mlgpong.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tcg.mlgpong.Game;

public class Ball extends Entity {
	
	private float speed, radians, degrees, calcSpeed;
	private Vector2 vel;
	private Vector2 spawn;
	
	private Vector2 center;
	private Array<Vector2> hitmarkers;
	
	private TextureRegion currentFrame;
	private TextureRegion sanic;
	private Texture sanT;
	
	private float stateTime;
	
	
	private int randomAnimIndex;
	private final int numAnims = 6;
	
	public static final int BOUNCE_TOP_BOTTOM = 0;
	public static final int BOUNCE_ALL_SIDES = 1;
	
	private int bounceMode;
	
	private float time, timer;	
	
	public Ball(Vector2 position, float width, float height, int bounceMode) {
		bounds = new Rectangle(position.x, position.y, width, height);
		bounds.x = position.x;
		bounds.y = position.y;
		spawn = new Vector2(position);
		this.bounceMode = bounceMode;
		init();
	}

	public Ball(float x, float y, float width, float height, int bounceMode) {
		bounds = new Rectangle(x, y, width, height);
		spawn = new Vector2(x, y);
		this.bounceMode = bounceMode;
		init();
	}

	@Override
	protected void init() {
		vel = new Vector2();
		center = new Vector2();
		hitmarkers = new Array<Vector2>();
		time = 0;
		timer = 1;
		sanT = new Texture("entities/sanic.png");
		sanic = new TextureRegion(sanT);
		reset();
		
	}
	
	@Override
	public void draw(ShapeRenderer sr, SpriteBatch sb, float dt) {
		if(calcSpeed > 25) {
			if(!Game.res.getMusic("sanic").isPlaying()) Game.res.getMusic("sanic").play();
			currentFrame = sanic;
		} else {
			Game.res.getMusic("sanic").stop();
			animate(dt);
		}
		sb.draw(currentFrame, bounds.x, bounds.y, bounds.width * .5f, bounds.height * .5f, getWidth(), getHeight(), 1, 1, degrees);
	}
	
	private void animate(float dt) {
		stateTime += dt;
		switch(randomAnimIndex) {
		case 0:
			currentFrame = Game.getAnimationFrame(Game.snoop, stateTime);
			break;
		case 1:
			currentFrame = Game.getAnimationFrame(Game.frog, stateTime);
			break;
		case 2:
			currentFrame = Game.getAnimationFrame(Game.shrek, stateTime);
			break;
		case 3:
			currentFrame = Game.getAnimationFrame(Game.kid, stateTime);
			break;
		case 4:
			currentFrame = Game.getAnimationFrame(Game.spook, stateTime);
			break;
		case 5:
			currentFrame = Game.getAnimationFrame(Game.mlg, stateTime);
			break;
		}
	}
	
	public void update(float dt) {
		if(bounceMode == Ball.BOUNCE_ALL_SIDES) {
			bounceAllSides();
		}
		if(bounceMode == Ball.BOUNCE_TOP_BOTTOM) {
			bounceTopBottom();
		}
		bounds.x += vel.x;
		bounds.y += vel.y;
		center.x = bounds.x + (bounds.width * .5f);
		center.y = bounds.y + (bounds.height * .5f);
		float trig = (((float) (Math.atan(Math.abs(((bounds.y + vel.y) - bounds.y)) / Math.abs(((bounds.x + vel.x) - bounds.x))))) * MathUtils.radiansToDegrees);
		if(vel. y < 0 && vel.x > 0) {
			degrees = 270 - (trig);
		} else if(vel.y < 0 && vel.x < 0) {
			degrees = (trig) + 90;
		} else if(vel.y > 0 && vel.x > 0) {
			degrees = (trig) + 270;
		} else if(vel.y > 0 && vel.x < 0) {
			degrees = 90 - (trig);
		}
		time += dt;
		if(time >= timer) {
			if(hitmarkers.size > 0) {
				hitmarkers.removeIndex(0);
			}
			time = 0;
		}
		calcSpeed = (float) Math.sqrt(((Math.pow(((bounds.x + vel.x) - bounds.x), 2)) + (Math.pow(((bounds.y + vel.y) - bounds.y), 2))));
	}
	
	private void bounceAllSides() {
		if(getX() >= Game.SIZE.x - getWidth()) {
			bounds.x--;
			bounceX();
		}
		if(getX() <= 0) {
			bounds.x++;
			bounceX();
		}
		if(getY() >= Game.SIZE.y - getHeight()) {
			bounds.y--;
			bounceY();
		}
		if(getY() <= 0) {
			bounds.y++;
			bounceY();
		}
	
	}
	
	private void bounceTopBottom() {
		if(getX() >= Game.SIZE.x - getWidth() + 50) {
			reset();
		}
		if(getX() <= -50) {
			reset();
		}
		if(getY() >= Game.SIZE.y - getHeight()) {
			bounds.y--;
			bounceY();
		}
		if(getY() <= 0) {
			bounds.y++;
			bounceY();
		}
	
	
	}
	
	public void collisions(Paddle p) {
		if(collidingWith(p)) {
			if(p.getSide() == Paddle.LEFT) {
				bounds.x = p.getX() + p.getWidth() + 1;
				bounceX();
			}
			if(p.getSide() == Paddle.RIGHT) {
				bounds.x = p.getX() - getWidth() - 1;
				bounceX();
			}
		}
	}
	
	public void bounceX() {
		Game.res.getSound("hitmarker").play();
		if(bounds.x < Game.CENTER.x) {
			hitmarkers.add(new Vector2(center));
		} else {
			hitmarkers.add(new Vector2(getX() - getWidth(), getY()));
		}
		vel.x *= -1;
		if(vel.x > 0) {
			vel.x++;
		} else {
			vel.x--;
		}
	}
	
	public void bounceY() {
		Game.res.getSound("hitmarker").play();
		if(bounds.y < Game.CENTER.y) {
			hitmarkers.add(new Vector2(center));
		} else {
			hitmarkers.add(new Vector2(getX(), getY() - getHeight()));
		}
		vel.y *= -1;
		if(vel.y > 0) {
			vel.y++;
		} else {
			vel.y--;
		}
	}
	
	public void reset() {
		hitmarkers.clear();
		speed = 10;
		randomAnimIndex = MathUtils.random(numAnims - 1);
		do {
			radians = MathUtils.random(2 * MathUtils.PI);
		} while(Math.abs(MathUtils.cos(radians) * speed) < 1.5);
		vel.set(0, 0);
		bounds.x = spawn.x;
		bounds.y  = spawn.y;
		vel.x = MathUtils.cos(radians) * speed;
		vel.y = MathUtils.sin(radians) * speed;
	}

	public Vector2 getSpawn() {
		return spawn;
	}

	public void setSpawn(Vector2 spawn) {
		this.spawn = spawn;
	}

	public Array<Vector2> getHitmarkers() {
		return hitmarkers;
	}

}
