package application;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import application.Field.FieldState;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * A pálya osztálya. Eltárolja és kezeli a mezõket,
 * a Game osztály példányát, valamint a méreteket.
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Map extends StackPane {
	//Állandó értékek
	/**
	 * A pálya szélessége/magassága (db mezõ)
	 */
	private final static int size = 50;
	/**
	 * Egy mezõ szélessége/magassága
	 */
	private final static int sizeOfField = 9;
	
	
	
	//adattagok
	/**
	 * A mezõk listája
	 */
	private List<Field> map;
	/**
	 * Az aktuális játék példánya
	 */
	private Game game;
	
	
	
	//Konstruktor
	/**
	 * Beállitja a játékot, lérehozza a mezõk
	 * tömbjét és feltölti azt mezõkkel.
	 * @param g - Az aktuális játék poéldánya
	 */
	public Map(Game g) {
		game = g;
		map = new ArrayList<>();
		
		for(int i = 0; i < size*size; i++) {
			Field f = new Field(FieldState.empty, sizeOfField);
			map.add(f);
			getChildren().add(f);
		}
	}
	
	
	
	
	//Getterek
	/**
	 * VIsszaadja a mezõk listáját
	 * @return - A mezõk listája
	 */
	public synchronized List<Field> getMap(){
		return map;
	}
	/**
	 * Visszaajda az egy sorban/oszlopban található
	 * mezõk számát
	 * @return - A pálya szélessége/magassága (int)
	 */
	public int getMapSize() {
		return size;
	}
	/**
	 * Visszaadja egy mezõ méretét
	 * @return - A mezõ mérete (int)
	 */
	public int getSizeOfField() {
		return sizeOfField;
	}
	/**
	 * Visszaadja az aktuális játék példányát
	 * @return - A játék példánya (Game)
	 */
	public Game getGame() {
		return game; 
	}
	/**
	 * Visszaadja a mezõk listájának egyik elemét
	 * @param i - A visszaadandó elem indexe
	 * @return - Az i. mezõ
	 */
	public synchronized Field getMapElement(int i) {
		return map.get(i);
	}
	
	
	
	//Setterek
	/**
	 * Beállitja a mezõk tömbjét
	 * @param l - A beállitando tömb
	 */
	public void setMap (List<Field> l) {
		map = l;
	}
	/**
	 * Beállitja a játék példányát
	 * @param g - A beállitandó példány
	 */
	public void setGame(Game g) {
		game = g;
	}
	
	
	
	
	//Algoritmusok
	/**
	 * A mezõket felhelyezi egy Panere megjeleités céljából
	 * @param parent - A Pane példánya, amire fel kell helyezni a mezõket
	 */
	public void addContentToPane(Pane parent) {
				
		for(int i = 0; i<map.size(); i++) {
			Field f = map.get(i);
			f.setTranslateX(sizeOfField*(i%size));
			f.setTranslateY(sizeOfField*(i/size));
			parent.getChildren().add(f);
		}
	}
	/**
	 * Egy új almát helyez a pályára, random helyre, addig próbálkozik,
	 * amig nem sikerül, vagy a pálya minden mezõjén van valami
	 */
	public synchronized void addFood() {
		boolean siker = false;
		Random ran = new Random();
		int n = 0;
		while(!siker && n++ < size*size) {
			int i = ran.nextInt(size*size);
			if(getMapElement(i).getFieldState() == FieldState.empty) {
				getMapElement(i).setFieldState(FieldState.food);
				siker = true;
			}
		}
	}	
}
