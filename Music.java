package application;

import java.io.File;
import java.io.IOException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music extends Thread{
	private static final String menuMusicName = "fullFUNK.mp3";
	private static final String pointMusicName = "point.mp3";
	private String actualMusicName = "";
	private Settings settings;
	private MediaPlayer mp;
	
	public Music(String string, Settings set) {
		settings = set;
		if(string.equals("menu")) {
			actualMusicName = menuMusicName;
		} else if(string.equals("point")) {
			actualMusicName = pointMusicName;
		}
		
	}
	
	public void run() {
		try {
			File tmpfile = new File(System.getProperty("user.dir") + File.separator + "media" + File.separator + actualMusicName);
			Media media = new Media(tmpfile.toURI().toString());
			MediaPlayer mp = new MediaPlayer(media);
			settings.addMediaPlayer(mp);
			mp.setVolume(settings.getVolume());
			mp.play();
			mp.setOnEndOfMedia(() -> {
				settings.removeMediaPlayer(mp);
		
			});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public String getMenuMusicName() {
		return menuMusicName;
	}
	public String getPointMusicName() {
		return pointMusicName;
	}
	public String getActualMusicName() {
		return actualMusicName;
	}
	public Settings getSettings() {
		return settings;
	}
	public MediaPlayer getMediaPlayer() {
		return mp;
	}
	
	
	public void seVolume(int i) {
		mp.setVolume(i);
	}
	
	public void setActualMusicName(String s) {
		actualMusicName = s;
	}
	public void setSettings(Settings s) {
		settings = s;
	}
	public void setMediaPlayer(MediaPlayer m) {
		mp = m;
	}
}
