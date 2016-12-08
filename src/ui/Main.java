package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import object.*;
import javafx.scene.Scene;

public class Main extends Application {
	private Board borad = new Board();
	public static final Main instance = new Main();

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(new Scene(new OptionPane()));
		primaryStage.setTitle("Game Setting");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Board getBoard() {
		return this.borad;
	}
}
