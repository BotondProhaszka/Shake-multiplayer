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
 * A j�t�k oszt�lya, felel�s a konkr�t j�t�khoz sz�ks�ges
 * ablakok megjelenit�s��rt, a j�t�khoz sz�ks�ges objektumok
 * l�trehoz�s��rt �s kezel�s��rt.
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Game extends Thread{
	
	//Adattagok
	/**
	 * A be�llitasokat t�rol� Settings oszt�ly p�ld�nya
	 */
	private Settings settings;
	/**
	 * A p�lya oszt�ly p�ld�nya
	 */
	private Map map;
	/**
	 * A j�t�kban aktivan r�szt vev� kigy�k list�ja
	 */
	private ArrayList<Snake> snakeList;
	/**
	 * A j�t�k m�k�d�s�hez sz�ks�ges sz�lak list�ja
	 */
	private ArrayList<Thread> threadList;
	/**
	 * A men� jelenete, ahonnan meghivt�k az els� f�ggv�nyt, elmenti,
	 * hogy vissza lehessen menni a men�be
	 */
	private Scene menuScene;
	/**
	 * A pontokat megjelenit� MyText p�ld�nyok
	 */
	private ArrayList<MyText> pointList;
	/**
	 * A gy�ztes nev�t kiir� MyText
	 */
	private MyText winnerMyText;
	
	
	
	//Konstruktor
	/**
	 * Be�llitja a Settings oszt�ly p�ld�ny�t, l�trehozza a p�ly�t 
	 * �s l�trehozza a t�mb�ket.
	 * @param set - A b e�llit�sokat t�rol� objektum
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
	 * Visszaadja a Settings oszt�ly p�ld�ny�t
	 * @return - A Settings p�ld�ny
	 */
	public Settings getSettings() {
		return settings;
	}
	/**
	 * Visszaadja a Map osz�ly p�ld�ny�t
	 * @return - Az aktu�lis p�lya
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * Visszaadja a kigy�k list�j�t
	 * @return - A kigy�kat t�rol� lista
	 */
	public ArrayList<Snake> getSnakeList() {
		return snakeList;
	}
	/**
	 * Visszaajda a sz�lak list�j�t
	 * @return - A sz�lak list�ja
	 */
	public ArrayList<Thread> getThreadList() {
		return threadList;
	}
	/**
	 * Visszaajda a men� jelenet�t
	 * @return - A men� Scene p�ld�nya
	 */
	public Scene getMenuScene() {
		return menuScene;
	}
	/**
	 * Visszaadja a pontokat megjelit� MyText list�j�t
	 * @return - A t�rol� lista
	 */
	public ArrayList<MyText> getPointList() {
		return pointList;
	}
	/**
	 * Visszaadja a gy�ztest mutat� MyText p�ld�nyt
	 * @return - A MyText p�ld�ny
	 */
	public MyText getWinnerMyText() {
		return winnerMyText;
	}
	
	
	
	//Setterek
	/**
	 * Be�llitja a Settings oszt�ly p�ld�ny�t
	 * @param set - A be�llitand� p�ld�ny
	 */
	public void setSettings(Settings set) {
		settings = set;
	}
	/**
	 * Be�llitja a p�lya p�d�ny�t
	 * @param m - A be�llitand� p�ld�ny
	 */
	public void setMap(Map m) {
		map = m;
	}
	/**
	 * Be�llitja a kigy�k list�j�t
	 * @param l - A be�llitand� lista
	 */
	public void setSnakeList(ArrayList<Snake> l) {
		snakeList = l;
	}
	/**
	 * Be�llitja a sz�lak list�j�t
	 * @param l - A be�llitand� lista
	 */
	public void setThreadList(ArrayList<Thread> l) {
		threadList = l;
	}
	/**
	 * Be�llitja a kapott men� jelenetet
	 * @param s - A be�llitand� p�ld�ny
	 */
	public void setMenuScene(Scene s) {
		menuScene = s;
	}
	/**
	 * Be�llitja a potokat megjel�enit� MyText list�t
	 * @param l - A be�llitand� lista
	 */
	public void setPointList(ArrayList<MyText> l) {
		pointList = l;
	}
	/**
	 * Be�llitja a gy�ztest mutat� MyText p�ld�nyt
	 * @param mt - A be�llitand� p�ld�ny
	 */
	public void setWinnerMyText(MyText mt) {
		winnerMyText = mt;
	}
	
	
	
	//Algoritmusk
	/**
	 * L�trehozza a be�llit�soknak megfelel� sz�m�
	 * kigy�t �s felhelyezi azokat a p�ly�ra
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
	 * Elmenti a men� jelenet�t �s meghivja az ablakot l�trehoz� f�ggv�nyt
	 * @param stage - Az aktu�lis Stage, amin dolgozni fog
	 */
	public void initGame(Stage stage) {
		menuScene = stage.getScene();
		initGameWindow(stage);
		
		
	}
	/**
	 * L�trehozza a jelenetet �s meghivja az egy�b 
	 * objektumokat l�trehoz� f�ggv�nyeket.
	 * @param stage - Az aktu�lis stage
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
	 * L�trehozza a pontokat megjelenit� panelt
	 * @param stage - Az aktu�lis stage
	 * @return - A l�trehozott panel (GridPane) minden sz�ks�ges elemmel
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
	 * L�trehozza a nevek megad�s�ra szolog�l� panelt
	 * �s kezeli a nevek megad�s�t �s t�rol�s�t
	 * @param s - Az aktu�lis stage
	 * @return - A l�trehozott panel (GridPane)
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
	 * ELinditja a j�t�kot, azaz l�trehozza a sz�lakat �s elinditja azokat
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
	 * Le�llitja a j�t�kot (le�llitja a sz�lakat)
	 * �s elmenti a pontokat
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
	 * Hozz�adja gombokat a k�perny�h�z �s kezeli a megnyom�sukat, illteve 
	 * ha elinditj�k a j�t�kot, leveszi a nevek megad�s�ra szolg�l� panelt
	 * @param stage - Az aktu�lis stage
	 * @param parent - A panel (GridPane), amir�l le kell veni a panelt
	 * @param names - A panel, amit le kell venni
	 * @return - A l�trehozott gombok panelje (GridPane)
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
	 * Kiirja, hogy a 2es j�tlkos nyert
	 */
	public void snake1Loses() {
		if(snakeList.size() == 1)
			winnerMyText.setText(":(");
		else
			winnerMyText.setText(snakeList.get(1).getSnakeName() + " won!");
		
	}
	/**
	 * Kiirja, hogy az 1es j�t�kos nyert
	 */
	public void snake2Loses() {
		winnerMyText.setText(snakeList.get(0).getSnakeName() + " won!");
	}
	/**
	 * Kiirja, hogy d�ntetlen
	 */
	public void draw() {
		winnerMyText.setText("DRAW!");
	}
	
	/**
	 * Kezeli a billenty�k lenyom�s�t
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
