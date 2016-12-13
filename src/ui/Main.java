package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import object.*;
import utility.AudioUtility;
import javafx.scene.Scene;

public class Main extends Application {
	public static final Main instance = new Main();

	private Stage primaryStage;
	private Scene gameScene, optionScene;
	private Board board;
	private OptionPanel optionPanel;
	private GameScreen gameScreen;
	private boolean isGameScreenShow;
	private final int defaultSelectFieldValue = 3;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		optionPanel = new OptionPanel();
		optionScene = new Scene(optionPanel);
		isGameScreenShow = false;

		gameScreen = new GameScreen(new Board(), defaultSelectFieldValue);
		gameScreen.requestFocus();
		gameScene = new Scene(gameScreen);

		primaryStage.setScene(optionScene);
		((OptionPanel) (optionScene.getRoot())).getStartButton().setOnAction(e -> {
			AudioUtility.playPlaying();
			toggleScene();
		});
		primaryStage.setTitle("Game Setting");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
		AudioUtility.playWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void toggleScene() {
		isGameScreenShow = !isGameScreenShow;
		if (isGameScreenShow) {
			board = new Board(optionPanel.getColumnValue(), optionPanel.getRowValue());
			NumberPlate.setGenerateMax((int) Math.ceil(((double) (board.getColumn() + board.getRow())) / 2) - 1);
			gameScreen = new GameScreen(board, optionPanel.getSelValue());
			gameScreen.requestFocus();
			gameScene = new Scene(gameScreen);
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Mergit ver. 7.00a");
			primaryStage.centerOnScreen();
		} else {
			primaryStage.setScene(optionScene);
			primaryStage.setTitle("Game Setting");
			primaryStage.centerOnScreen();
		}
	}
}
