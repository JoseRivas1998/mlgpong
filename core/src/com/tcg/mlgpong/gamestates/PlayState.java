package com.tcg.mlgpong.gamestates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.Game;
import com.tcg.mlgpong.MyCamera;
import com.tcg.mlgpong.entities.*;
import com.tcg.mlgpong.managers.GameStateManager;
import com.tcg.mlgpong.managers.MyInput;

public class PlayState extends GameState {
	
	private MyCamera cam;
	private Ball b;
	private Texture hit;
	private Paddle player, opponent;
	private int rightScore, leftScore, rightStreak, leftStreak;
	private float time;
	private boolean nukeP;
	
	private float a1X, a1Y, a1W, a1H, a2X, a2Y, a2W, a2H;
	private float a3X, a3Y, a3W, a3H, a4X, a4Y, a4W, a4H, a5X, a5Y, a5W, a5H;
	private float a6X, a6Y, a6W, a6H;
	private float stateTime, time1, time2, wallyTime, time3, gunTime;
	private boolean wally, gun;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		b = new Ball(Game.CENTER, 50, 50, Ball.BOUNCE_TOP_BOTTOM);
		player = new Player(50, 100, Paddle.LEFT, "entities/dew.png");
		opponent = new AI(50, 100, Paddle.RIGHT, "entities/doritos.png", b);
		hit = new Texture("entities/hitmarker.png");
		cam = new MyCamera(Game.SIZE);
		cam.translate(Game.CENTER);
		cam.update();
		time = 300;
//		time = 1;
		nukeP = false;
		stateTime = 0;
		time1 = 1;
		wallyTime = Game.wow.getAnimationDuration();
		gunTime = Game.gun.getAnimationDuration();
		wally = false;
		gun = false;
		time3 = gunTime;
		time2 = wallyTime;
		rightStreak = 0;
		leftStreak = 0;
		setAnimValues();
		Game.res.getSound("airhorn").play(); 
	}
	
	private void setAnimValues() {
		float left = 0;
		float right = 0;
		
		a1W = Game.getAnimationFrame(Game.snoop, stateTime).getRegionWidth() * .5f;
		a1H = Game.getAnimationFrame(Game.snoop, stateTime).getRegionHeight() * .5f;
		a1X = MathUtils.random((Game.SIZE.x - a1W));
		a1Y = MathUtils.random((Game.SIZE.y - a1H));
		if(a1X > Game.CENTER.x) {
			right++;
		} else {
			left++;
		}
		
		a2W = Game.getAnimationFrame(Game.frog, stateTime).getRegionWidth() * .5f;
		a2H = Game.getAnimationFrame(Game.frog, stateTime).getRegionHeight() * .5f;
		if(right > left) {
			a2X = MathUtils.random((Game.CENTER.x - a2W));
			left++;
		} else {
			a2X = MathUtils.random((Game.CENTER.x), (Game.SIZE.x - a2W));
			right++;
		}
		a2Y = MathUtils.random((Game.SIZE.y - a2H));
		
		a3W = Game.getAnimationFrame(Game.shrek, stateTime).getRegionWidth() * .5f;
		a3H = Game.getAnimationFrame(Game.shrek, stateTime).getRegionHeight() * .5f;
		if(right > left) {
			a3X = MathUtils.random((Game.CENTER.x - a3W));
			left++;
		} else {
			a3X = MathUtils.random((Game.CENTER.x), (Game.SIZE.x - a3W));
			right++;
		}
		a3Y = MathUtils.random((Game.SIZE.y - a3H));
		
		a4W = Game.getAnimationFrame(Game.kid, stateTime).getRegionWidth() * .5f;
		a4H = Game.getAnimationFrame(Game.kid, stateTime).getRegionHeight() * .5f;
		if(right > left) {
			a4X = MathUtils.random((Game.CENTER.x - a4W));
			left++;
		} else {
			a4X = MathUtils.random((Game.CENTER.x), (Game.SIZE.x - a4W));
			right++;
		}
		a4Y = MathUtils.random((Game.SIZE.y - a4H));
		
		a5W = Game.getAnimationFrame(Game.spook, stateTime).getRegionWidth() * .5f;
		a5H = Game.getAnimationFrame(Game.spook, stateTime).getRegionHeight() * .5f;
		if(right > left) {
			a5X = MathUtils.random((Game.CENTER.x - a5W));
			left++;
		} else {
			a5X = MathUtils.random((Game.CENTER.x), (Game.SIZE.x - a5W));
			right++;
		}
		a5Y = MathUtils.random((Game.SIZE.y - a5H));
		
		a6W = Game.getAnimationFrame(Game.mlg, stateTime).getRegionWidth();
		a6H = Game.getAnimationFrame(Game.mlg, stateTime).getRegionHeight();
		if(right > left) {
			a6X = MathUtils.random((Game.CENTER.x - a6W));
			left++;
		} else {
			a6X = MathUtils.random((Game.CENTER.x), (Game.SIZE.x - a6W));
			right++;
		}
		a6Y = MathUtils.random((Game.SIZE.y - a6H));
	}

	private void setTimer(float dt) {
		time -= dt;
		if(time <= 0) {
			gsm.gameOver(leftScore, rightScore);
		}
		if(time <= 5.546 && !nukeP) {
			nukeP = true;
			Game.res.getSound("nuke").play();
		}
	}

	@Override
	public void handleInput() {
		if(MyInput.isPressed(MyInput.ESCAPE)) gsm.setState(gsm.TITLE);
	}

	@Override
	public void update(float dt) {
		b.update(dt);
		player.update();
		opponent.update();
		b.collisions(player);
		b.collisions(opponent);
		scoreCheck();
		setTimer(dt);
		time1 += dt;
		time2 += dt;
		time3 += dt;
		wally = time2 < wallyTime;
		gun = (time3 < gunTime) && !wally;
		Game.flicker = (time1 < 1) && !wally;
	}
	
	private void scoreCheck() {
		if(b.getX() <= -50) {
			rightScore++;
			rightStreak++;
			leftStreak = 0;
			Game.res.getRandomSadSound().play();
			setAnimValues();
		}
		if(b.getX() >= Game.SIZE.x - b.getWidth() + 50) {
			leftScore++;
			leftStreak++;
			rightStreak = 0;
			if(MathUtils.randomBoolean()) {
				time1 = 0;
				time2 = wallyTime;
				time3 = gunTime;
			} else if(MathUtils.randomBoolean()) {
				time1 = 1;
				time2 = 0;
				time3 = gunTime;
			} else {
				time1 = 1;
				time2 = wallyTime;
				time3 = 0;
			}
			if(leftStreak == 3) {
				Game.res.getSound("triple").play();
			} else if(leftScore == 21) {
				Game.res.getSound("21").play();
			} else {
				Game.res.getRandomHappySound().play();
			}
			setAnimValues();
		}
		if(leftStreak == 25 || rightStreak == 25) {
			time = 7;
		}
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		
		stateTime += dt;
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		drawAnimations(sb, stateTime);
		sb.end();
		
		sr.begin(ShapeType.Filled);
		sr.setProjectionMatrix(cam.combined);
		drawCenter(sr);
		sr.end();
		
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		for(Vector2 v : b.getHitmarkers()) {
			sb.draw(hit, v.x, v.y);
		}
		player.draw(sr, sb, dt);
		opponent.draw(sr, sb, dt);
		b.draw(sr, sb, dt);
		drawScore(sb);
		drawTime(sb);
		if(gun) sb.draw(Game.getAnimationFrame(Game.gun, time3), 0, 0, Game.SIZE.x, Game.SIZE.y);
		sb.end();
	}
	
	private void drawAnimations(SpriteBatch sb, float stateTime) {
		if(wally) sb.draw(Game.getAnimationFrame(Game.wow, time2), 0, 0, Game.SIZE.x, Game.SIZE.y);
		sb.draw(Game.getAnimationFrame(Game.snoop, stateTime), a1X, a1Y, a1W, a1H);
		sb.draw(Game.getAnimationFrame(Game.frog, stateTime), a2X, a2Y, a2W, a2H);
		sb.draw(Game.getAnimationFrame(Game.shrek, stateTime), a3X, a3Y, a3W, a3H);
		sb.draw(Game.getAnimationFrame(Game.kid, stateTime), a4X, a4Y, a4W, a4H);
		sb.draw(Game.getAnimationFrame(Game.spook, stateTime), a5X, a5Y, a5W, a5H);
		sb.draw(Game.getAnimationFrame(Game.mlg, stateTime), a6X, a6Y, a6W, a6H);
	}
	
	private void drawCenter(ShapeRenderer sr) {
		int numRects;
		numRects = (int) (Game.SIZE.y / 10);
		for(int i = 0; i < numRects; i++) {
			sr.rect(Game.CENTER.x - 5, i * 10, 10, 5);
		}
	}

	private void drawScore(SpriteBatch sb) {
		String right, left;
		left = "" + leftScore;
		right = "" + rightScore;
		float lX, lY, lW, lH, rX, rY, rW, rH;
		lW = Game.res.getFont("main").getBounds(left).width;
		lH = Game.res.getFont("main").getBounds(left).height - Game.res.getFont("main").getDescent();
		rW = Game.res.getFont("main").getBounds(right).width;
		rH = Game.res.getFont("main").getBounds(right).height - Game.res.getFont("main").getDescent();
		lX = (Game.SIZE.x * .25f) - (lW * .5f);
		lY = (Game.SIZE.y * .75f) + (lH * .5f);
		rX = (Game.SIZE.x * .75f) - (rW * .5f);
		rY = (Game.SIZE.y * .75f) + (rH * .5f);
		Game.res.getFont("main").draw(sb, left, lX, lY);
		Game.res.getFont("main").draw(sb, right, rX, rY);
	}
	
	private void drawTime(SpriteBatch sb) {
		float seconds = this.time;
		float minutes = seconds / 60;
		float remainingSeconds = seconds % 60;
		String timeS;
		if(remainingSeconds >= 10) {
			timeS = (int) minutes + ":" + (int) remainingSeconds;
		} else {
			if(seconds < 0) {
				timeS = "-" + (int) minutes + ":0" + (int) Math.abs(remainingSeconds);
			} else {
				timeS = (int) minutes + ":0" + (int) remainingSeconds;
			}
		}
		timeS = "Time Left: " + timeS;
		float sy = Game.SIZE.y - (Game.res.getFont("main").getAscent());
		Game.res.getFont("main").draw(sb, timeS, 0, sy);
	}

	@Override
	public void resize(Vector2 size) {
		cam.resize(size, true);
		b.setSpawn(Game.CENTER);
		player.resetX(player.getWidth());
		opponent.resetX(opponent.getWidth());
	}

	@Override
	public void dispose() {
		hit.dispose();
		player.dispose();
		opponent.dispose();
	}

}
