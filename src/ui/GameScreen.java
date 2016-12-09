package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import object.Board;
import object.SelectField;

public class GameScreen extends StackPane {
	private int screenWidth, screenHeight;
	private int padding = 40;
	private Board board;
	private SelectField selectField;

	public GameScreen(Board board) {
		super();
		this.board = board;
		screenWidth = calculateScreenWidth();
		screenHeight = calculateScreenHeight();
		Canvas background = new Canvas(screenWidth, screenHeight);
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setFill(Color.GAINSBORO);
		gc.fillRect(0, 0, screenWidth, screenHeight);
		board.draw(gc, padding, padding);

		// 3 > next function to be dev. 
		selectField = new SelectField(board, 3);
		selectField.draw(gc, padding, padding + 20 + board.getHeight());

		this.getChildren().add(background);

	}

	public Board getBoard() {
		return board;
	}

	private int calculateScreenWidth() {
		return 2 * padding + board.getWidth();
	}

	private int calculateScreenHeight() {
		return 2 * padding + board.getHeight() + 120;
	}
}
