package application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyInterface {
	protected Stage stage;
	protected Scene mainscene;
	protected Settings settings = new Settings();
	
	
	
	
	protected MyInterface (Stage s) {
		stage = s;
	}
	
	
	protected void globalInit() {
		
		stage.setTitle("Snake (v0.1b)");
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setMinHeight(600);
		stage.setMinWidth(800);		
		
		VBox parent = new VBox();
		parent.setSpacing(10);
		parent.setAlignment(Pos.TOP_CENTER);
		parent.setStyle("-fx-background-color: LIME");
		
		  
        
        Scene scene = new Scene(parent);
        
        Music m = new Music("menu", settings);
        Thread t = new Thread(m);
        t.run(); 
        
        //initMenubar(parent);
        initText(parent);
        initButtons(parent);
        
        	
        
        mainscene = scene;
        stage.setScene(scene);
        stage.show();      
        
        
        
	}
	
	protected void  initButtons(VBox parent) {
		///GAME
		Button gameButton = new Button("GAME");
		gameButton.setMinSize(100, 30);
		gameButton.setPrefSize(150, 50);
		gameButton.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 16;"
        		+ "-fx-text-fill: LIME");
		gameButton.setMnemonicParsing(true);
        
        
		gameButton.setOnAction(e -> {
        	Game g = new Game(settings);
        	g.initGame(stage);
        });
		parent.getChildren().add(gameButton);
		
		
        ///SETTINGS
        Button setButton = new Button("SETTINGS");
        setButton.setMinSize(100, 30);
        setButton.setPrefSize(150, 50);
        
        /*settings.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-background-color: BLACK, linear-gradient(RED, BLUE), linear-gradient(GRAY 0%, GRAY 49%, BLACK 50%, BLACK 100%);"
        		+ "-fx-font-size: 16;"
        		+ "-fx-text-fill: LIME");*/
        setButton.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 16;"
        		+ "-fx-text-fill: LIME");
        
        setButton.setOnAction(e -> {
        	settings.settingsMenu(stage);
        });

        parent.getChildren().add(setButton);
        
        ///EXIT
        Button exit = new Button("EXIT");
        exit.setMinSize(100, 30);
        exit.setPrefSize(150, 50);
        
        //exit.setCursor(Cursor.OPEN_HAND);
        
        
        
        exit.setStyle("-fx-background-color: BLACK;"
        		+ "-fx-font-weight: BOLD;"
        		+ "-fx-font-size: 16;"
        		+ "-fx-text-fill: LIME");
        exit.setOnAction(e -> {
        	stage.close();
        });

        parent.getChildren().add(exit);
        
        
        
        
	}
	
	protected void initMenubar(VBox parent) {
		 MenuBar menubar = new MenuBar();
	     Menu m1 = new Menu("SETTINGS");
	     m1.setStyle("-fx-font-weight: BOLD;"
	        		+ "-fx-font-size: 13;"
	        		+ "-fx-text-fill: LIME");
	     Menu ex = new Menu("EXIT");
	     ex.setStyle("-fx-font-weight: BOLD;"
	        		+ "-fx-font-size: 13;"
	        		+ "-fx-text-fill: LIME");   
	     
	     
	     RadioMenuItem rm1 = new RadioMenuItem("Fullscreen");
	     RadioMenuItem rm2 = new RadioMenuItem("Windowed");
	     rm2.setDisable(false);

	     ToggleGroup toggleGroup = new ToggleGroup();
	     toggleGroup.getToggles().add(rm1);
	     toggleGroup.getToggles().add(rm2);
	     
	     Menu subMenu = new Menu("Window mode");
	     subMenu.getItems().add(rm1);
	     subMenu.getItems().add(rm2);
	     m1.getItems().add(subMenu);
	     
	     
	     rm1.setOnAction(e-> {
	    	 stage.setFullScreen(true);
	     });
	     rm2.setOnAction(e-> {
	    	 stage.setFullScreen(false);
	     });
	     
	     ex.setOnAction(e-> {
	    	
	     });
	     
	     
	     menubar.getMenus().addAll(m1, ex);
	     menubar.setStyle("-fx-background-color: BLACK"); 
	     
	     
	     parent.getChildren().add(menubar);
	     
	}
	
	protected void initText(VBox parent) {
		Text text = new Text("Snake game");
		text.setFont(Font.font("impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 40));
		text.setFill(Color.BLACK);
		
		
		parent.getChildren().add(text);
	}
	
	
	
	
	protected void initMenuWindow() {
		
	}
}
