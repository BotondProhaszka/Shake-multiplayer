package application;

import javafx.scene.control.Button;
/**
 * A gombok egys�ges kin�zet��rt felel�s oszt�ly, egyszer�siti a gombok l�trehoz�s�t
 * @author Pro�hszka Botond Bendeg�z
 *
 */
public class MyButton extends Button{
	//Konstruktor
	/**
	 * L�trehozza a gombot �s be�llitja a kin�zet�t
	 * @param s - A gombon olvashat� sz�veg
	 * @param type - A l�trehozand� gomb m�ret�t be�llito karaktersorozat.
	 * 				�rt�ke lehet:	-"small":	Kis gombot hoz l�tre
	 * 								-"medium":	K�zepes m�ret� gombot hoz l�tre
	 * 								-"large":	Nagy gombot hoz l�tre
	 */
	public MyButton(String s, String type) {
		int fontSize = 0;
		if(type.equals("small")){
			setMinSize(60, 30);
			setPrefSize(60, 30);
			fontSize = 10;
		} else if(type.equals("medium")) {
			setMinSize(80, 40);
			setPrefSize(80, 40);
			fontSize = 14;
		} else if(type.equals("large")) {
			setMinSize(160, 45);
			setPrefSize(160, 45);
			fontSize = 18;
		}
		setText(s);
		setStyle( "-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
				+ "-fx-font-size: " + fontSize + ";"
        		+ "-fx-text-fill: LIME");
		setFocusTraversable(false);
	}
	
}
