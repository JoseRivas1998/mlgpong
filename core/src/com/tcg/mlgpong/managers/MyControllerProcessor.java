package com.tcg.mlgpong.managers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;

public class MyControllerProcessor extends ControllerAdapter {

	@Override
	public boolean buttonDown(Controller controller, int buttonIndex) {
		if(buttonIndex == 6) {
			MyInput.setKey(MyInput.ESCAPE, true);
		}
		if(buttonIndex == 7) {
			MyInput.setKey(MyInput.ENTER, true);
		}
		return true;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonIndex) {
		if(buttonIndex == 6) {
			MyInput.setKey(MyInput.ESCAPE, false);
		}
		if(buttonIndex == 7) {
			MyInput.setKey(MyInput.ENTER, false);
		}
		return true;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisIndex, float value) {
		if(axisIndex == 0) {
			if(value >= .1) {
				MyInput.setKey(MyInput.DOWN, true);
			} else {
				MyInput.setKey(MyInput.DOWN, false);
			}
			if(value <= -.1) {
				MyInput.setKey(MyInput.UP, true);
			} else {
				MyInput.setKey(MyInput.UP, false);
			}
		} 
		return true;
	}

	@Override
	public boolean povMoved(Controller controller, int povIndex, PovDirection value) {

		if(value == PovDirection.north) {
			MyInput.setKey(MyInput.UP, true);
		} else {
			MyInput.setKey(MyInput.UP, false);
		}
		if(value == PovDirection.south) {
			MyInput.setKey(MyInput.DOWN, true);
		} else {
			MyInput.setKey(MyInput.DOWN, false);
		}
		return true;
	}

}
