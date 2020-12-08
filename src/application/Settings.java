package application;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * A be�llit�sokat t�rlol� oszt�ly
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Settings {
	//Enumok
	/**
	 * A neh�zs�gi szinteket megk�l�nb�ztet� tipus
	 */
	public enum Difficulty {easy, medium, hard};
	/**
	 * A j�t�km�dokat  megk�l�nb�ztet� tipus
	 */
	public enum GameMode {singleplayer, multiplayer, vsrobot};
	
	
	//Adattagok
	/**
	 * Hanger�
	 */
	private double volume = 0.5;
	/**
	 * J�t�km�d
	 */
	private GameMode mode = GameMode.singleplayer;
	/**
	 * Kigy�k sebess�ge
	 */
	private int speed;
	/**
	 * Logikai v�ltoz�, a j�t�k fut�s�nak �llapot�t t�rolja
	 */
	private boolean gameRuns = true;	
	/**
	 * A kor�bbi eredm�nyeket t�rol� Scorebord p�ld�ny
	 */
	private Scoreboard scoreboard;
	/**
	 * Neh�zs�g
	 */
	private Difficulty dif = Difficulty.medium;
	/**
	 * Az �ppen lej�tsz�d� zen�k �s hangeffektek list�ja
	 */
	private List<MediaPlayer> mediaPlayers;

	
	
	//Konstruktor
	/**
	 * Beolvastatja a kor�bban mentett pontokat
	 */
	public Settings() {
		scoreboard = new Scoreboard("snakemultiplayer_data.dat");
		scoreboard.readData();
		setDif(Difficulty.medium);
		mediaPlayers = new ArrayList<>();
	}
	
	
	
	//Getterek
	/**
	 * Visszaadja a hanger�t
	 * @return - Hanger�
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * Visszaadja a j�t�km�dot
	 * @return - J�t�km�d
	 */
	public GameMode getMode() {
		return mode;
	}
	/**
	 * VIsszaadja a sebess�get
	 * @return - Sebess�g
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Visszaadj, hogy fut-e a j�t�k
	 * @return - Igaz, ha �ppen mozoghatnak a kigy�k
	 */
	public boolean getGameRuns() {
		return gameRuns;
	}
	
	/**
	 * Visszaadja a Scoreboardot
	 * @return - A Scoreboard
	 */
	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	/**
	 * Visszaadja a neh�zs�get
	 * @return - Neh�zs�g
	 */
	public Difficulty getDif() {
		return dif;
	}
	/**
	 * Visszaadja a neh�zs�get Stringben
	 * @return - Neh�zs�g sz�vegk�nt
	 */
	public String getDifString() {
		if(dif == Difficulty.easy)
			 return "easy";
		else if(dif == Difficulty.medium)
			return "medium";
		else if(dif == Difficulty.hard)
			return "hard";
		else
			return "don't know";
	}
	/**
	 * Visszaadja a MediaPlayerek t�mbj�t
	 * @return - A MediaPlayerek t�mbje
	 */
	public List<MediaPlayer> getMediaPlayers(){
		return mediaPlayers;
	}

	
	//Setterek
	/**
	 * Be�llitja a hanger�t
	 * @param d - Be�llitand� hanger�
	 */
	public void setVolume(double d) {
		volume = d;
	}
	/**
	 * Be�llitja a j�t�km�dot
	 * @param gm - Be�llitand� m�d
	 */
	public void setMode(GameMode gm) {
		mode = gm;
	}
	/**
	 * Be�llitja, hogy megy-e a j�t�k
	 * @param b - Be�llitand� �rt�k
	 */
	public void setGameRuns(boolean b) {
		gameRuns = b;
	}
	/**
	 * Be�llitja a neh�zs�get
	 * @param d - Be�llitand� neh�zs�g
	 */
	public void setDif(Difficulty d) {
		dif = d;
		if(d == Difficulty.easy)
			 speed = 100;
		else if(d == Difficulty.medium)
			speed = 60;
		else if(d == Difficulty.hard)
			speed = 40;
		else
			speed = 60;
	}
	
	
	
	//Alogoritmusok
	/**
	 * Hozz�ad egy MdeiaPlayert a list�hoz
	 * @param mp - Hozz�adand� MediaPlayer
	 */
	public void addMediaPlayer(MediaPlayer mp) {
		mediaPlayers.add(mp);
	}
	/**
	 * Kivesz egy MediaPlayert a list�b�l
	 * @param mp - Kit�rlend� MediaPlayer
	 */
	public void removeMediaPlayer(MediaPlayer mp) {
		mediaPlayers.remove(mp);
	}
	/**
	 * L��trehozza a be�llit�sok men�j�t
	 * @param stage - Aktu�lis stage
	 */
	public void settingsMenu(Stage stage) {
		
		Scene mainscene = stage.getScene();
		VBox setBox= new VBox(10);
		
		setBox.setAlignment(Pos.CENTER);
		setBox.setStyle("-fx-background-color: LIME");
		addButtonsBox(setBox, stage, mainscene);
		
		Scene scene = new Scene(setBox);
		stage.setScene(scene);
		stage.show();

	}
	/**
	 * L�trehozza �s kirajzolja a men�be vissztajuttat� gombot
	 * @param pane - A panel, amire ki kell rajzolni
	 * @param stage - Az aktu�lis stage
	 * @param mainscene - Az adott jelenet
	 */
	public void addButtonsBox(VBox pane, Stage stage, Scene mainscene) {
		pane.setSpacing(50);
		pane.getChildren().addAll(createDifficultyLevelPane(), createMusicSettingsPane());
		
		
	
		MyButton ex = new MyButton("BACK", "medium");
		ex.setOnAction(e-> {
			stage.setScene(mainscene);
			stage.show();
		});
		
		pane.getChildren().addAll( ex);
	}
	
	/**
	 * L�trehozza a hang be�llit�s�ra szolg�l� panelt
	 * @return - A l�trehozott panel
	 */
	public VBox createMusicSettingsPane() {
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(10);
		pane.setPrefWidth(200);
		MyText musicText = new MyText("MUSIC", "large");
		pane.getChildren().add(musicText);
		Slider slider = new Slider(0, 100, 1);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50f);
		slider.setMinWidth(200);
		slider.setMaxWidth(200);
		slider.setValue(volume * 100);
		
		pane.getChildren().add(slider);
		
		MyText t = new MyText("volume: " + (int)(volume * 100), "medium");
		slider.valueProperty().addListener( 
	             new ChangeListener<Number>() { 
	  
	            public void changed(ObservableValue <? extends Number >  
	                      observable, Number oldValue, Number newValue) 
	            { 
	            	int v = newValue.intValue();
	            	t.setText("volume: " + String.valueOf(v));
	            	double d = (double)v/100;
	            	setVolume(d);
	            	
	            	mediaPlayers.forEach((n) -> n.setVolume(d));
	            } 
	        });
		
		pane.getChildren().add(t);
		return pane;
	}

	/**
	 * L�trehozza a neh�zs�g be�llit�s�ra szolg�l� panelt
	 * @return - A l�trehozott panel
	 */
	public VBox createDifficultyLevelPane() {
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(10);
		pane.setPrefWidth(200);
		MyText difTitle= new MyText("DIFFICULTY", "large");
		MyText currentDif = new MyText("current: " + getDifString(), "medium");
		
		
		MyButton easyBut = new MyButton("EASY", "medium");
		MyButton mediumBut = new MyButton("MEDIUM", "medium");
		MyButton hardBut = new MyButton("HARD", "medium");
		
		easyBut.setOnAction(e -> {
			setDif(Difficulty.easy);
			currentDif.setText("current: " + getDifString());
		});
		mediumBut.setOnAction(e -> {
			setDif(Difficulty.medium);
			currentDif.setText("current: " + getDifString());
		});
		hardBut.setOnAction(e -> {
			setDif(Difficulty.hard);
			currentDif.setText("current: " + getDifString());
		});
		
		pane.getChildren().addAll(difTitle, currentDif, easyBut, mediumBut, hardBut);
		return pane;
	}
}
