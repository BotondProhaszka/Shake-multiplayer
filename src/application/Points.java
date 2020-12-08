package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A játékos pontjait tároló osztály.
 * @author Prohászka Botond Bendegúz
 *
 */
public class Points implements Serializable{
	//Állandó értékek
	/**
	 * Automatikusan létrehozott szerializálási verzió
	 */
	private static final long serialVersionUID = 1L;
	
	//Adattagok
	/**
	 * A játékos neve
	 */
	private String name;
	/**
	 * A játékos pontja
	 */
	private int score;
	/**
	 * Játék nehézsége
	 */
	private String difficulty;
	/**
	 * Játék dátuma
	 */
	private String date;
	/**
	 * Igaz, ha a játélos nyert, ellenben hamis
	 */
	private boolean isWinner;
	
	
	//Konstrkutor
	/**
	 * Beállitja a szükséges adatokat
	 * @param n - Játékos neve
	 * @param p - Játékos pontja
	 * @param dif - Játék nehézsége
	 * @param b - Nyerés tényét tároló objektum
	 */
	public Points(String n, int p, String dif, boolean b) {
		name = n;
		score = p;
		difficulty = dif;
		isWinner = b;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		date = dateFormat.format(new Date(System.currentTimeMillis()));
	}
	
	
	
	//Getterek
	/**
	 * Visszaadja a játékos nevét
	 * @return - Játékos neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Visszaadja a játékos pontját
	 * @return - Játékos pontja
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Visszaadja a nehézséget
	 * @return - A nehézség
	 */
	public String getDifficulty() {
		return difficulty;
	}
	/**
	 * Visszaadja a dátumot
	 * @return - A dátum
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Visszaadja, hogy nyert-e a játékos
	 * @return - Igaz, ha nyert, ellnben hamis
	 */
	public boolean getIsWinner() {
		return isWinner;
	}
	
	
	
	//Setterek
	/**
	 * Beállitja a játékos nevét
	 * @param s - Beállitandó név
	 */
	public void setName(String s) {
		name = s;
	}
	/**
	 * Beállitja a játékos pontját
	 * @param i - Beállitandó érték
	 */
	public void setScore(int i) {
		score = i; 
	}
	/**
	 * Beállitja a nehézséget
	 * @param s - Beállitandó érték
	 */
	public void setDifficulty(String s) {
		difficulty = s;
	}
	/**
	 * Beállitja a dátumot
	 * @param s - Beállitandó érték
	 */
	public void setDate(String s) {
		date = s;
	}
	/**
	 * Beállitja, hogy nyert-e a játékos
	 * @param b - Beállitandó érték
	 */
	public void setIsWinner(boolean b) {
		isWinner = b;
	}
}
