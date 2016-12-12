package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import object.*;
import javafx.scene.Scene;

public class Main extends Application {
	public static final Main instance = new Main();

	private Stage primaryStage;
	private Scene gameScene;
	private Scene optionScene;
	private Board board;
	private OptionPane optionPane;
	private GameScreen gameScreen;
	private boolean isGameScreenShow;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		optionPane = new OptionPane();
		optionScene = new Scene(optionPane);
		isGameScreenShow = false;

		gameScreen = new GameScreen(new Board());
		gameScreen.requestFocus();
		gameScene = new Scene(gameScreen);

		primaryStage.setScene(optionScene);
		((OptionPane) (optionScene.getRoot())).getStartButton().setOnAction(e -> {
			toggleScene();
		});
		primaryStage.setTitle("Game Setting");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
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
			board = new Board(optionPane.getColumnValue(), optionPane.getRowValue());
			NumberPlate.setGenerateMax((int) Math.ceil(((double) (board.getColumn() + board.getRow())) / 2) - 1);
			gameScreen = new GameScreen(board);
			gameScreen.requestFocus();
			gameScene = new Scene(gameScreen);
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("PROG METH PROJECT-4 2016 ver. 7.00a");
			primaryStage.centerOnScreen();
		} else {
			primaryStage.setScene(optionScene);
			primaryStage.setTitle("Game Setting");
			primaryStage.centerOnScreen();
		}
	}
}
