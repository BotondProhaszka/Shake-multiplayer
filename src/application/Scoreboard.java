package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A kor�bbi eredm�nyek kiir�s��rt, beolvas�s��rt �s azok t�rol�s��rt felel�s oszt�ly
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Scoreboard {
	//Adattagok
	/**
	 * Az egyj�t�kos m�dban el�rt eredm�nyek list�ja
	 */
	private List<Points> singleplayerList;
	/**
	 * A t�bbj�t�kos m�dban el�rt eredm�nyek list�ja
	 */
	private List<MultiplayerScoreLine> multiplayerList;
	/**
	 * A file neve, ahonnan az eredm�nyeket beolvasni �s ahova az eredm�nyeket kiirni kell
	 */
	private String filename;
	
	
	
	//Konstruktor
	/**
	 * Be�llitja a file nev�t �s l�trehozza a list�kat
	 * @param s - A file neve
	 */
	public Scoreboard(String s) {
		filename = s;
		singleplayerList = new ArrayList<Points>();
		multiplayerList = new ArrayList<MultiplayerScoreLine>();
	}
	
	
	
	//Getter
	/**
	 * Visszaadja a singleplayer eredm�nyek list�j�t
	 * @return - Az eredm�nyk list�ja
	 */
	public List<Points> getSinglePlayerList(){
		return singleplayerList;
	}
	/**
	 * Visszaadja a multiplayer eredm�nyek list�j�t
	 * @return - Az eredm�nyek list�ja
	 */
	public List<MultiplayerScoreLine> getMultiplayerList(){
		return multiplayerList;
	}
	/**
	 * Visszaadja a file nev�t
	 * @return - A file neve
	 */
	public String getFileName() {
		return filename;
	}
	
	
	
	//Setterek
	/**
	 * Be�llitja a singleplayer eredm�nyek list�j�t
	 * @param l - A be�llitand� lista
	 */
	public void setSingleplayerList(List<Points> l) {
		singleplayerList = l;
	}
	/**
	 * Be�llitja a multiplayer eredm�nyek list�j�t
	 * @param l - A be�llitand� lista
	 */
	public void setMultiplayerList(List<MultiplayerScoreLine> l) {
		multiplayerList = l;
	}
	/**
	 * Be�llitja a file nev�t
	 * @param s - A be�llitand� n�v
	 */
	public void setFilename(String s) {
		filename = s;
	}

	
	
	//Algoritmusok
	/**
	 * A param�terk�nt kapott kigy�k list�j�b�l kiveszi a pontokat �s a megfelel� list�hoz adja
	 * @param snakes - A kigy�k list�ja
	 */
	public void addPoints(List<Snake> snakes) {
		if(snakes.size() == 1) {
			Points p = new Points(snakes.get(0).getSnakeName(), snakes.get(0).getPoints(), snakes.get(0).getMap().getGame().getSettings().getDifString(), snakes.get(0).getWinner());
			singleplayerList.add(p);
		} else {
			Points p1 = new Points(snakes.get(0).getSnakeName(), snakes.get(0).getPoints(), snakes.get(0).getMap().getGame().getSettings().getDifString(), snakes.get(0).getWinner());
			Points p2 = new Points(snakes.get(1).getSnakeName(), snakes.get(1).getPoints(), snakes.get(1).getMap().getGame().getSettings().getDifString(), snakes.get(1).getWinner());
			MultiplayerScoreLine msl = new MultiplayerScoreLine(p1, p2);
			multiplayerList.add(msl);
		}
		
	}
	
	
	/**
	 * Kiirja az adatokat a fileba
	 */
	public void writeData() {
		try {
			FileOutputStream f = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(singleplayerList);
			out.writeObject(multiplayerList);
			out.close();
			
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
		
	}
	
	/**
	 * Beolvassa az adatokat
	 */
	@SuppressWarnings("unchecked")
	public void readData() {
		try {
			File tempfile = new File(System.getProperty("user.dir") + File.separator + filename);
			if(tempfile.exists()) {			
				FileInputStream f = new FileInputStream(filename);
				@SuppressWarnings("resource")
				ObjectInputStream in = new ObjectInputStream(f);
				singleplayerList = (List<Points>)in.readObject();
				multiplayerList = (List<MultiplayerScoreLine>)in.readObject();
			}
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		}
	}
}
