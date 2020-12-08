package application;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import application.Field.FieldState;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * A p�lya oszt�lya. Elt�rolja �s kezeli a mez�ket,
 * a Game oszt�ly p�ld�ny�t, valamint a m�reteket.
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Map extends StackPane {
	//�lland� �rt�kek
	/**
	 * A p�lya sz�less�ge/magass�ga (db mez�)
	 */
	private final static int size = 50;
	/**
	 * Egy mez� sz�less�ge/magass�ga
	 */
	private final static int sizeOfField = 9;
	
	
	
	//adattagok
	/**
	 * A mez�k list�ja
	 */
	private List<Field> map;
	/**
	 * Az aktu�lis j�t�k p�ld�nya
	 */
	private Game game;
	
	
	
	//Konstruktor
	/**
	 * Be�llitja a j�t�kot, l�rehozza a mez�k
	 * t�mbj�t �s felt�lti azt mez�kkel.
	 * @param g - Az aktu�lis j�t�k po�ld�nya
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
	 * VIsszaadja a mez�k list�j�t
	 * @return - A mez�k list�ja
	 */
	public synchronized List<Field> getMap(){
		return map;
	}
	/**
	 * Visszaajda az egy sorban/oszlopban tal�lhat�
	 * mez�k sz�m�t
	 * @return - A p�lya sz�less�ge/magass�ga (int)
	 */
	public int getMapSize() {
		return size;
	}
	/**
	 * Visszaadja egy mez� m�ret�t
	 * @return - A mez� m�rete (int)
	 */
	public int getSizeOfField() {
		return sizeOfField;
	}
	/**
	 * Visszaadja az aktu�lis j�t�k p�ld�ny�t
	 * @return - A j�t�k p�ld�nya (Game)
	 */
	public Game getGame() {
		return game; 
	}
	/**
	 * Visszaadja a mez�k list�j�nak egyik elem�t
	 * @param i - A visszaadand� elem indexe
	 * @return - Az i. mez�
	 */
	public synchronized Field getMapElement(int i) {
		return map.get(i);
	}
	
	
	
	//Setterek
	/**
	 * Be�llitja a mez�k t�mbj�t
	 * @param l - A be�llitando t�mb
	 */
	public void setMap (List<Field> l) {
		map = l;
	}
	/**
	 * Be�llitja a j�t�k p�ld�ny�t
	 * @param g - A be�llitand� p�ld�ny
	 */
	public void setGame(Game g) {
		game = g;
	}
	
	
	
	
	//Algoritmusok
	/**
	 * A mez�ket felhelyezi egy Panere megjeleit�s c�lj�b�l
	 * @param parent - A Pane p�ld�nya, amire fel kell helyezni a mez�ket
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
	 * Egy �j alm�t helyez a p�ly�ra, random helyre, addig pr�b�lkozik,
	 * amig nem siker�l, vagy a p�lya minden mez�j�n van valami
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
