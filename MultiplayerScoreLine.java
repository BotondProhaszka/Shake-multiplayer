package application;

import java.io.Serializable;

public class MultiplayerScoreLine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name1;
	private int score1;
	private String name2;
	private int score2;
	private String winner;
	private String difficulty;
	private String date;
	
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
	
	
	public String getName1() {
		return name1;
	}
	public int getScore1() {
		return score1;
	}
	public String getName2() {
		return name2;
	}
	public int getScore2() {
		return score2;
	}
	public String getWinner() {
		return winner;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public String getDate() {
		return date;
	}
	
	public void setName1(String s) {
		name1 = s;
	}
	public void setScore1(int i) {
		score1 = i; 
	}
	
	public void setName2(String s) {
		name2 = s;
	}
	public void setScore2(int i) {
		score2 = i; 
	}
	public void setWinner(String s) {
		winner = s;
	}
	public void setDifficulty(String s) {
		difficulty = s;
	}
	public void setDate(String s) {
		date = s;
	}
	
}
