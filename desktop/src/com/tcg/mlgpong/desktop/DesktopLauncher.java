package com.tcg.mlgpong.desktop;

import java.awt.Toolkit;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tcg.mlgpong.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		config.fullscreen = true;
//		config.width = 800;
//		config.height = 600;
//		config.fullscreen = false;
		String p = "icon/";
		config.addIcon(p + "win.png", Files.FileType.Internal);
		config.addIcon(p + "lin.png", Files.FileType.Internal);
		config.addIcon(p + "mac.png", Files.FileType.Internal);
		new LwjglApplication(new Game(), config);
	}
}
