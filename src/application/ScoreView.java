package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * A dicsõséglisták megjelitéséért felelõs osztály
 * @author Prohászka BOtond Bendegúz
 *
 */
public class ScoreView{
	//Adattagok
	/**
	 * Az adatokat tartalmazó objektum
	 */
	private Scoreboard sb;
	
	
	
	//Konstruktor
	/**
	 * Bállitja a Scoreboard objektumot
	 * @param s - A beállitandó objektum
	 */
	public ScoreView(Scoreboard s) {
		sb = s;
	}
	
	
	
	//Getterek
	/**
	 * Visszaadja az adatokat tároló Scoreboard objektumot
	 * @return - Scoreboard objektum
	 */
	public Scoreboard getScoreboard() {
		return sb;
	}
	/**
	 * Visszaadja a singleplayer módban mentett adatok listáját
	 * @return - Az adatok listája
	 */
	public ObservableList<Points> getSingleplayerPoints(){
		ObservableList<Points> points = FXCollections.observableArrayList();
		for(int i = 0; i < sb.getSinglePlayerList().size(); i++)
			points.add(sb.getSinglePlayerList().get(i));
		return points;
	}
	/**
	 * Visszaadja a multiplayer módban mentett adatok listáját
	 * @return - Az adatok listája
	 */
	public ObservableList<MultiplayerScoreLine> getMultiplayerPoints(){
		ObservableList<MultiplayerScoreLine> points = FXCollections.observableArrayList();
		for(int i = 0; i < sb.getMultiplayerList().size(); i++)
			points.add(sb.getMultiplayerList().get(i));
		return points;
	}
	
	
	//Setterek
	/**
	 * Beállitja az adatokat tároló Scoreboard objektumot
	 * @param s - A beállitandó objektum
	 */
	public void setScoreboard(Scoreboard s) {
		sb = s;
	}
	
	
	
	//Algorimtusok
	/**
	 * Létrehozza a megjelenõ jelenetet
	 * @param stage - Az aktuális stage
	 */
	public void initScoreWindow(Stage stage) {
		Scene mainscene = stage.getScene();
		VBox parent = new VBox();
		parent.setSpacing(10);
		parent.setAlignment(Pos.TOP_CENTER);
		parent.setStyle("-fx-background-color: LIME");
		parent.setSpacing(5);
	    parent.setPadding(new Insets(10, 50, 50, 60));
		  
	    createSingleplayerTable(parent);
        createMultiplayerTable(parent);
	    
        Scene scene = new Scene(parent);
			
	    stage.setScene(scene);
	    stage.show();
	    
	    MyButton ex = new MyButton("BACK", "medium");
		ex.setOnAction(e-> {
			stage.setScene(mainscene);
			stage.show();
		});
		parent.getChildren().add(ex);
		
	}
	
	/**
	 * Létrehozza és megjeleniti a singleplayer pontok táblázatát
	 * @param parent - Az aktuális stage
	 */
	@SuppressWarnings("unchecked")
	public void createSingleplayerTable(VBox parent) {
		MyText singleplayerMyText = new MyText("SINGLEPLAYER", "medium");
		
		TableColumn<Points, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setPrefWidth(160);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Points, Integer> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setPrefWidth(70);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		TableColumn<Points, String> difficultyColumn = new TableColumn<>("Difficulty");
		difficultyColumn.setPrefWidth(100);
		difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
		
		TableColumn<Points, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setPrefWidth(160);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		
		TableView<Points> table = new TableView<>();
		table.setStyle("-fx-background-color: transparent");
		table.setEditable(false);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setMaxWidth(500);
		table.setItems(getSingleplayerPoints());
		table.getColumns().addAll(nameColumn, scoreColumn, difficultyColumn, dateColumn);
		parent.getChildren().addAll(singleplayerMyText, table);
	}
	
	/**
	 * Létrehozza és megjeleniti a multiplayer pontok táblázatát
	 * @param parent - Az aktuális stage
	 */
	@SuppressWarnings("unchecked")
	public void createMultiplayerTable(VBox parent) {
		MyText multiplayerTitle = new MyText("MULTIPLAYER", "medium");
		///1st player's name
		TableColumn<MultiplayerScoreLine, String> name1Column = new TableColumn<>("1st player's name");
		name1Column.setPrefWidth(200);
		name1Column.setCellValueFactory(new PropertyValueFactory<>("name1"));
		
		///1st player's score
		TableColumn<MultiplayerScoreLine, Integer> score1Column = new TableColumn<>("1st player's score");
		score1Column.setPrefWidth(130);
		score1Column.setCellValueFactory(new PropertyValueFactory<>("score1"));
		
		///2nd player's name
		TableColumn<MultiplayerScoreLine, String> name2Column = new TableColumn<>("2nd player's name");
		name2Column.setPrefWidth(200);
		name2Column.setCellValueFactory(new PropertyValueFactory<>("name2"));
		
		///2nd player's score
		TableColumn<MultiplayerScoreLine, Integer> score2Column = new TableColumn<>("2nd player's score");
		score2Column.setPrefWidth(130);
		score2Column.setCellValueFactory(new PropertyValueFactory<>("score2"));
		
		///Winner
		TableColumn<MultiplayerScoreLine, String> winnerColumn = new TableColumn<>("Winner");
		winnerColumn.setPrefWidth(70);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winner"));
		
		///Difficulty
		TableColumn<MultiplayerScoreLine, String> difficultyColumn = new TableColumn<>("Difficulty");
		difficultyColumn.setPrefWidth(100);
		difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));		
		
		///Date
		TableColumn<MultiplayerScoreLine, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setPrefWidth(200);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		
		TableView<MultiplayerScoreLine> table = new TableView<>();
		table.setStyle("-fx-background-color: transparent");
		table.setEditable(false);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setMaxWidth(700);
		table.setEditable(false);
		table.setItems(getMultiplayerPoints());
		table.getColumns().addAll(name1Column, score1Column, name2Column, score2Column, winnerColumn, difficultyColumn, dateColumn);
		parent.getChildren().addAll(multiplayerTitle, table);
	}
}
