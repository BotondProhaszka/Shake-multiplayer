package application;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import application.Field.FieldState;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Map extends StackPane {
	private List<Field> map;
	private int size = 50;
	private int sizeOfField = 9;
	private Settings set;
	private Game game;
	
	
	public Map(Settings s, Game g) {
		set = s;
		game = g;
	}
	
	
	public List<Field> getMap(){
		return map;
	}
	public synchronized int getMapSize() {
		return size;
	}
	public int getSizeOfField() {
		return sizeOfField;
	}
	public Settings getSettings() {
		return set;
	}
	public Game getGame() {
		return game; 
	}
	public synchronized Field getMapElement(int i) {
		return map.get(i);
	}
	
	
	public void setMap (List<Field> l) {
		map = l;
	}
	public void setMapSize(int i) {
		size = i;
	}
	public void setSizeOfField(int i) {
		sizeOfField = i;
	}
	public void setSettings(Settings s) {
		set = s;
	}
	public void setGame(Game g) {
		game = g;
	}
	
	
	
	
	
	public void content(Pane parent) {
		map = new ArrayList<>();
		
		for(int i = 0; i < size*size; i++) {
			Field f = new Field(FieldState.empty, sizeOfField);
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
	
	public synchronized void addFood() {
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
	
	public synchronized void addFood(int i) {
		getMapElement(i).setFieldState(FieldState.food);
		
	}
}
