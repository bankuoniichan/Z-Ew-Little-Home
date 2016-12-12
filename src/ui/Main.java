package ui;

import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import object.*;
import utility.AudioUtility;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	public static final Main instance = new Main();

	private Stage primaryStage;
	private Scene gameScene;
	private Scene optionScene;
	private Board board;
	private OptionPanel optionPane;
	private GameScreen gameScreen;
	private boolean isGameScreenShow;

	@Override
	public void start(Stage primaryStage) {
		/*
		 * Random rand = new Random(); for(int i=0;i<100;i++)
		 * System.out.println(4+rand.nextInt(5));
		 */

		this.primaryStage = primaryStage;
		optionPane = new OptionPanel();
		optionScene = new Scene(optionPane);
		isGameScreenShow = false;

		gameScreen = new GameScreen(new Board(), optionPane.getSelectFieldValue());
		gameScreen.requestFocus();
		gameScene = new Scene(gameScreen);

		primaryStage.setScene(optionScene);
		((OptionPanel) (optionScene.getRoot())).getStartButton().setOnAction(e -> {
			
			toggleScene();
		});
		primaryStage.setTitle("Game Setting");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();

		AudioUtility.playWait(1);
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

			gameScreen = new GameScreen(board, optionPane.getSelectFieldValue());
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
