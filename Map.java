package application;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import application.Field.FieldState;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Map extends StackPane {
	protected List<Field> map;
	protected int foods;
	protected int size = 50;
	protected int sizeOfField = 9;
	
	
	protected Map() {
		
	}

	protected void content(Pane parent) {
		map = new ArrayList<>();
		
		for(int i = 0; i < size*size; i++) {
			Field f = new Field(FieldState.empty);
			map.add(f);
			getChildren().add(f);
		}
		
		for(int i = 0; i<map.size(); i++) {
			Field f = map.get(i);
			f.setTranslateX(sizeOfField*(i%size));
			f.setTranslateY(sizeOfField*(i/size));
			parent.getChildren().add(f);
		}
	}
		
	protected synchronized Field getMapElement(int i) {
		return map.get(i);
	}
	
	protected synchronized int getMapSize() {
		return size;
	}
	
	protected synchronized void addFood() {
		boolean siker = false;
		Random ran = new Random();
		while(!siker) {
			int i = ran.nextInt(size*size);
			if(getMapElement(i).getFieldState() == FieldState.empty) {
				getMapElement(i).setFieldState(FieldState.food);
				siker = true;
			}
		}
	}
	
	protected synchronized void addFood(int i) {
		getMapElement(i).setFieldState(FieldState.food);
		
	}
	
	protected void kiir() {
		for(int i = 0; i < 2500; i++) {
			String string = new String();
			FieldState fs = getMapElement(i).getFieldState();
			if(fs == FieldState.snake1_body) {
				string = "1 body\n";
			} else if(fs == FieldState.snake1_head) {
				string = "1 head\n";
			} else if(fs == FieldState.snake2_body) {
				string = "2 body\n";
			} else if(fs == FieldState.snake2_head) {
				string = "2 head\n";
			} else if(fs == FieldState.food) {
				string = "food\n";
			} else
				string = "";
			System.out.print(string);
				
		}
		
	}
}
