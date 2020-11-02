package application;

import java.util.ArrayList;

import application.Snake.Type_Direction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Thread{
	protected Settings settings;
	protected Map map;
	protected ArrayList<Snake> snakeList;
	
	
	protected Game() {
		map = new Map();
		snakeList = new ArrayList<>();
	}
	
	protected void addSnake(Snake snake) {
		snakeList.add(snake);
	}
	
	protected void initGame(Stage stage) {
		initGameWindow(stage);
		map.addFood();
		Snake s1 = new Snake(1, map);
		Snake s2 = new Snake(2, map);
		addSnake(s1);
		addSnake(s2);
		s1.placeSnake(map);
		s2.placeSnake(map);
		
		Thread t1 = new Thread(s1);
		t1.start();
		Thread t2= new Thread(s2);
		t2.start();	
	}
	
	protected void initGameWindow(Stage stage) {
		Pane game_map = new Pane();
		int n = map.size;
		
		map.content(game_map);
		
		game_map.setMinHeight(n*map.sizeOfField);
		game_map.setMinWidth(n*map.sizeOfField);
		
        game_map.setPrefSize(n*map.sizeOfField, n*map.sizeOfField);
        
        
        GridPane points = new GridPane();
        points.setPrefSize(n*map.sizeOfField, 80);
        points.setStyle("-fx-background: RED");
        points.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        points.setLayoutX(0);
        Text t1 = new Text("points");
        Text t2 = new Text("points2");
        points.setAlignment(Pos.CENTER);
        GridPane.setMargin(t1, new Insets(10,100, 10,10));
        
        
        
        points.add(t1, 1, 0);
        points.add(t2, 3, 0);

        points.getNodeOrientation();
        t1.setFill(Color.BLUE);
        t2.setFill(Color.BLACK);
        
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(10, 10, 10, 10));
        
        
        gp.setMinHeight(n*map.sizeOfField);
        gp.setMinWidth(n*map.sizeOfField);
        gp.setStyle("-fx-background: LIME");
        gp.add(points, 0, 1);
        gp.add(game_map, 0, 2);
        gp.setAlignment(Pos.CENTER);

        Scene s = new Scene(gp);
        handleActions(s);
        stage.setMinHeight(n*map.sizeOfField + 200);
        stage.setMinWidth(n*map.sizeOfField + 200);
        stage.setScene(s);
        stage.show();
	}
	
	protected void handleActions(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode() == KeyCode.W) {
				snakeList.get(0).move_direction = Type_Direction.up;
				snakeList.get(0).move();
			} else if(key.getCode() == KeyCode.A) {
				snakeList.get(0).move_direction = Type_Direction.left;
				snakeList.get(0).move();
			} else if(key.getCode() == KeyCode.S) {
				snakeList.get(0).move_direction = Type_Direction.down;
				snakeList.get(0).move();
			} else if(key.getCode() == KeyCode.D) {
				snakeList.get(0).move_direction = Type_Direction.right;
				snakeList.get(0).move();
			} 
			if(true) {
				if(key.getCode() == KeyCode.UP) {
					snakeList.get(1).move_direction = Type_Direction.up;
					snakeList.get(1).move();
				} else if(key.getCode() == KeyCode.LEFT) {
					snakeList.get(1).move_direction = Type_Direction.left;
					snakeList.get(1).move();
				} else if(key.getCode() == KeyCode.DOWN) {
					snakeList.get(1).move_direction = Type_Direction.down;
					snakeList.get(1).move();
				} else if(key.getCode() == KeyCode.RIGHT) {
					snakeList.get(1).move_direction = Type_Direction.right;
					snakeList.get(1).move();
				}
			}
		});
	}
}
