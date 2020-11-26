package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		       @Override
		       public void handle(WindowEvent e) {
		          Platform.exit();
		          System.exit(0);
		       }
		 });
		 MyInterface myif = new MyInterface(primaryStage);
		 myif.globalInit();

	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
