package application;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Field osztály
 * A játékhoz szükséges pálya mezõinek osztálya, a pálya egy-egy cellája
 * Feladata tárolni és kezelni a játéjben szereplõ elemeket, és megjeleniteni a pályát
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Field extends StackPane {
	//Enum(ok)
	/**
	 * FieldState
	 * A mezõk tipusa, amik lehetnek üresek,
	 * az egyes játékos kigyojának teste, 
	 * feje a ketts játékos kigyojának teste,
	 * feje, illetve alma 
	 */
	public enum FieldState {empty, snake1_body, snake1_head, snake2_body, snake2_head, food};
	
	
	
	//Állandó értékek
		/**
		 * A mezõ értékeinek szinei
		 */
		private final static Color empty_color = Color.BLACK;
		private final static Color snake1_color = Color.BLUE;
		private final static  Color snake2_color = Color.YELLOW;
		private final static Color food_color = Color.RED;
	
		
		
	//Adattagok
	/**
	 * A mezõt megjelenitõ Rectangle tipusú változó
	 */
	private Rectangle border;
	/**
	 * A mezõ állapotát tároló adattag
	 */
	private FieldState state;
	/*
	 * A megjelenitendõ négyzet oldalhossza
	 */
	private int size;
	
	
	
	//Konstruktor
	/**
	 * Beállitja és létrehozza a szükséges értékeket,
	 * valamint a létrejött Rectangle változót megjeleniti
	 * 
	 * @param fs - A létrehozandó mezõ értéke
	 * @param s - A létrehozandó mezõ mérete
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
	 * Visszaadja a mezõ állapotát
	 * @return  a mezõ éllapota
	 */
	public synchronized FieldState getFieldState() {
		return state;
	}
	
	/**
	 * Visszaadja a négyzet méretét
	 * @return a négyzet mérete
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Visszaadja az üres mezõ szinét
	 * @return az üres mezõ szine
	 */
	public final static Color getEmptyColor() {
		return empty_color;
	}
	
	/**
	 * Visszaadja az 1-es kigyó szinét
	 * @return az 1-es kigyó szine
	 */
	public final static Color getSnake1Color() {
		return snake1_color;
	}
	
	/**
	 * Visszaadja a 2-es kigyó szinét
	 * @return a 2-es kigyó szine
	 */
	public final static Color getSnake2Color() {
		return snake2_color;
	}
	
	/**
	 * Visszaadja az alma szinét
	 * @return az alma szine
	 */
	public final static Color getFoodColor() {
		return food_color;
	}
	
	
	
	//Getterek
	/**
	 * A mezõ állapotát állitja be
	 * @param fs - a beállitandó érték 
	 */
	public synchronized void setFieldState(FieldState fs) {
		state = fs;
		setBorderFill(fs);
	}
	
	/**
	 * A mezõ szinét állitja be
	 * @param s - A beállitandó érték 
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
	 *  A négyzet méretét állitja be
	 * @param i - A beállitandó érték 
	 */
	public void setSize(int i) {
		size = i;
	}
}
