package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;



public class Scoreboard {
	private List<Points> singleplayerList;
	private List<MultiplayerScoreLine> multiplayerList;
	private String filename;
	
	public Scoreboard(String s) {
		filename = s;
		singleplayerList = new ArrayList<Points>();
		multiplayerList = new ArrayList<MultiplayerScoreLine>();
	}
	

	public List<Points> getSinglePlayerList(){
		return singleplayerList;
	}
	
	public List<MultiplayerScoreLine> getMultiplayerList(){
		return multiplayerList;
	}
	
	public String getFileName() {
		return filename;
	}
	
	public void setSingleplayerList(List<Points> l) {
		singleplayerList = l;
	}
	public void setMultiplayerList(List<MultiplayerScoreLine> l) {
		multiplayerList = l;
	}
	public void setFilename(String s) {
		filename = s;
	}

	
	public void addPoints(List<Snake> snakes) {
		if(snakes.size() == 1) {
			Points p = new Points(snakes.get(0).getSnakeName(), snakes.get(0).getPoints(), snakes.get(0).getMap().getSettings().getDifString(), snakes.get(0).getWinner());
			singleplayerList.add(p);
		} else {
			Points p1 = new Points(snakes.get(0).getSnakeName(), snakes.get(0).getPoints(), snakes.get(0).getMap().getSettings().getDifString(), snakes.get(0).getWinner());
			Points p2 = new Points(snakes.get(1).getSnakeName(), snakes.get(1).getPoints(), snakes.get(1).getMap().getSettings().getDifString(), snakes.get(0).getWinner());
			MultiplayerScoreLine msl = new MultiplayerScoreLine(p1, p2);
			multiplayerList.add(msl);
		}
		
	}
	
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
	
	
	@SuppressWarnings("unchecked")
	public void readData() {
		try {
			File tempfile = new File(System.getProperty("user.dir") + File.separator + filename);
			if(tempfile.exists()) {			
			FileInputStream f = new FileInputStream(filename);
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
