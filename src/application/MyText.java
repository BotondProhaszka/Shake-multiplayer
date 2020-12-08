package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * A sz�vegek egys�ges megjelenit�s��rt felel�s oszt�ly
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class MyText extends Text{
	//Konstruktor
	/**
	 * L�trehozza a sz�veget �s be�llitja a stilus�t
	 * @param text - A megjelenitend� sz�veg
	 * @param type - A sz�veg stilus�nak tipusa
	 *				�rt�ke lehet:	-"small":		Kis m�ret� sz�veg
	 *								-"medium":		K�zepes m. sz.
	 *								-"large":		Nagy m. sz.
	 *								-"title":		Cim m�ret�, hatlamsa sz�veg
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
