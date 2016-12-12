package ui;

import java.util.Random;

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
	private OptionPanel optionPane;
	private GameScreen gameScreen;
	private boolean isGameScreenShow;
	private static Thread t1;
	private static Thread t2;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		optionPane = new OptionPanel();
		optionScene = new Scene(optionPane);
		isGameScreenShow = false;

		gameScreen = new GameScreen(new Board());
		gameScreen.requestFocus();
		gameScene = new Scene(gameScreen);

		t2 = new Thread(() -> {
			Random rand = new Random();
			AudioUtility.playPlaying(1 + rand.nextInt(5));
		});
		
		primaryStage.setScene(optionScene);
		((OptionPanel) (optionScene.getRoot())).getStartButton().setOnAction(e -> {
			// stop inital sound here pls
			t1.stop();
			t2.start();
			toggleScene();
		});
		primaryStage.setTitle("Game Setting");
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public static void main(String[] args) {

		t1 = new Thread(() -> {
			Random rand = new Random();
			AudioUtility.playWait(1 + rand.nextInt(5));
		});
		//t1.start();
		
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
