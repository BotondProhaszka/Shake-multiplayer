package application;

import java.util.ArrayList;

import application.Snake.Type_Direction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Thread{
	protected Settings settings;
	protected Map map;
	protected ArrayList<Snake> snakeList;
	protected ArrayList<Thread> threadList;
	protected Scene menuScene;
	
	
	protected Game(Settings set) {
		settings = set;
		map = new Map(set, this);
		snakeList = new ArrayList<>();
		threadList = new ArrayList<>();
	}
	
	
	protected Settings getSettings() {
		return settings;
	}
	
	
	protected void addSnakes() {
		Snake s1 = new Snake(1, map);
		snakeList.add(s1);
		s1.placeSnake(map);
		if(settings.getMultiplayer()) {
			Snake s2 = new Snake(2, map);
			snakeList.add(s2);
			s2.placeSnake(map);
		}
	}
	
	protected void initGame(Stage stage) {
		menuScene = stage.getScene();
		initGameWindow(stage);
		
		
	}
	
	protected void initGameWindow(Stage stage) {
		Pane game_map = new Pane();
		int n = map.size;
		
		map.content(game_map);
		
		game_map.setMinHeight(n*map.sizeOfField);
		game_map.setMinWidth(n*map.sizeOfField);
        game_map.setPrefSize(n*map.sizeOfField, n*map.sizeOfField);
        
        map.addFood();
        addSnakes();
		
		
        GridPane points = new GridPane();
        points.setPrefSize(n*map.sizeOfField, 80);
        points.setStyle("-fx-background: RED");
        points.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        points.setLayoutX(0);
        points.setAlignment(Pos.CENTER);
        
        GridPane names = setName(stage);
        //points.add(names, 1, 3);
        Text t1 = snakeList.get(0).getSnakeName();
        //Text p1 = snakeList.get(0).getSnake
        if(settings.getMultiplayer()) {
        	Text t2 = snakeList.get(1).getSnakeName();
            points.add(t2, 3, 0);
            t2.setFill(Color.BLACK);
        }
        points.setAlignment(Pos.CENTER);
        GridPane.setMargin(t1, new Insets(10,100, 10,10));
        
        points.add(t1, 1, 0);

        points.getNodeOrientation();
        t1.setFill(Color.BLUE);
        
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));
        
        
        gp.setMinHeight(n*map.sizeOfField);
        gp.setMinWidth(n*map.sizeOfField);
        gp.setStyle("-fx-background: LIME");
        gp.add(points, 0, 1);
        gp.add(game_map, 0, 3);
        gp.add(names, 0, 2);
        gp.setAlignment(Pos.CENTER);
        
        
        addButtons(stage, gp, names);
        

        Scene s = new Scene(gp);
        
        handleActions(s);
        stage.setMinHeight(n*map.sizeOfField + 200);
        stage.setMinWidth(n*map.sizeOfField + 200);
        stage.setScene(s);
        stage.show();
	}
	
	protected void countDown(Stage s) {
		Pane p = new Pane();
		Text t = new Text("3");
		p.getChildren().add(t);
		Scene sc = new Scene(p);
		s.setScene(sc);
	}
	
	protected GridPane setName(Stage s) {
		GridPane gp = new GridPane();
	
		gp.setAlignment(Pos.CENTER);
		gp.setPrefSize(map.size*map.sizeOfField, 80);
        gp.setStyle("-fx-background: LIME");
        gp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        Text t1 = new Text("First player: ");
        t1.setFont(Font.font("impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 10));
		t1.setFill(Color.BLACK);
        TextField tf1 = new TextField();
        gp.add(t1, 1, 1);
        gp.add(tf1, 2, 1);
        TextField tf2 = new TextField();
        if(settings.getMultiplayer()) {
	        Text t2 = new Text("Second player: ");
	        t2.setFont(Font.font("impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 10));
			t2.setFill(Color.BLACK);
	        
	        gp.add(t2, 1, 2);
	        gp.add(tf2, 2, 2);
        }
        
        
        Button button = new Button("Okay");
        button.setMinSize(50, 30);
        button.setPrefSize(50, 30);
        gp.add(button, 3, 1);
        
        
        
        button.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 12;"
        		+ "-fx-text-fill: LIME");
        
        button.setOnAction(e -> {
        	snakeList.get(0).setSnakeName(tf1.getText());
        	if(settings.getMultiplayer())
        		snakeList.get(1).setSnakeName(tf2.getText());
        	
        });
        
       return gp;     
	}
	
	protected void stopGame() {
		
		settings.setGameRuns(false);
		threadList.forEach((n) -> n.stop());
	}
	
	protected void startGame() {
		settings.setGameRuns(true);
		Thread th1 = new Thread(snakeList.get(0));
		threadList.add(th1);
		if(settings.getMultiplayer()) {
			Thread th2 = new Thread(snakeList.get(1));
			threadList.add(th2);
			th2.start();
		}
		th1.start();
		System.out.println("start");
	}
	
	protected void addButtons(Stage stage, GridPane gp, GridPane names) {
		GridPane buttons = new GridPane();
	       
	    Button startButton = new Button("START");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(startButton, 0, 0);
	    startButton.setMinSize(60, 40);
	    startButton.setPrefSize(60, 40);
	        
	        
	        
	    startButton.setStyle("-fx-background-color: BLACK;"
	    		+ "-fx-font-weight: BOLD;"
	        	+ "-fx-font-size: 12;"
	        	+ "-fx-text-fill: LIME");
	        
	    startButton.setOnAction(e -> {
	    	gp.getChildren().remove(names);
	        startGame();
	    });
	    
	    Button newButton = new Button("NEW");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(newButton, 1, 0);
	    newButton.setMinSize(60, 40);
	    newButton.setPrefSize(60, 40);	        
	        
	        
	    newButton.setStyle("-fx-background-color: BLACK;"
	    		+ "-fx-font-weight: BOLD;"
	        	+ "-fx-font-size: 12;"
	        	+ "-fx-text-fill: LIME");
	        
	    newButton.setOnAction(e -> {
	    	stopGame();
	    	snakeList.forEach((n) -> n.removeSnake(map));
	    	String name1 = snakeList.get(0).getName();
	    	String name2 = new String();
	    	if(settings.getMultiplayer())
	    		name2 = snakeList.get(0).getName();
	    	
	    	snakeList.clear();
	    	addSnakes();
	    	snakeList.get(0).setName(name1);
	    	if(settings.getMultiplayer())
	    		snakeList.get(1).setName(name2);
	    	map.addFood();
	    	setName(stage);
	    });
	    
	    /*
	    Button stepButton = new Button("MOVE");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(stepButton, 3, 0);
	    stepButton.setMinSize(60, 40);
	    stepButton.setPrefSize(60, 40);	        
	        
	        
	    stepButton.setStyle("-fx-background-color: BLACK;"
	    		+ "-fx-font-weight: BOLD;"
	        	+ "-fx-font-size: 12;"
	        	+ "-fx-text-fill: LIME");
	    
	    stepButton.setOnAction(e -> {
	    	snakeList.get(0).move();
	    });
	    
	    
	    Button updButton = new Button("UPDATE");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(updButton, 4, 0);
	    updButton.setMinSize(60, 40);
	    updButton.setPrefSize(60, 40);	        
	        
	        
	    updButton.setStyle("-fx-background-color: BLACK;"
	    		+ "-fx-font-weight: BOLD;"
	        	+ "-fx-font-size: 12;"
	        	+ "-fx-text-fill: LIME");
	    
	    updButton.setOnAction(e -> {
	    	t1.setText(snakeList.get(0).getMoveText()); 	
	    	t2.setText(snakeList.get(0).getLastText());
	    });
	    
	    */
	    Button exitButton = new Button("EXIT");
	    buttons.setAlignment(Pos.CENTER);
	    buttons.add(exitButton, 3, 0);
	    exitButton.setMinSize(60, 40);
	    exitButton.setPrefSize(60, 40);	        
	        
	        
	    exitButton.setStyle("-fx-background-color: BLACK;"
	    		+ "-fx-font-weight: BOLD;"
	        	+ "-fx-font-size: 12;"
	        	+ "-fx-text-fill: LIME");
	    
	    exitButton.setOnAction(e -> {
	    	stage.setScene(menuScene);
	    });
	    
	    gp.add(buttons, 0, 4);

	}
	
	
	protected void handleActions(Scene scene) {
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
				if(settings.getMultiplayer()) {
					if(key.getCode() == KeyCode.I) {
						snakeList.get(1).setMoveDir(Type_Direction.up);
					} else if(key.getCode() == KeyCode.J) {
						snakeList.get(1).setMoveDir(Type_Direction.left);
					} else if(key.getCode() == KeyCode.K) {
						snakeList.get(1).setMoveDir(Type_Direction.down);
					} else if(key.getCode() == KeyCode.L) {
						snakeList.get(1).setMoveDir(Type_Direction.right);
					}
				}
			}
		});
	}
}
