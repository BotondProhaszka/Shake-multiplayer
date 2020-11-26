package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MyText extends Text{
	
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
