package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * A j�t�kban hallhat� zen�k �s effektek lej�tsz�s��rt
 * �s kezel�s��rt felel�s oszt�ly
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Music extends Thread{
	//�lland� �rt�kek
	/**
	 * A men�ben hallhat� zene neve (�s f�jltipusa)
	 */
	private static final String menuMusicName = "fullFUNK.mp3";
	/**
	 * A pontszerz�skor hllhat� effekt neve (�s f�jltipusa)
	 */
	private static final String pointMusicName = "point.mp3";
	/**
	 * Az adoott p�d�ny �ltal lej�tszsand� zene neve (�s f�jltipusa)
	 */
	private String actualMusicName = "";
	/**
	 * A be�llit�sokat t�rol� Settings oszt�ly p�ld�nya
	 */
	private Settings settings;
	/**
	 * A hangot lej�tsz� MediaPlayer p�ld�nya
	 */
	private MediaPlayer mp;
	
	
	
	//Konstruktor
	/**
	 * Be�llitja a f�fjlnevet �s a be�llit�sok p�ld�ny�t
	 * @param string - A lej�tszand� zene megk�l�nb�ztet�s�re szolg�l� string,
	 	ezzel lehet be�llitani, hogy az adot p�d�ny mit j�tsszon le.
	 * 				�rt�ke lehet: 	- "menu":  A men�ben lej�tszand� zene
	 * 								- "point":  A pontszerz�s effektje
	 * @param set - A Settings p�ld�nya
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
	 * A sz�l elindit�sa sor�n ez a f�ggv�ny keresi meg �s nyitja meg a filet
	 * �s inditja el a hang lej�tsz�s�t 
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
	 * Visszaadja a men�ben lej�tszand� zene nev�t
	 * @return - A hagfile neve
	 */
	public String getMenuMusicName() {
		return menuMusicName;
	}
	/**
	 * Visszaadja a pontszerz�skor hallhat� effekt nev�t
	 * @return - Az effekt-hangfile neve
	 */
	public String getPointMusicName() {
		return pointMusicName;
	}
	/**
	 * Visszaadja az aktu�lis hangdfile nev�t
	 * @return - Az aktu�lis n�v
	 */
	public String getActualMusicName() {
		return actualMusicName;
	}
	/**
	 * Visszaajda a Settings oszt�ly p�ld�ny�t 
	 * @return - A p�ld�ny
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
	 * Be�llitja a hanger�t
	 * @param i - 
	 */
	public void seVolume(int i) {
		mp.setVolume(i);
	}
	/**
	 * Be�llitja az aktu�lis hangfile nev�t 
	 * @param s - A be�llitand� �rt�k
	 */
	public void setActualMusicName(String s) {
		actualMusicName = s;
	}
	/**
	 * Be�llitja a Settings oszt�ly p�ld�ny�t
	 * @param s - A be�llitand� objektum
	 */
	public void setSettings(Settings s) {
		settings = s;
	}
	/**
	 * Be�llitja a MediaPlayert
	 * @param m - A be�llitand� objektum
	 */
	public void setMediaPlayer(MediaPlayer m) {
		mp = m;
	}
}
