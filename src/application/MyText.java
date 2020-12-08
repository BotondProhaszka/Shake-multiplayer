package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A szövegek egységes megjelenitéséért felelõs osztály
 * @author Prohászka Botond Bendegúz
 *
 */
public class MyText extends Text{
	//Konstruktor
	/**
	 * Létrehozza a szöveget és beállitja a stilusát
	 * @param text - A megjelenitendõ szöveg
	 * @param type - A szöveg stilusának tipusa
	 *				Értéke lehet:	-"small":		Kis méretû szöveg
	 *								-"medium":		Közepes m. sz.
	 *								-"large":		Nagy m. sz.
	 *								-"title":		Cim méretû, hatlamsa szöveg
	 */
	public MyText (String text, String type) {
		setText(text);
		int size = 0;
		switch(type) {
			case("small"): size = 13; break;
			case("medium"): size = 16; break;
			case("large"): size = 20; break;
			case("title"): size = 40; break;
			default: size = 16; break;
		}
		setFont(Font.font("impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, size));
		setFill(Color.BLACK);
	}
}
