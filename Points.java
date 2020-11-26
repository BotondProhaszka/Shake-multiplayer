package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Points implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	private int score;
	private String difficulty;
	private String date;
	private boolean isWinner;
	
	public Points(String n, int p, String dif, boolean b) {
		name = n;
		score = p;
		difficulty = dif;
		isWinner = b;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		date = dateFormat.format(new Date(System.currentTimeMillis()));
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public String getDate() {
		return date;
	}
	public boolean getIsWinner() {
		return isWinner;
	}
	
	public void setName(String s) {
		name = s;
	}
	public void setScore(int i) {
		score = i; 
	}
	public void setDifficulty(String s) {
		difficulty = s;
	}
	public void setDate(String s) {
		date = s;
	}
	public void setIsWinner(boolean b) {
		isWinner = b;
	}
}
