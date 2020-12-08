/**
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * A futtat�shoz telepitett JavaFX kell!
 * Futtat�shoz futtat�si argumentumk�nt meg kell adni az al�bbiakat (AZ EL�R�SI UTAT BE KELL HELYETTESIENI!!!):
 * 
 * --add-modules javafx.controls,javafx.fxml,javafx.controls,javafx.media
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 *
 * 
 * @author Proh�szka Botond  Bendeg�z - Nk.: DG1647 
 * 
 * Snake multiplayer j�t�k
 * K�sz�lt a  BME  M�rn�kinformatikus szak A Programoz�s Alapjai 3 c. t�rgy h�zi feladatak�nt 2020/2021 tan�v �szi f�l�v�ben.
 * 
 * 2020.11.28.
 *
 *
 * Megjegyz�s: a kommentekben �s a dokument�ci�ban minden '�' karakter helyett technikai okokb�l 'i' karakter van.
 */


package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Main oszt�ly, elinditja a j�t�ko, meghivja a sz�ks�ges f�ggv�nyeket
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
