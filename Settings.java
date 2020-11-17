package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings {
	protected boolean sound = true;
	protected boolean music = true;
	protected boolean multiplayer = true;
	protected int speed = 100;
	protected boolean gameRuns = true;
	
	
	
	
	protected boolean getSound() {
		return sound;
	}
	
	protected boolean getMusic() {
		return music;
	}
	
	protected boolean getMultiplayer() {
		return multiplayer;
	}
	
	protected int getSpeed() {
		return speed;
	}
	
	protected boolean getGameRuns() {
		return gameRuns;
	}
	
	

	
	protected void setSound(boolean b) {
		sound = b;
	}
	
	protected void setMusic(boolean b) {
		music = b;
	}
	
	protected void setMultiplayer(boolean b) {
		multiplayer = b;
	}
	
	protected void setGameRuns(boolean b) {
		gameRuns = b;
	}
	
	
	protected void settingsMenu(Stage stage) {
		Scene mainscene = stage.getScene();
		VBox setBox= new VBox(7);
		setBox.setAlignment(Pos.CENTER);
		setBox.setStyle("-fx-background-color: LIME");
		
		
		Button ex = new Button("BACK TO MENU");
		
        ex.setMinSize(75, 37.5);
        ex.setPrefSize(112.5, 37.5);
        ex.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 12;"
        		+ "-fx-text-fill: LIME");
        
        
        setBox.getChildren().addAll(ex);
		Scene scene = new Scene(setBox);
		setBox.setAlignment(Pos.TOP_CENTER);
		setBox.setStyle("-fx-background-color: LIME");
		
		stage.setScene(scene);
		stage.show();
		
		
		
		ex.setOnAction(e-> {
			stage.setScene(mainscene);
			stage.show();
			return;
		});
	}
	
	protected boolean isMusic() {
		return music;
	}
	
}
