package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A j�t�kos pontjait t�rol� oszt�ly.
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Points implements Serializable{
	//�lland� �rt�kek
	/**
	 * Automatikusan l�trehozott szerializ�l�si verzi�
	 */
	private static final long serialVersionUID = 1L;
	
	//Adattagok
	/**
	 * A j�t�kos neve
	 */
	private String name;
	/**
	 * A j�t�kos pontja
	 */
	private int score;
	/**
	 * J�t�k neh�zs�ge
	 */
	private String difficulty;
	/**
	 * J�t�k d�tuma
	 */
	private String date;
	/**
	 * Igaz, ha a j�t�los nyert, ellenben hamis
	 */
	private boolean isWinner;
	
	
	//Konstrkutor
	/**
	 * Be�llitja a sz�ks�ges adatokat
	 * @param n - J�t�kos neve
	 * @param p - J�t�kos pontja
	 * @param dif - J�t�k neh�zs�ge
	 * @param b - Nyer�s t�ny�t t�rol� objektum
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
	 * Visszaadja a j�t�kos nev�t
	 * @return - J�t�kos neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Visszaadja a j�t�kos pontj�t
	 * @return - J�t�kos pontja
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Visszaadja a neh�zs�get
	 * @return - A neh�zs�g
	 */
	public String getDifficulty() {
		return difficulty;
	}
	/**
	 * Visszaadja a d�tumot
	 * @return - A d�tum
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Visszaadja, hogy nyert-e a j�t�kos
	 * @return - Igaz, ha nyert, ellnben hamis
	 */
	public boolean getIsWinner() {
		return isWinner;
	}
	
	
	
	//Setterek
	/**
	 * Be�llitja a j�t�kos nev�t
	 * @param s - Be�llitand� n�v
	 */
	public void setName(String s) {
		name = s;
	}
	/**
	 * Be�llitja a j�t�kos pontj�t
	 * @param i - Be�llitand� �rt�k
	 */
	public void setScore(int i) {
		score = i; 
	}
	/**
	 * Be�llitja a neh�zs�get
	 * @param s - Be�llitand� �rt�k
	 */
	public void setDifficulty(String s) {
		difficulty = s;
	}
	/**
	 * Be�llitja a d�tumot
	 * @param s - Be�llitand� �rt�k
	 */
	public void setDate(String s) {
		date = s;
	}
	/**
	 * Be�llitja, hogy nyert-e a j�t�kos
	 * @param b - Be�llitand� �rt�k
	 */
	public void setIsWinner(boolean b) {
		isWinner = b;
	}
}
