package application;


import application.Settings.GameMode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * A játék inditásakor megjelenõ ablak létrehozására szolgáló osztály, ez kezeli a menüt.
 * @author Prohászka Botond Bendegúz
 *
 */
public class MyInterface {
	//Adattagok
	/**
	 * Az adott stage
	 */
	private Stage stage;
	/**
	 * A Settings osztály példánya
	 */
	private Settings settings;
	
	
	
	//Konstruktor
	/**
	 * Létrehozza a játékhoz szükséges settings példányt is
	 * @param s - Az aktuális stage
	 */
	public MyInterface (Stage s) {
		stage = s;
		settings  = new Settings();
	}
	
	
	//Getterek
	/**
	 * Visszaadja a Stage-et
	 * @return - A stage
	 */
	public Stage getStage() {
		return stage;
	}
	/**
	 * Visszaadja a Settings példányt
	 * @return - A példány
	 */
	public Settings getSettings() {
		return settings;
	}
	
	
	
	//Setterek
	/**
	 * Beállija a stage-et
	 * @param s - A beállitando objektum
	 */
	public void setStage(Stage s) {
		stage = s;
	}
	/**
	 * Beállitja a settingst
	 * @param s - A beallitando objektum
	 */
	public void setSettings(Settings s) {
		settings = s;
	}
	
	
	
	//Algoritmusok
	/**
	 * Meghivja a szükséges függvényeket, hogy létrehozza az ablakot,
	 * valamint elinditja a zene lejátszását
	 */
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
	/**
	 * Létrehozza a menü gombjait és kezeli a megynomásukat
	 * @param parent - A pane, amire a gombokat kell helyezni
	 */
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
	
	/**
	 * Kiirja a játék nevét cimként
	 * @param parent - A pane, amire a szöveget helyezni kell
	 */
	public void initText(VBox parent) {
		MyText text = new MyText("SNAKE GAME", "title");
		parent.getChildren().add(text);
	}
	
	/**
	 * Létrehozza az ablakot és beállitja a méreteit
	 */
	public void initMenuWindow() {

		stage.setTitle("Snake multiplayer game(v1.0)");
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setMinHeight(600);
		stage.setMinWidth(800);		
		
	}
	/**
	 * A dicsõséglista megtekintését segiti, létrehozza a szükséges objektumot
	 * és meghivja a jelenetet iniccializáló függvényt
	 */
	public void initScore() {
		ScoreView sv = new ScoreView(settings.getScoreboard());
		sv.initScoreWindow(stage);
	}
	
}