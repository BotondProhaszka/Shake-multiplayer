package application;


import application.Settings.GameMode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * A j�t�k indit�sakor megjelen� ablak l�trehoz�s�ra szolg�l� oszt�ly, ez kezeli a men�t.
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class MyInterface {
	//Adattagok
	/**
	 * Az adott stage
	 */
	private Stage stage;
	/**
	 * A Settings oszt�ly p�ld�nya
	 */
	private Settings settings;
	
	
	
	//Konstruktor
	/**
	 * L�trehozza a j�t�khoz sz�ks�ges settings p�ld�nyt is
	 * @param s - Az aktu�lis stage
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
	 * Visszaadja a Settings p�ld�nyt
	 * @return - A p�ld�ny
	 */
	public Settings getSettings() {
		return settings;
	}
	
	
	
	//Setterek
	/**
	 * Be�llija a stage-et
	 * @param s - A be�llitando objektum
	 */
	public void setStage(Stage s) {
		stage = s;
	}
	/**
	 * Be�llitja a settingst
	 * @param s - A beallitando objektum
	 */
	public void setSettings(Settings s) {
		settings = s;
	}
	
	
	
	//Algoritmusok
	/**
	 * Meghivja a sz�ks�ges f�ggv�nyeket, hogy l�trehozza az ablakot,
	 * valamint elinditja a zene lej�tsz�s�t
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
	 * L�trehozza a men� gombjait �s kezeli a megynom�sukat
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
	 * Kiirja a j�t�k nev�t cimk�nt
	 * @param parent - A pane, amire a sz�veget helyezni kell
	 */
	public void initText(VBox parent) {
		MyText text = new MyText("SNAKE GAME", "title");
		parent.getChildren().add(text);
	}
	
	/**
	 * L�trehozza az ablakot �s be�llitja a m�reteit
	 */
	public void initMenuWindow() {

		stage.setTitle("Snake multiplayer game(v1.0)");
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setMinHeight(600);
		stage.setMinWidth(800);		
		
	}
	/**
	 * A dics�s�glista megtekint�s�t segiti, l�trehozza a sz�ks�ges objektumot
	 * �s meghivja a jelenetet iniccializ�l� f�ggv�nyt
	 */
	public void initScore() {
		ScoreView sv = new ScoreView(settings.getScoreboard());
		sv.initScoreWindow(stage);
	}
	
}