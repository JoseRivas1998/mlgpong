package com.tcg.mlgpong.entities;

import com.tcg.mlgpong.managers.MyInput;

public class Player extends Paddle {

	public Player(float width, float height, int side, String texPath) {
		super(width, height, side, texPath);
	}

	@Override
	public void behave() {
		if(MyInput.isDown(MyInput.UP)) {
			yVel = 10;
		} else if(MyInput.isDown(MyInput.DOWN)) {
			yVel = -10;
		} else {
			yVel = 0;
		}
	}

}
