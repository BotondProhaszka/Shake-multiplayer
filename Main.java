package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		 MyInterface myif = new MyInterface(primaryStage);
		 myif.globalInit();

	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
