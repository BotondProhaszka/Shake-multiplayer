/**
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * A futtatáshoz telepitett JavaFX kell!
 * Futtatáshoz futtatási argumentumként meg kell adni az alábbiakat (AZ ELÉRÉSI UTAT BE KELL HELYETTESIENI!!!):
 * 
 * --add-modules javafx.controls,javafx.fxml,javafx.controls,javafx.media
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 *
 * 
 * @author Prohászka Botond  Bendegúz - Nk.: DG1647 
 * 
 * Snake multiplayer játék
 * Készült a  BME  Mérnökinformatikus szak A Programozás Alapjai 3 c. tárgy házi feladataként 2020/2021 tanév õszi félévében.
 * 
 * 2020.11.28.
 *
 *
 * Megjegyzés: a kommentekben és a dokumentációban minden 'í' karakter helyett technikai okokból 'i' karakter van.
 */


package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Main osztály, elinditja a játéko, meghivja a szükséges függvényeket
 */
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
