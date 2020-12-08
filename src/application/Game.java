package application;

import java.util.ArrayList;

import application.Field.FieldState;
import application.Settings.GameMode;
import application.Snake.Type_Direction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * A játék osztálya, felelõs a konkrét játékhoz szükséges
 * ablakok megjelenitéséért, a játékhoz szükséges objektumok
 * létrehozásáért és kezeléséért.
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Game extends Thread{
	
	//Adattagok
	/**
	 * A beállitasokat tároló Settings osztály példánya
	 */
	private Settings settings;
	/**
	 * A pálya osztály példánya
	 */
	private Map map;
	/**
	 * A játékban aktivan részt vevõ kigyók listája
	 */
	private ArrayList<Snake> snakeList;
	/**
	 * A játék mûködéséhez szükséges szálak listája
	 */
	private ArrayList<Thread> threadList;
	/**
	 * A menü jelenete, ahonnan meghivták az elsõ függvényt, elmenti,
	 * hogy vissza lehessen menni a menübe
	 */
	private Scene menuScene;
	/**
	 * A pontokat megjelenitõ MyText példányok
	 */
	private ArrayList<MyText> pointList;
	/**
	 * A gyõztes nevét kiiró MyText
	 */
	private MyText winnerMyText;
	
	
	
	//Konstruktor
	/**
	 * Beállitja a Settings osztály példányát, létrehozza a pályát 
	 * és létrehozza a tömböket.
	 * @param set - A b eállitásokat tároló objektum
	 */
	public Game(Settings set) {
		settings = set;
		map = new Map(this);
		snakeList = new ArrayList<>();
		threadList = new ArrayList<>();
		pointList = new ArrayList<>();
	}
	
	
	
	//Getterek
	/**
	 * Visszaadja a Settings osztály példányát
	 * @return - A Settings példány
	 */
	public Settings getSettings() {
		return settings;
	}
	/**
	 * Visszaadja a Map oszály példányát
	 * @return - Az aktuális pálya
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * Visszaadja a kigyók listáját
	 * @return - A kigyókat tároló lista
	 */
	public ArrayList<Snake> getSnakeList() {
		return snakeList;
	}
	/**
	 * Visszaajda a szálak listáját
	 * @return - A szálak listája
	 */
	public ArrayList<Thread> getThreadList() {
		return threadList;
	}
	/**
	 * Visszaajda a menü jelenetét
	 * @return - A menü Scene példánya
	 */
	public Scene getMenuScene() {
		return menuScene;
	}
	/**
	 * Visszaadja a pontokat megjelitõ MyText listáját
	 * @return - A tároló lista
	 */
	public ArrayList<MyText> getPointList() {
		return pointList;
	}
	/**
	 * Visszaadja a gyõztest mutató MyText példányt
	 * @return - A MyText példány
	 */
	public MyText getWinnerMyText() {
		return winnerMyText;
	}
	
	
	
	//Setterek
	/**
	 * Beállitja a Settings osztály példányát
	 * @param set - A beállitandó példány
	 */
	public void setSettings(Settings set) {
		settings = set;
	}
	/**
	 * Beállitja a pálya pédányát
	 * @param m - A beállitandó példány
	 */
	public void setMap(Map m) {
		map = m;
	}
	/**
	 * Beállitja a kigyók listáját
	 * @param l - A beállitandó lista
	 */
	public void setSnakeList(ArrayList<Snake> l) {
		snakeList = l;
	}
	/**
	 * Beállitja a szálak listáját
	 * @param l - A beállitandó lista
	 */
	public void setThreadList(ArrayList<Thread> l) {
		threadList = l;
	}
	/**
	 * Beállitja a kapott menü jelenetet
	 * @param s - A beállitandó példány
	 */
	public void setMenuScene(Scene s) {
		menuScene = s;
	}
	/**
	 * Beállitja a potokat megjeléenitõ MyText listát
	 * @param l - A beállitandó lista
	 */
	public void setPointList(ArrayList<MyText> l) {
		pointList = l;
	}
	/**
	 * Beállitja a gyõztest mutató MyText példányt
	 * @param mt - A beállitandó példány
	 */
	public void setWinnerMyText(MyText mt) {
		winnerMyText = mt;
	}
	
	
	
	//Algoritmusk
	/**
	 * Létrehozza a beállitásoknak megfelelõ számú
	 * kigyót és felhelyezi azokat a pályára
	 */
	public void addSnakes() {
		Snake s1 = new Snake(1, map);
		snakeList.add(s1);
		s1.placeSnake();
		if(settings.getMode() == GameMode.multiplayer) {
			Snake s2 = new Snake(2, map);
			snakeList.add(s2);
			s2.placeSnake();
		} else if(settings.getMode() == GameMode.vsrobot) {
			Snake s3 = new Snake(3, map);
			snakeList.add(s3);
			s3.placeSnake();
		}
	}
	/**
	 * Elmenti a menü jelenetét és meghivja az ablakot létrehozó függvényt
	 * @param stage - Az aktuális Stage, amin dolgozni fog
	 */
	public void initGame(Stage stage) {
		menuScene = stage.getScene();
		initGameWindow(stage);
		
		
	}
	/**
	 * Látrehozza a jelenetet és meghivja az egyéb 
	 * objektumokat létrehozó függvényeket.
	 * @param stage - Az aktuális stage
	 */
	public void initGameWindow(Stage stage) {
		Pane game_map = new Pane();
		int n = map.getMapSize();
		map.addContentToPane(game_map);
		
		game_map.setMinHeight(n*map.getSizeOfField());
		game_map.setMinWidth(n*map.getSizeOfField());
        game_map.setPrefSize(n*map.getSizeOfField(), n*map.getSizeOfField());
        
        map.addFood();
        addSnakes();
		
        GridPane names = setName(stage);
        GridPane points = initPoints(stage);
        
        
        
        VBox winnerBox = new VBox();
        winnerBox.setAlignment(Pos.CENTER);
        
        winnerMyText = new MyText("Who will win?", "large");
        winnerMyText.setFill(Color.RED);
        winnerBox.getChildren().add(winnerMyText);
        
        GridPane gp = new GridPane();
        gp.setHgap(20);
        gp.setVgap(20);
        gp.setAlignment(Pos.CENTER);
        
        
        gp.setMinHeight(n*map.getSizeOfField());
        gp.setMinWidth(n*map.getSizeOfField());
        gp.setStyle("-fx-background: LIME");
        gp.add(names, 0, 1);
        gp.add(points, 0, 2);
        gp.add(winnerBox, 0, 3);
        gp.add(game_map, 0, 4);
        gp.add(createButtons(stage, gp, names), 0, 5);
        gp.setAlignment(Pos.CENTER);
        
        
        Scene s = new Scene(gp);
        
        handleActions(s);
        stage.setMinHeight(n*map.getSizeOfField() + 400);
        stage.setMinWidth(n*map.getSizeOfField() + 200);
        stage.setScene(s);
        stage.show();
	}
	
	/**
	 * Létrehozza a pontokat megjelenitõ panelt
	 * @param stage - Az aktuális stage
	 * @return - A létrehozott panel (GridPane) minden szükséges elemmel
	 */
	public GridPane initPoints(Stage stage) {
		GridPane points = new GridPane();
        points.setPrefSize(map.getMapSize()*map.getSizeOfField(), 80);
        points.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        points.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        points.setAlignment(Pos.CENTER);
        
        
        
        MyText name1 = snakeList.get(0).getSnakeNameMyText();
        map.getMapElement(0);
		name1.setFill(Field.getSnake1Color());
        MyText point1 = snakeList.get(0).getPointMyText();
        map.getMapElement(0);
		point1.setFill(Field.getSnake1Color());
        
        pointList.add(point1);
        if(settings.getMode() == GameMode.multiplayer) {
        	MyText name2 = snakeList.get(1).getSnakeNameMyText();
			name2.setFill(Field.getSnake2Color());
        	MyText point2 = snakeList.get(1).getPointMyText();
			point2.setFill(Field.getSnake2Color());
        	points.add(name2, 1, 2);
        	points.add(point2, 2, 2);
        } else if(settings.getMode() == GameMode.vsrobot) {
        	MyText name2 = snakeList.get(1).getSnakeNameMyText();
			name2.setFill(Field.getSnake2Color());
        	MyText point2 = snakeList.get(1).getPointMyText();
			point2.setFill(Field.getSnake2Color());
        	pointList.add(point2);
        	points.add(name2, 1, 2);
        	points.add(point2, 2, 2);
        }
        points.setAlignment(Pos.CENTER);
        GridPane.setMargin(name1, new Insets(10,100, 10,10));
        
        points.add(name1, 1, 1);
        points.add(point1, 2, 1);
        points.getNodeOrientation();
        return points;
	}
	
	/**
	 * Létrehozza a nevek megadására szologáló panelt
	 * és kezeli a nevek megadását és tárolását
	 * @param s - Az aktuális stage
	 * @return - A létrehozott panel (GridPane)
	 */
	public GridPane setName(Stage s) {
		GridPane gp = new GridPane();
		
		gp.setVgap(10);
		gp.setHgap(10);
		
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(map.getMapSize()*map.getMap().get(0).getSize(), 80);
        gp.setStyle("-fx-background: LIME");
        gp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        Text t1 = new Text("First player: ");
        t1.setFont(Font.font("impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 10));
		t1.setFill(Color.BLACK);
        TextField tf1 = new TextField();
        gp.add(t1, 1, 1);
        gp.add(tf1, 2, 1);
        TextField tf2 = new TextField();
        if(settings.getMode() == GameMode.multiplayer) {
	        Text t2 = new Text("Second player: ");
	        t2.setFont(Font.font("impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 10));
			t2.setFill(Color.BLACK);
	        
	        gp.add(t2, 1, 2);
	        gp.add(tf2, 2, 2);
        }         
        
        MyButton button = new MyButton("Okay", "small");
        gp.add(button, 3, 1);
        
        
        button.setOnAction(e -> {
        	snakeList.get(0).setSnakeName(tf1.getText());
        	if(settings.getMode() == GameMode.multiplayer)
        		snakeList.get(1).setSnakeName(tf2.getText());
        	else if(settings.getMode() == GameMode.vsrobot)
        		snakeList.get(1).setSnakeName("Robot");
        });
        
       return gp;     
	}
	
	/**
	 * ELinditja a játékot, azaz létrehozza a szálakat és elinditja azokat
	 */
	public void startGame() {
		Thread th1 = new Thread(snakeList.get(0));
		threadList.add(th1);
		if(settings.getMode() != GameMode.singleplayer) {
			Thread th2 = new Thread(snakeList.get(1));
			threadList.add(th2);
			th2.start();
		}
		th1.start();
	}
	
	/**
	 * Leállitja a játékot (leállitja a szálakat)
	 * és elmenti a pontokat
	 */
	@SuppressWarnings("deprecation")
	public void stopGame() {
		
		settings.setGameRuns(false);
		threadList.forEach((n) -> n.stop());
		boolean en = false;
		for(Snake s : snakeList) {
			s.getErremenj().clear();
			if(s.getPoints() != 0)
				en = true;
		}
		if(en)
			settings.getScoreboard().addPoints(snakeList);
	}
	
	/**
	 * Hozzáadja gombokat a képernyõhöz és kezeli a megnyomásukat, illteve 
	 * ha elinditják a játékot, leveszi a nevek megadására szolgáló panelt
	 * @param stage - Az aktuális stage
	 * @param parent - A panel (GridPane), amirõl le kell veni a panelt
	 * @param names - A panel, amit le kell venni
	 * @return - A létrehozott gombok panelje (GridPane)
	 */
	public GridPane createButtons(Stage stage, GridPane parent, GridPane names) {
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		GridPane buttons = new GridPane();
		buttons.setVgap(10);
		buttons.setHgap(10);
		
		
	    MyButton startButton = new MyButton("START", "medium");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(startButton, 0, 0);
	    
	        
	    startButton.setOnAction(e -> {
	    	parent.getChildren().remove(names);
	        if(settings.getGameRuns())
	        	startGame();
	    });
	    
	    MyButton newButton = new MyButton("NEW", "medium");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(newButton, 1, 0);
	    
	    newButton.setOnAction(e -> {
	    	stopGame();
	    	map.getMap().forEach((n) -> n.setFieldState(FieldState.empty));
	    	for(int i = 0; i < snakeList.size(); i++) {
	    		snakeList.get(i).resetSnake();
	    	}
	    	//snakeList.forEach((n) -> n.resetSnake());
	    	map.addFood();
	    	winnerMyText.setText("Who will win?");
	    	settings.setGameRuns(true);
	    });
	    
	    MyButton exitButton = new MyButton("EXIT", "medium");
	    settings.setGameRuns(true);
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(exitButton, 2, 0);
	    
	    exitButton.setOnAction(e -> {
	    	stopGame();
	    	stage.setScene(menuScene);
	    });
	    
	    gp.add(buttons, 0, 4);
	    return gp;
	}
	
	/**
	 * Kiirja, hogy a 2es játlkos nyert
	 */
	public void snake1Loses() {
		if(snakeList.size() == 1)
			winnerMyText.setText(":(");
		else
			winnerMyText.setText(snakeList.get(1).getSnakeName() + " won!");
		
	}
	/**
	 * Kiirja, hogy az 1es játékos nyert
	 */
	public void snake2Loses() {
		winnerMyText.setText(snakeList.get(0).getSnakeName() + " won!");
	}
	/**
	 * Kiirja, hogy döntetlen
	 */
	public void draw() {
		winnerMyText.setText("DRAW!");
	}
	
	/**
	 * Kezeli a billentyûk lenyomását
	 * @param scene - Az adott jelenet
	 */
	public void handleActions(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(settings.getGameRuns()) {
				if(key.getCode() == KeyCode.W) {
					snakeList.get(0).setMoveDir(Type_Direction.up);
				} else if(key.getCode() == KeyCode.A) {
					snakeList.get(0).setMoveDir(Type_Direction.left);
				} else if(key.getCode() == KeyCode.S) {
					snakeList.get(0).setMoveDir(Type_Direction.down);
				} else if(key.getCode() == KeyCode.D) {
					snakeList.get(0).setMoveDir(Type_Direction.right);
				} 
				if(settings.getMode() == GameMode.multiplayer) {
					if(key.getCode() == KeyCode.UP) {
						snakeList.get(1).setMoveDir(Type_Direction.up);
					} else if(key.getCode() == KeyCode.LEFT) {
						snakeList.get(1).setMoveDir(Type_Direction.left);
					} else if(key.getCode() == KeyCode.DOWN) {
						snakeList.get(1).setMoveDir(Type_Direction.down);
					} else if(key.getCode() == KeyCode.RIGHT) {
						snakeList.get(1).setMoveDir(Type_Direction.right);
					}
				}
			}
		});
	}
}
