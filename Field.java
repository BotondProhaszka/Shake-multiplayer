package application;


//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends StackPane {
	protected enum FieldState {empty, snake1_body, snake1_head, snake2_body, snake2_head, food};
	
	Rectangle border;
	protected FieldState state;
	//protected Image img;
	
	//private int id = 0;
	
	private Color empty_color = Color.BLACK;
	private Color snake1_color = Color.DEEPPINK;
	private Color snake2_color = Color.BLUE;
	private Color food_color = Color.RED;
	
	
	protected Field(FieldState s) {
		state = s;
		border = new Rectangle(9, 9);
		
		//img = new Image("file:grass.jpg", 9, 9, true, true);
        //ImageView iv = new ImageView(img);
        
		setBorderFill(s);
		//border.setStroke(Color.BLACK);
		getChildren().addAll(border);
	}
	
	protected synchronized FieldState getFieldState() {
		return state;
	}
	
	protected synchronized void setFieldState(FieldState fs) {
		state = fs;
		setBorderFill(fs);
	}
	
	protected synchronized void setBorderFill(FieldState s) {
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
