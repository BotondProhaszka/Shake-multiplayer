package application;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Field oszt�ly
 * A j�t�khoz sz�ks�ges p�lya mez�inek oszt�lya, a p�lya egy-egy cell�ja
 * Feladata t�rolni �s kezelni a j�t�jben szerepl� elemeket, �s megjeleniteni a p�ly�t
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Field extends StackPane {
	//Enum(ok)
	/**
	 * FieldState
	 * A mez�k tipusa, amik lehetnek �resek,
	 * az egyes j�t�kos kigyoj�nak teste, 
	 * feje a ketts j�t�kos kigyoj�nak teste,
	 * feje, illetve alma 
	 */
	public enum FieldState {empty, snake1_body, snake1_head, snake2_body, snake2_head, food};
	
	
	
	//�lland� �rt�kek
		/**
		 * A mez� �rt�keinek szinei
		 */
		private final static Color empty_color = Color.BLACK;
		private final static Color snake1_color = Color.BLUE;
		private final static  Color snake2_color = Color.YELLOW;
		private final static Color food_color = Color.RED;
	
		
		
	//Adattagok
	/**
	 * A mez�t megjelenit� Rectangle tipus� v�ltoz�
	 */
	private Rectangle border;
	/**
	 * A mez� �llapot�t t�rol� adattag
	 */
	private FieldState state;
	/*
	 * A megjelenitend� n�gyzet oldalhossza
	 */
	private int size;
	
	
	
	//Konstruktor
	/**
	 * Be�llitja �s l�trehozza a sz�ks�ges �rt�keket,
	 * valamint a l�trej�tt Rectangle v�ltoz�t megjeleniti
	 * 
	 * @param fs - A l�trehozand� mez� �rt�ke
	 * @param s - A l�trehozand� mez� m�rete
	 */
	public Field(FieldState fs, int s) {
		size = s;
		state = fs;
		border = new Rectangle(size, size);
		setBorderFill(fs);
		getChildren().addAll(border);
	}
	
	
	
	
	//Getterek
	/**
	 * Visszaadja a mez� �llapot�t
	 * @return  a mez� �llapota
	 */
	public synchronized FieldState getFieldState() {
		return state;
	}
	
	/**
	 * Visszaadja a n�gyzet m�ret�t
	 * @return a n�gyzet m�rete
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Visszaadja az �res mez� szin�t
	 * @return az �res mez� szine
	 */
	public final static Color getEmptyColor() {
		return empty_color;
	}
	
	/**
	 * Visszaadja az 1-es kigy� szin�t
	 * @return az 1-es kigy� szine
	 */
	public final static Color getSnake1Color() {
		return snake1_color;
	}
	
	/**
	 * Visszaadja a 2-es kigy� szin�t
	 * @return a 2-es kigy� szine
	 */
	public final static Color getSnake2Color() {
		return snake2_color;
	}
	
	/**
	 * Visszaadja az alma szin�t
	 * @return az alma szine
	 */
	public final static Color getFoodColor() {
		return food_color;
	}
	
	
	
	//Getterek
	/**
	 * A mez� �llapot�t �llitja be
	 * @param fs - a be�llitand� �rt�k 
	 */
	public synchronized void setFieldState(FieldState fs) {
		state = fs;
		setBorderFill(fs);
	}
	
	/**
	 * A mez� szin�t �llitja be
	 * @param s - A be�llitand� �rt�k 
	 */
	public void setBorderFill(FieldState s) {
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
	
	/**
	 *  A n�gyzet m�ret�t �llitja be
	 * @param i - A be�llitand� �rt�k 
	 */
	public void setSize(int i) {
		size = i;
	}
}
