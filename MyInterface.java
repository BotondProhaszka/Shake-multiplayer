package application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyInterface {
	private Stage stage;
	private Settings settings;
	
	
	
	
	public MyInterface (Stage s) {
		stage = s;
		settings  = new Settings();
	}
	
	
	
	public Stage getStage() {
		return stage;
	}
	public Settings getSettings() {
		return settings;
	}
	
	public void setStage(Stage s) {
		stage = s;
	}
	public void setSettings(Settings s) {
		settings = s;
	}
	public void globalInit() {
		initMenuWindow();
		VBox parent = new VBox();
		parent.setSpacing(10);
		parent.setAlignment(Pos.CENTER);
		parent.setStyle("-fx-background-color: LIME");
		
		  
        
        Scene scene = new Scene(parent);
        
        Music m = new Music("menu", settings);
        Thread t = new Thread(m);
        t.run(); 

        //initMenubar(parent);
        initText(parent);
        initButtons(parent);
        
        	
        
        stage.setScene(scene);
        stage.show();      
        
        
        
	}
	
	public void  initButtons(VBox parent) {
		///SINGLEPLAYER
		MyButton spButton = new MyButton("SINGLEPLAYER", "large"); 
		spButton.setOnAction(e -> {
			settings.setMode(GameMode.singleplayer);
        	Game g = new Game(settings);
        	g.initGame(stage);
        });
		parent.getChildren().add(spButton);
		
		///MULTIPLAYER
		MyButton mpButton = new MyButton("MULTIPLAYER", "large"); 
		mpButton.setOnAction(e -> {
			settings.setMode(GameMode.multiplayer);
        	Game g = new Game(settings);
        	g.initGame(stage);
        });
		parent.getChildren().add(mpButton);
		
		///VS ROBOT
		MyButton vsRobotButton = new MyButton("VS ROBOT", "large"); 
		vsRobotButton.setOnAction(e -> {
			settings.setMode(GameMode.vsrobot);
        	Game g = new Game(settings);
        	g.initGame(stage);
        });
		parent.getChildren().add(vsRobotButton);
		
		///SCOREBOARD
		MyButton scoreButton = new MyButton("SCORES", "large");
        scoreButton.setOnAction(e-> {
        	initScore();
        });
        parent.getChildren().add(scoreButton);
	
        ///SETTINGS
        MyButton setButton = new MyButton("SETTINGS", "large");
        setButton.setOnAction(e -> {
        	settings.settingsMenu(stage);
        });
        parent.getChildren().add(setButton);
        
        ///EXIT
        MyButton exitButton = new MyButton("EXIT", "medium");
        exitButton.setOnAction(e -> {
        	settings.getScoreboard().writeData();
        	stage.close();
        });
        parent.getChildren().add(exitButton);
        
        
        
        
	}
	
	public void initText(VBox parent) {
		MyText text = new MyText("SNAKE GAME", "title");
		parent.getChildren().add(text);
	}
	
	public void initMenuWindow() {

		stage.setTitle("Snake (v0.5b)");
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setMinHeight(600);
		stage.setMinWidth(800);		
		
	}
	
	public void initScore() {
		ScoreView sv = new ScoreView(settings.getScoreboard());
		sv.initScoreWindow(stage);
	}
	
}