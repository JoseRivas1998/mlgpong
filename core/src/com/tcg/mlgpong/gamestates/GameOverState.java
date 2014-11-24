package com.tcg.mlgpong.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.Game;
import com.tcg.mlgpong.MyCamera;
import com.tcg.mlgpong.managers.GameStateManager;
import com.tcg.mlgpong.managers.MyInput;

public class GameOverState extends GameState {

	private int playerScore, aiScore;
	private MyCamera cam;
	private float time;
	
	private String over;
	private float oX, oY, oW, oH;
	
	private String result;
	private float rX, rY, rW, rH;
	
	private String button;
	private float bX, bY, bW, bH;
	
	private float stateTime;
	
	public GameOverState(GameStateManager gsm, int playerScore, int aiScore) {
		super(gsm);
		init(playerScore, aiScore);
	}

	@Override
	protected void init() {
		cam = new MyCamera(Game.SIZE);
		cam.translate(Game.CENTER);
		cam.update();
		time = 5;
		stateTime = 0;
	}
	
	private void init(int playerScore, int aiScore) {
		this.playerScore = playerScore;
		this.aiScore = aiScore;
		if(this.playerScore > this.aiScore) {
			Game.res.getMusic("allstar").play();
		} else if(this.playerScore < this.aiScore) {
			Game.res.getMusic("whatchasay").play();
		} else {
			Game.res.getMusic("dankstorm").play();
		}
		over = "Game Over";
		result = "";
		button = "";
		Game.res.getSound("explode").play();
	}
	
	@Override
	public void handleInput() {
		if(MyInput.anyKeyDown() && !(time > 0)) {
			if(MathUtils.randomBoolean(.15f)) {
				gsm.setState(gsm.ILLUMINATI);
			} else {
				gsm.setState(gsm.TITLE);
			}
		}
	}

	@Override
	public void update(float dt) {
		if(time >= 0) {
			time -= dt;
			float seconds = time;
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
			button = "Time Left: " + timeS;
		} else {
			button = "Press Any Key";
		}
		setText();
	}
	
	private void setText() {
		over = "Game Over";
		if(this.playerScore > this.aiScore) {
			result = "You win!";
		} else if(this.playerScore < this.aiScore) {
			result = "You lose";
		} else {
			result = "You tied";
		}
		float descent = Game.res.getFont("splash").getDescent();
		oW = Game.res.getFont("splash").getBounds(over).width;
		oH = Game.res.getFont("splash").getBounds(over).height + descent;
		oX = Game.CENTER.x - (oW * .5f);
		oY = (Game.SIZE.y * .75f) + (oH * .5f);
		rW = Game.res.getFont("splash").getBounds(result).width;
		rH = Game.res.getFont("splash").getBounds(result).height + descent;
		rX = Game.CENTER.x - (rW * .5f);
		rY = Game.CENTER.y + (rH * .5f);
		bW = Game.res.getFont("splash").getBounds(button).width;
		bH = Game.res.getFont("splash").getBounds(button).height + descent;
		bX = Game.CENTER.x - (bW * .5f);
		bY = (Game.SIZE.y * .25f) + (bH * .5f);
		
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr, float dt) {
		stateTime += dt;
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		Game.res.getFont("splash").draw(sb, over, oX, oY);
		Game.res.getFont("splash").draw(sb, result, rX, rY);
		Game.res.getFont("splash").draw(sb, button, bX, bY);
		if(time > 3.5)sb.draw(Game.getAnimationFrame(Game.nuke, stateTime), 0, 0, Game.SIZE.x, Game.SIZE.y);
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
