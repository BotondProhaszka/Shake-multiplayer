package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * A játékban hallható zenék és effektek lejátszásáért
 * és kezeléséért felelõs osztály
 * @author Prohászka Botond Bendegúz
 *
 */
public class Music extends Thread{
	//Állandó értékek
	/**
	 * A menüben hallható zene neve (és fájltipusa)
	 */
	private static final String menuMusicName = "fullFUNK.mp3";
	/**
	 * A pontszerzéskor hllható effekt neve (és fájltipusa)
	 */
	private static final String pointMusicName = "point.mp3";
	/**
	 * Az adoott pédány által lejátszsandó zene neve (és fájltipusa)
	 */
	private String actualMusicName = "";
	/**
	 * A beállitásokat tároló Settings osztály példánya
	 */
	private Settings settings;
	/**
	 * A hangot lejátszó MediaPlayer példánya
	 */
	private MediaPlayer mp;
	
	
	
	//Konstruktor
	/**
	 * Beállitja a fáfjlnevet és a beállitások példányát
	 * @param string - A lejátszandó zene megkülönböztetésére szolgáló string,
	 	ezzel lehet beéllitani, hogy az adot pédány mit játsszon le.
	 * 				Értéke lehet: 	- "menu":  A menüben lejátszandó zene
	 * 								- "point":  A pontszerzés effektje
	 * @param set - A Settings példánya
	 */
	public Music(String string, Settings set) {
		settings = set;
		if(string.equals("menu")) {
			actualMusicName = menuMusicName;
		} else if(string.equals("point")) {
			actualMusicName = pointMusicName;
		}
		
	}
	
	//Run algoritmus
	/**
	 * A szál elinditása során ez a függvény keresi meg és nyitja meg a filet
	 * és inditja el a hang lejátszását 
	 */
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
	
	
	
	//Getterek
	/**
	 * Visszaadja a menüben lejátszandó zene nevét
	 * @return - A hagfile neve
	 */
	public String getMenuMusicName() {
		return menuMusicName;
	}
	/**
	 * Visszaadja a pontszerzéskor hallható effekt nevét
	 * @return - Az effekt-hangfile neve
	 */
	public String getPointMusicName() {
		return pointMusicName;
	}
	/**
	 * Visszaadja az aktuális hangdfile nevét
	 * @return - Az aktuális név
	 */
	public String getActualMusicName() {
		return actualMusicName;
	}
	/**
	 * Visszaajda a Settings osztály példányát 
	 * @return - A példány
	 */
	public Settings getSettings() {
		return settings;
	}
	/**
	 * Visszaadja a MediaPlayert
	 * @return - A MediaPlayer
	 */
	public MediaPlayer getMediaPlayer() {
		return mp;
	}
	
	
	
	//Setterek
	/**
	 * Beéllitja a hangerõt
	 * @param i - 
	 */
	public void seVolume(int i) {
		mp.setVolume(i);
	}
	/**
	 * Beállitja az aktuális hangfile nevét 
	 * @param s - A beállitandó érték
	 */
	public void setActualMusicName(String s) {
		actualMusicName = s;
	}
	/**
	 * Beállitja a Settings osztály példányát
	 * @param s - A beállitandó objektum
	 */
	public void setSettings(Settings s) {
		settings = s;
	}
	/**
	 * Beállitja a MediaPlayert
	 * @param m - A beállitandó objektum
	 */
	public void setMediaPlayer(MediaPlayer m) {
		mp = m;
	}
}
