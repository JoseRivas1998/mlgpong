package com.tcg.mlgpong.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Content {
	
	private HashMap<String, Music> music;
	private HashMap<String, Sound> sound;
	private HashMap<String, BitmapFont> font;

	private Array<Sound> sadSound;
	private Array<Sound> happySound;
	
	public Content() {
		music = new HashMap<String, Music>();
		sound = new HashMap<String, Sound>();
		font = new HashMap<String, BitmapFont>();
		sadSound = new Array<Sound>();
		happySound = new Array<Sound>();
	}
	
	public void loadMusic(String folder, String path, String key, boolean looping) {
		Music m = Gdx.audio.newMusic(Gdx.files.internal(folder + "/" + path));
		m.setLooping(looping);
		music.put(key, m);
	}
	
	public Music getMusic(String key) {
		return music.get(key);
	}
	
	public void setVolumeAll(float vol) {
		for(Object o : music.values()) {
			Music music = (Music) o;
			music.setVolume(vol);
		}
	}
	
	public void loadSound(String folder, String path, String key) {
		Sound s = Gdx.audio.newSound(Gdx.files.internal(folder + "/" + path));
		sound.put(key, s);
	}
	
	public void loadSadSound(String folder, String path) {
		Sound s = Gdx.audio.newSound(Gdx.files.internal(folder + "/" + path));
		sadSound.add(s);
	}
	
	public void loadHappySound(String folder, String path) {
		Sound s = Gdx.audio.newSound(Gdx.files.internal(folder + "/" + path));
		happySound.add(s);
	}
	
	public Sound getSound(String key) {
		return sound.get(key);
	}
	
	public Sound getRandomSadSound() {
		return sadSound.get(MathUtils.random(sadSound.size - 1));
	}
	
	public Sound getRandomHappySound() {
		return happySound.get(MathUtils.random(happySound.size - 1));
	}
	
	@SuppressWarnings("deprecation")
	public void loadBitmapFont(String folder, String path, String key, int size, Color color) {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(folder + "/" + path));
		BitmapFont bmf = gen.generateFont(size);
		bmf.setColor(color);
		font.put(key, bmf);
	}
	
	public BitmapFont getFont(String key) {
		return font.get(key);
	}
	
	public void removeAll() {
		for(Object o : music.values()) {
			Music m = (Music) o;
			m.dispose();
		}
		for(Object o : sound.values()) {
			Sound s = (Sound) o;
			s.dispose();
		}
		for(Object o : font.values()) {
			BitmapFont bmf = (BitmapFont) o;
			bmf.dispose();
		}
		for(Sound s : sadSound) {
			s.dispose();
		}
		for(Sound s : happySound) {
			s.dispose();
		}
	}
	
	public void stopSound() {
		for(Object o : sound.values()) {
			Sound s = (Sound) o;
			s.stop();
		}
		for(Sound s : sadSound) {
			s.stop();
		}
		for(Sound s : happySound) {
			s.stop();
		}
	}
	
	public void stopMusic() {
		for(Object o : music.values()) {
			Music m = (Music) o;
			m.stop();
		}
	}
	
	public void stopAllSound() {
		stopSound();
		stopMusic();
	}
}
