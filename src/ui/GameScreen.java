package ui;

import animation.PlateMoveAnimation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import object.Block;
import object.Board;
import object.NumberPlate;
import object.Plate;
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
	private PlateMoveAnimation holdingAnimation = null;

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
			if (!MouseUtility.isMousePressed()) {
				if (!holdingPlate) {
					Plate plate = null;
					Block[] blocks = selectField.getBlocks();
					for (Block b : blocks) {
						if (b.isMouseOver()) {
							plate = b.getPlate();
							b.setPlate(null);
							break;
						}
					}
					if (plate != null) {
						holdingPlate = true;
						holdingAnimation = new PlateMoveAnimation(this, gc, plate);
						holdingAnimation.start();
					}
				} else {
					for (Block[] blocks : board.getBlocks()) {
						for (Block b : blocks) {
							if (b.isMouseOver())
								b.setPlate(holdingAnimation.getPlate());
						}
					}
					holdingPlate = false;
					holdingAnimation.stop();
					drawBackgroundAndBoard();

				}
			}
			MouseUtility.setMousePressed(true);
		});
		this.setOnMouseReleased(event -> {
			MouseUtility.setMousePressed(false);
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
