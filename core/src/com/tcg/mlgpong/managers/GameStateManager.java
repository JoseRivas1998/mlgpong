package com.tcg.mlgpong.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tcg.mlgpong.Game;
import com.tcg.mlgpong.gamestates.*;

public class GameStateManager {

	SpriteBatch sb;
	ShapeRenderer sr;
	
	GameState state;
	
	public final int SPLASH = 0;
	public final int TITLE = 1;
	public final int PLAY = 2;
	public final int ILLUMINATI = 3;
	
	public GameStateManager() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		setState(SPLASH);
//		setState(ILLUMINATI);
	}
	
	public void setState(int newState) {
		Game.res.stopAllSound();
		if(state != null) state.dispose();
		if(newState == SPLASH) {
			state = new SplashState(this);
		} 
		if(newState == TITLE) {
			state = new TitleState(this);
		} 
		if(newState == PLAY) {
			state = new PlayState(this);
		}
		if(newState == ILLUMINATI) {
			state = new IlluminatiState(this);
		}
	}
	
	public void gameOver(int pScore, int aiScore) {
		Game.res.stopAllSound();
		if(state != null) state.dispose();
		state = new GameOverState(this, pScore, aiScore);
	}
	
	public void update(float dt) {
		state.update(dt);
	}
	
	public void draw(float dt) {
		state.draw(sb, sr, dt);
	}
	
	public void handleInput() {
		state.handleInput();
	}
	
	public void resize(Vector2 size) {
		state.resize(size);
	}
	
	public void dispose() {
		state.dispose();
		sb.dispose();
		sr.dispose();
	}

}
