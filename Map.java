package application;


import java.util.ArrayList;
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
	protected Settings set;
	protected Game game;
	
	
	protected Map(Settings s, Game g) {
		set = s;
		game = g;
	}

	protected Settings getSettings() {
		return set;
	}
	
	protected void setGame(Game g) {
		game = g;
	}
	
	protected Game getGame() {
		return game; 
	}
	
	protected List<Field> getMap(){
		return map;
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
}
