package application;

import javafx.scene.control.Button;

public class MyButton extends Button{
	
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
