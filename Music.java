package application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music extends Thread{
	private String path = "file:///C://BME//III.felev//prog3//hf//snake//music//";
	private String menuMusicName = "fullFUNK.mp3";
	//private String inGameMusicName = "";
	private String actualMusicName = "";
	protected Settings settings;
	
	protected Music(String string, Settings set) {
		settings = set;
		if(string.equals("menu")) {
			actualMusicName = menuMusicName;
		}
	}
	
	public void run() {
		if(settings.isMusic()) {
			 Media media = new Media(path + actualMusicName);
		     MediaPlayer mp = new MediaPlayer(media);
		     mp.play();
		}
	}
}
