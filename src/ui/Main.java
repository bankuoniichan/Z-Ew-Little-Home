package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import object.*;
import javafx.scene.Scene;

public class Main extends Application {
	private Board borad = new Board();
	public static final Main instance = new Main();

	private Stage primaryStage;
	private Scene gameScene;
	private Scene optionScene;

	private boolean isGameScreenShow;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		optionScene = new Scene(new OptionPane());
		GameScreen gameScreen = new GameScreen();
		gameScreen.requestFocus();
		gameScene = new Scene(gameScreen);
		isGameScreenShow = false;

		primaryStage.setScene(optionScene);
		primaryStage.setTitle("Game Setting");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Board getBoard() {
		return this.borad;
	}

	public void toggleScene() {
		isGameScreenShow = !isGameScreenShow;
		if (isGameScreenShow){
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game name");
		}
		else{
			primaryStage.setScene(optionScene);
			primaryStage.setTitle("Game Setting");
		}
	}
}
