package application;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends StackPane {
	public enum FieldState {empty, snake1_body, snake1_head, snake2_body, snake2_head, food};
	
	Rectangle border;
	private FieldState state;
	
	
	private Color empty_color = Color.BLACK;
	private Color snake1_color = Color.BLUE;
	private Color snake2_color = Color.YELLOW;
	private Color food_color = Color.RED;
	private int size;
	
	public Field(FieldState fs, int s) {
		size = s;
		state = fs;
		border = new Rectangle(size, size);
		setBorderFill(fs);
		getChildren().addAll(border);
	}
	
	public synchronized FieldState getFieldState() {
		return state;
	}
	
	public Color getEmptyColor() {
		return empty_color;
	}
	public Color getSnake1Color() {
		return snake1_color;
	}
	public Color getSnake2Color() {
		return snake2_color;
	}
	public Color getFoodColor() {
		return food_color;
	}
	public int getSize() {
		return size;
	}
	
	
	public synchronized void setFieldState(FieldState fs) {
		state = fs;
		setBorderFill(fs);
	}
	
	public synchronized void setBorderFill(FieldState s) {
		if(s == FieldState.empty) {
			//border.setFill(new ImagePattern(img));
			border.setFill(empty_color);
		} else if(s == FieldState.snake1_body || s == FieldState.snake1_head) {
			border.setFill(snake1_color);
		} else if(s == FieldState.snake2_body || s == FieldState.snake2_head) {
			border.setFill(snake2_color);
		} else if(s == FieldState.food) {
			border.setFill(food_color);
		} else {
			border.setFill(Color.WHITE);
		}		
	}
}
