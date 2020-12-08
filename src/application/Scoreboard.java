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
 * A korábbi eredmények kiirásáért, beolvasásáért és azok tárolásáért felelõs osztály
 * @author Prohászka Botond Bendegúz
 *
 */
public class Scoreboard {
	//Adattagok
	/**
	 * Az egyjátékos módban elért eredmények listája
	 */
	private List<Points> singleplayerList;
	/**
	 * A többjátékos módban elért eredmények listája
	 */
	private List<MultiplayerScoreLine> multiplayerList;
	/**
	 * A file neve, ahonnan az eredményeket beolvasni és ahova az eredményeket kiirni kell
	 */
	private String filename;
	
	
	
	//Konstruktor
	/**
	 * Beállitja a file nevét és létrehozza a listákat
	 * @param s - A file neve
	 */
	public Scoreboard(String s) {
		filename = s;
		singleplayerList = new ArrayList<Points>();
		multiplayerList = new ArrayList<MultiplayerScoreLine>();
	}
	
	
	
	//Getter
	/**
	 * Visszaadja a singleplayer eredmények listáját
	 * @return - Az eredményk listája
	 */
	public List<Points> getSinglePlayerList(){
		return singleplayerList;
	}
	/**
	 * Visszaadja a multiplayer eredmények listáját
	 * @return - Az eredmények listája
	 */
	public List<MultiplayerScoreLine> getMultiplayerList(){
		return multiplayerList;
	}
	/**
	 * Visszaadja a file nevét
	 * @return - A file neve
	 */
	public String getFileName() {
		return filename;
	}
	
	
	
	//Setterek
	/**
	 * Beállitja a singleplayer eredmények listáját
	 * @param l - A beállitandó lista
	 */
	public void setSingleplayerList(List<Points> l) {
		singleplayerList = l;
	}
	/**
	 * Beállitja a multiplayer eredmények listáját
	 * @param l - A beállitandó lista
	 */
	public void setMultiplayerList(List<MultiplayerScoreLine> l) {
		multiplayerList = l;
	}
	/**
	 * Beállitja a file nevét
	 * @param s - A beállitandó név
	 */
	public void setFilename(String s) {
		filename = s;
	}

	
	
	//Algoritmusok
	/**
	 * A paraméterként kapott kigyók listájából kiveszi a pontokat és a megfelelõ listához adja
	 * @param snakes - A kigyók listája
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
