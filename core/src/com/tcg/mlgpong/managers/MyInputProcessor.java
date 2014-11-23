package com.tcg.mlgpong.managers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter {

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.UP) {
			MyInput.setKey(MyInput.UP, true);
		}
		if(keycode == Keys.DOWN) {
			MyInput.setKey(MyInput.DOWN, true);
		}
		if(keycode == Keys.LEFT) {
			MyInput.setKey(MyInput.LEFT, true);
		}
		if(keycode == Keys.RIGHT) {
			MyInput.setKey(MyInput.RIGHT, true);
		}
		if(keycode == Keys.W) {
			MyInput.setKey(MyInput.W, true);
		}
		if(keycode == Keys.A) {
			MyInput.setKey(MyInput.A, true);
		}
		if(keycode == Keys.S) {
			MyInput.setKey(MyInput.S, true);
		}
		if(keycode == Keys.D) {
			MyInput.setKey(MyInput.D, true);
		}
		if(keycode == Keys.ENTER) {
			MyInput.setKey(MyInput.ENTER, true);
		}
		if(keycode == Keys.ESCAPE) {
			MyInput.setKey(MyInput.ESCAPE, true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.UP) {
			MyInput.setKey(MyInput.UP, false);
		}
		if(keycode == Keys.DOWN) {
			MyInput.setKey(MyInput.DOWN, false);
		}
		if(keycode == Keys.LEFT) {
			MyInput.setKey(MyInput.LEFT, false);
		}
		if(keycode == Keys.RIGHT) {
			MyInput.setKey(MyInput.RIGHT, false);
		}
		if(keycode == Keys.W) {
			MyInput.setKey(MyInput.W, false);
		}
		if(keycode == Keys.A) {
			MyInput.setKey(MyInput.A, false);
		}
		if(keycode == Keys.S) {
			MyInput.setKey(MyInput.S, false);
		}
		if(keycode == Keys.D) {
			MyInput.setKey(MyInput.D, false);
		}
		if(keycode == Keys.ENTER) {
			MyInput.setKey(MyInput.ENTER, false);
		}
		if(keycode == Keys.ESCAPE) {
			MyInput.setKey(MyInput.ESCAPE, false);
		}
		return true;
	}

}
