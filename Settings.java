package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings {
	protected boolean fullscreen = false;
	protected boolean sound = true;
	protected boolean music = true;
	
	
	protected void settingsMenu(Stage stage) {
		Scene mainscene = stage.getScene();
		VBox setBox= new VBox(7);
		setBox.setAlignment(Pos.CENTER);
		setBox.setStyle("-fx-background-color: LIME");
		
		
		Button wi = new Button("WINDOW");
		wi.setMinWidth(200);
		Button fs= new Button("FULLSCREEN");
		fs.setMinWidth(200);
		Button ex = new Button("BACK TO MENU");
		
		
		wi.setMinSize(75, 37.5);
        wi.setPrefSize(112.5, 37.5);
        wi.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 12;"
        		+ "-fx-text-fill: LIME");
        fs.setMinSize(75, 37.5);
        fs.setPrefSize(112.5, 37.5);
        fs.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 12;"
        		+ "-fx-text-fill: LIME");
        ex.setMinSize(75, 37.5);
        ex.setPrefSize(112.5, 37.5);
        ex.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 12;"
        		+ "-fx-text-fill: LIME");
        
        
        Text t = new Text("Window mode");
        setBox.getChildren().addAll(t, wi, fs, ex);
		Scene scene = new Scene(setBox);
		setBox.setAlignment(Pos.TOP_CENTER);
		setBox.setStyle("-fx-background-color: LIME");
		
		stage.setScene(scene);
		stage.setFullScreen(getFullScreen());
		stage.show();
		
		
		wi.setOnAction(e-> {
			fullscreen = false;
			stage.setFullScreen(false);
		});
		fs.setOnAction(e-> {
			fullscreen = true;
			stage.setFullScreen(true);
		});
		ex.setOnAction(e-> {
			stage.setScene(mainscene);
			stage.setFullScreen(fullscreen);
			stage.show();
			return;
		});
	}
	
	protected boolean isMusic() {
		return music;
	}
	
	protected boolean getFullScreen() {
		return fullscreen;
	}
}
