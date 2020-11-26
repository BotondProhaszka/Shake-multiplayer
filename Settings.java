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


enum Difficulty {easy, medium, hard};
enum GameMode {singleplayer, multiplayer, vsrobot};

public class Settings {
	private double volume = 0.5;
	private GameMode mode = GameMode.singleplayer;
	private int speed;
	private boolean gameRuns = true;	
	private Scoreboard scoreboard;
	private Difficulty dif = Difficulty.medium;
	private List<MediaPlayer> mediaPlayers;

	public Settings() {
		scoreboard = new Scoreboard("snakemultiplayer_data.txt");
		scoreboard.readData();
		setDif(Difficulty.medium);
		mediaPlayers = new ArrayList<>();
	}
	
	
	
	
	public double getVolume() {
		return volume;
	}
	
	public GameMode getMode() {
		return mode;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public boolean getGameRuns() {
		return gameRuns;
	}
	
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public Difficulty getDif() {
		return dif;
	}
	
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
	public List<MediaPlayer> getMediaPlayers(){
		return mediaPlayers;
	}

	
	
	public void setVolume(double d) {
		volume = d;
	}
	
	public void setMode(GameMode gm) {
		mode = gm;
	}
	
	public void setGameRuns(boolean b) {
		gameRuns = b;
	}
	
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
	
	public void addMediaPlayer(MediaPlayer mp) {
		mediaPlayers.add(mp);
	}
	
	public void removeMediaPlayer(MediaPlayer mp) {
		mediaPlayers.remove(mp);
	}
	
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
