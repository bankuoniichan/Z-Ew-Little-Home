package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import object.Board;
import object.SelectField;
import utility.MouseUtility;

public class GameScreen extends StackPane {
	private int screenWidth, screenHeight;
	private int padding = 40;
	private Board board;
	private SelectField selectField;
	private Canvas canvas;
	private GraphicsContext gc;
	private boolean holdingPlate;

	public GameScreen(Board board) {
		super();
		this.board = board;
		screenWidth = calculateScreenWidth();
		screenHeight = calculateScreenHeight();
		holdingPlate = false;
		canvas = new Canvas(screenWidth, screenHeight);
		this.getChildren().add(canvas);
		drawBackgroundAndBoard();
		this.requestFocus();
		this.addEventListener();

	}

	public Board getBoard() {
		return board;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	private int calculateScreenWidth() {
		return 2 * padding + board.getWidth();
	}

	private int calculateScreenHeight() {
		return 2 * padding + board.getHeight() + 120;
	}

	private void addEventListener() {
		this.setOnMouseMoved(event -> {
			MouseUtility.setMouseX((int) event.getX());
			MouseUtility.setMouseY((int) event.getY());
		});
		this.setOnMousePressed(event -> {
			
		});
	}

	public void drawBackgroundAndBoard() {
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.GAINSBORO);
		gc.fillRect(0, 0, screenWidth, screenHeight);
		board.draw(gc, padding, padding);

		// 3 > next function to be dev.
		selectField = new SelectField(board, 3);
		selectField.draw(gc, padding, padding + 20 + board.getHeight());
	}
}
