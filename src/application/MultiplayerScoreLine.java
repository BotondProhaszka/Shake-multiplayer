package application;

import java.io.Serializable;
/**
 * A multiplayer m�d pontjainak ment�s�re �s t�rol�s�ra szolg�l� oszt�ly
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class MultiplayerScoreLine implements Serializable {
	
	//�lland� �rt�kek
	/**
	 * Automatikusan l�trehozott szerializ�l�si verzi�
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	//Adattagok
	/**
	 * Az 1-es j�t�kos neve
	 */
	private String name1;
	/**
	 * Az 1-es j�t�kos pontja
	 */
	private int score1;
	/**
	 * Az 2-es j�t�kos neve
	 */
	private String name2;
	/**
	 * Az 2-es j�t�kos pontja
	 */
	private int score2;
	/**
	 * A gy�ztes neve
	 */
	private String winner;
	/**
	 * A j�t�k neh�zs�ge
	 */
	private String difficulty;
	/**
	 * A j�t�k d�tuma
	 */
	private String date;
	
	
	//Konstruktor
	/**
	 * Be�llitja a kappott pontokb�l az adatokat
	 * @param points1 - Az 1-es j�r�kos pontja
	 * @param points2 - A 2-es j�t�kos pontja
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
	 *  Visszaadja az 1-es j�tlkos nev�t
	 * @return - Az 1-es j�t�kos neve
	 */
	public String getName1() {
		return name1;
	}
	/**
	 *  Visszaadja az 1-es j�tlkos pontj�t
	 * @return - Az 1-es j�t�kos pontja
	 */
	public int getScore1() {
		return score1;
	}
	/**
	 *  Visszaadja az 2-es j�tlkos nev�t
	 * @return - Az 2-es j�t�kos neve
	 */
	public String getName2() {
		return name2;
	}
	/**
	 *  Visszaadja az 2-es j�tlkos pontj�t
	 * @return - Az 2-es j�t�kos pontja
	 */
	public int getScore2() {
		return score2;
	}
	/**
	 * Visszaadja a gy�ztes nev�t
	 * @return - A gy�ztes neve
	 */
	public String getWinner() {
		return winner;
	}
	/**
	 * VIsszaadja a neh�zs�get
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
	
	
	//Setterek
	/**
	 * Be�llitja az 1-es j�t�kos nev�t
	 * @param s - A be�llitand� �rt�k
	 */
	public void setName1(String s) {
		name1 = s;
	}
	/**
	 * Be�llitja az 1-es j�t�kos pontj�t
	 * @param i - A be�llitand� �rt�k
	 */
	public void setScore1(int i) {
		score1 = i; 
	}
	/**
	 * Be�llitja az 2-es j�t�kos nev�t
	 * @param s - A be�llitand� �rt�k
	 */
	public void setName2(String s) {
		name2 = s;
	}
	/**
	 * Be�llitja az 2-es j�t�kos pontj�t
	 * @param i - A be�llitand� �rt�k
	 */
	public void setScore2(int i) {
		score2 = i; 
	}
	/**
	 * Be�llitja a gy�ztest
	 * @param s - A be�llitand� �rt�k
	 */
	public void setWinner(String s) {
		winner = s;
	}
	/**
	 * Be�llitja a neh�zs�get
	 * @param s - A be�llitand� �rt�k
	 */
	public void setDifficulty(String s) {
		difficulty = s;
	}
	/**
	 * Be�llitja a d�tumot
	 * @param s - A be�llitand� �rt�k
	 */
	public void setDate(String s) {
		date = s;
	}
	
}
