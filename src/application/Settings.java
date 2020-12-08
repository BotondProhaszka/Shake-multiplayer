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
 * A beállitásokat tárloló osztály
 * @author Prohászka Botond Bendegúz
 *
 */
public class Settings {
	//Enumok
	/**
	 * A nehézségi szinteket megkülönböztetõ tipus
	 */
	public enum Difficulty {easy, medium, hard};
	/**
	 * A játékmódokat  megkülönböztetõ tipus
	 */
	public enum GameMode {singleplayer, multiplayer, vsrobot};
	
	
	//Adattagok
	/**
	 * Hangerõ
	 */
	private double volume = 0.5;
	/**
	 * Játékmód
	 */
	private GameMode mode = GameMode.singleplayer;
	/**
	 * Kigyók sebessége
	 */
	private int speed;
	/**
	 * Logikai változó, a játék futásának állapotát tárolja
	 */
	private boolean gameRuns = true;	
	/**
	 * A korábbi eredményeket tároló Scorebord példány
	 */
	private Scoreboard scoreboard;
	/**
	 * Nehézség
	 */
	private Difficulty dif = Difficulty.medium;
	/**
	 * Az éppen lejátszódó zenék és hangeffektek listája
	 */
	private List<MediaPlayer> mediaPlayers;

	
	
	//Konstruktor
	/**
	 * Beolvastatja a korábban mentett pontokat
	 */
	public Settings() {
		scoreboard = new Scoreboard("snakemultiplayer_data.dat");
		scoreboard.readData();
		setDif(Difficulty.medium);
		mediaPlayers = new ArrayList<>();
	}
	
	
	
	//Getterek
	/**
	 * Visszaadja a hangerõt
	 * @return - Hangerõ
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * Visszaadja a játékmódot
	 * @return - Játékmód
	 */
	public GameMode getMode() {
		return mode;
	}
	/**
	 * VIsszaadja a sebességet
	 * @return - Sebesség
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Visszaadj, hogy fut-e a játék
	 * @return - Igaz, ha éppen mozoghatnak a kigyók
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
	 * Visszaadja a nehézséget
	 * @return - Nehézség
	 */
	public Difficulty getDif() {
		return dif;
	}
	/**
	 * Visszaadja a nehézséget Stringben
	 * @return - Nehézség szövegként
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
	 * Visszaadja a MediaPlayerek tömbjét
	 * @return - A MediaPlayerek tömbje
	 */
	public List<MediaPlayer> getMediaPlayers(){
		return mediaPlayers;
	}

	
	//Setterek
	/**
	 * Beállitja a hangerõt
	 * @param d - Beállitandó hangerõ
	 */
	public void setVolume(double d) {
		volume = d;
	}
	/**
	 * Beállitja a játékmódot
	 * @param gm - Beállitandó mód
	 */
	public void setMode(GameMode gm) {
		mode = gm;
	}
	/**
	 * Beállitja, hogy megy-e a játék
	 * @param b - Beállitandó érték
	 */
	public void setGameRuns(boolean b) {
		gameRuns = b;
	}
	/**
	 * Beállitja a nehézséget
	 * @param d - Beállitandó nehézség
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
	 * Hozzáad egy MdeiaPlayert a listához
	 * @param mp - Hozzáadandó MediaPlayer
	 */
	public void addMediaPlayer(MediaPlayer mp) {
		mediaPlayers.add(mp);
	}
	/**
	 * Kivesz egy MediaPlayert a listából
	 * @param mp - Kitörlendõ MediaPlayer
	 */
	public void removeMediaPlayer(MediaPlayer mp) {
		mediaPlayers.remove(mp);
	}
	/**
	 * Léátrehozza a beállitások menüjét
	 * @param stage - Aktuális stage
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
	 * Létrehozza és kirajzolja a menübe vissztajuttató gombot
	 * @param pane - A panel, amire ki kell rajzolni
	 * @param stage - Az aktuális stage
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
	 * Létrehozza a hang beállitására szolgáló panelt
	 * @return - A létrehozott panel
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
	 * Létrehozza a nehézség beállitására szolgáló panelt
	 * @return - A létrehozott panel
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
