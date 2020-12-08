package application;

import java.io.Serializable;
/**
 * A multiplayer mód pontjainak mentésére és tárolására szolgáló osztály
 * @author Prohászka Botond Bendegúz
 *
 */
public class MultiplayerScoreLine implements Serializable {
	
	//Állandó értékek
	/**
	 * Automatikusan létrehozott szerializálási verzió
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	//Adattagok
	/**
	 * Az 1-es játékos neve
	 */
	private String name1;
	/**
	 * Az 1-es játékos pontja
	 */
	private int score1;
	/**
	 * Az 2-es játékos neve
	 */
	private String name2;
	/**
	 * Az 2-es játékos pontja
	 */
	private int score2;
	/**
	 * A gyõztes neve
	 */
	private String winner;
	/**
	 * A játék nehézsége
	 */
	private String difficulty;
	/**
	 * A játék dátuma
	 */
	private String date;
	
	
	//Konstruktor
	/**
	 * Beállitja a kappott pontokból az adatokat
	 * @param points1 - Az 1-es járékos pontja
	 * @param points2 - A 2-es játékos pontja
	 */
	public MultiplayerScoreLine(Points points1, Points points2) {
		name1 = points1.getName();
		score1 = points1.getScore();
		name2 = points2.getName();
		score2 = points2.getScore();
		difficulty = points1.getDifficulty();
		date = points1.getDate();
		
		
		if(points1.getIsWinner())
			winner = name1;
		else if(points2.getIsWinner())
			winner = name2;
		else
			winner = "draw";
	}
	
	
	
	//Getterek
	/**
	 *  Visszaadja az 1-es játlkos nevét
	 * @return - Az 1-es játékos neve
	 */
	public String getName1() {
		return name1;
	}
	/**
	 *  Visszaadja az 1-es játlkos pontját
	 * @return - Az 1-es játékos pontja
	 */
	public int getScore1() {
		return score1;
	}
	/**
	 *  Visszaadja az 2-es játlkos nevét
	 * @return - Az 2-es játékos neve
	 */
	public String getName2() {
		return name2;
	}
	/**
	 *  Visszaadja az 2-es játlkos pontját
	 * @return - Az 2-es játékos pontja
	 */
	public int getScore2() {
		return score2;
	}
	/**
	 * Visszaadja a gyõztes nevét
	 * @return - A gyõztes neve
	 */
	public String getWinner() {
		return winner;
	}
	/**
	 * VIsszaadja a nehézséget
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
	
	
	//Setterek
	/**
	 * Beállitja az 1-es játékos nevét
	 * @param s - A beállitandó érték
	 */
	public void setName1(String s) {
		name1 = s;
	}
	/**
	 * Beállitja az 1-es játékos pontját
	 * @param i - A beállitandó érték
	 */
	public void setScore1(int i) {
		score1 = i; 
	}
	/**
	 * Beállitja az 2-es játékos nevét
	 * @param s - A beállitandó érték
	 */
	public void setName2(String s) {
		name2 = s;
	}
	/**
	 * Beállitja az 2-es játékos pontját
	 * @param i - A beállitandó érték
	 */
	public void setScore2(int i) {
		score2 = i; 
	}
	/**
	 * Beállitja a gyõztest
	 * @param s - A beállitandó érték
	 */
	public void setWinner(String s) {
		winner = s;
	}
	/**
	 * Beállitja a nehézséget
	 * @param s - A beállitandó érték
	 */
	public void setDifficulty(String s) {
		difficulty = s;
	}
	/**
	 * Beállitja a dátumot
	 * @param s - A beállitandó érték
	 */
	public void setDate(String s) {
		date = s;
	}
	
}
