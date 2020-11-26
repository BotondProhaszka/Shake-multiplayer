package application;

import java.util.ArrayList;

import application.Field.FieldState;
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

public class Game extends Thread{
	private Settings settings;
	private Map map;
	private ArrayList<Snake> snakeList;
	private ArrayList<Thread> threadList;
	private Scene menuScene;
	private ArrayList<MyText> pointList;
	private MyText winnerMyText;
	
	
	public Game(Settings set) {
		settings = set;
		map = new Map(set, this);
		snakeList = new ArrayList<>();
		threadList = new ArrayList<>();
		pointList = new ArrayList<>();
	}
	
	
	public Settings getSettings() {
		return settings;
	}
	
	
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
	
	public void initGame(Stage stage) {
		menuScene = stage.getScene();
		initGameWindow(stage);
		
		
	}
	
	public void initGameWindow(Stage stage) {
		Pane game_map = new Pane();
		int n = map.getMapSize();
		map.content(game_map);
		
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
	
	public GridPane initPoints(Stage stage) {
		GridPane points = new GridPane();
        points.setPrefSize(map.getMapSize()*map.getSizeOfField(), 80);
        points.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        points.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        points.setAlignment(Pos.CENTER);
        
        
        
        MyText name1 = snakeList.get(0).getSnakeNameMyText();
        name1.setFill(map.getMapElement(0).getSnake1Color());
        MyText point1 = snakeList.get(0).getPointMyText();
        point1.setFill(map.getMapElement(0).getSnake1Color());
        
        pointList.add(point1);
        if(settings.getMode() == GameMode.multiplayer) {
        	MyText name2 = snakeList.get(1).getSnakeNameMyText();
        	name2.setFill(map.getMapElement(0).getSnake2Color());
        	MyText point2 = snakeList.get(1).getPointMyText();
        	point2.setFill(map.getMapElement(0).getSnake2Color());
        	pointList.add(point2);
        	points.add(name2, 1, 2);
        	points.add(point2, 2, 2);
        } else if(settings.getMode() == GameMode.vsrobot) {
        	MyText name2 = snakeList.get(1).getSnakeNameMyText();
        	name2.setFill(map.getMapElement(0).getSnake2Color());
        	MyText point2 = snakeList.get(1).getPointMyText();
        	point2.setFill(map.getMapElement(0).getSnake2Color());
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
	
	public void snake1Loses() {
		if(snakeList.size() == 1)
			winnerMyText.setText(":(");
		else
			winnerMyText.setText(snakeList.get(1).getSnakeName() + " won!");
		
	}
	public void snake2Loses() {
		winnerMyText.setText(snakeList.get(0).getSnakeName() + " won!");
	}
	public void draw() {
		winnerMyText.setText("DRAW!");
	}
	
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
