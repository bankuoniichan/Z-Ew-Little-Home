package ui;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import animation.PlateMoveAnimation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import object.Block;
import object.Board;
import object.NumberPlate;
import object.SelectField;
import utility.MouseUtility;

public class GameScreen extends StackPane {
	private int screenWidth, screenHeight, centerGap;
	private int padding = 40;
	private Board board;
	private SelectField selectField;
	private Canvas canvas;
	private GraphicsContext gc;
	private boolean holdingPlate;
	private Block pickedBlock = null;
	private PlateMoveAnimation holdingAnimation = null;
	private int score;

	public int getScore() {
		return score;
	}

	public GameScreen(Board board) {
		super();
		this.board = board;
		selectField = new SelectField(board, 3);
		screenWidth = calculateScreenWidth();
		screenHeight = calculateScreenHeight();
		centerGap = 20;
		holdingPlate = false;
		canvas = new Canvas(screenWidth, screenHeight);
		this.getChildren().add(canvas);
		drawScreen();
		this.requestFocus();
		this.addEventListener();
		score = 0;
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
		return 2 * padding + board.getHeight() + 150;
	}

	private void addEventListener() {
		this.setOnMouseMoved(event -> {
			MouseUtility.setMouseX((int) event.getX());
			MouseUtility.setMouseY((int) event.getY());
		});
		this.setOnMousePressed(event -> {
			if (!MouseUtility.isMousePressed()) {
				if (!holdingPlate) {
					NumberPlate plate = null;
					Block[] blocks = selectField.getBlocks();
					for (Block b : blocks) {
						if (b.isMouseOver()) {
							plate = b.getPlate();
							b.remove();
							pickedBlock = b;
							break;
						}
					}
					if (plate != null) {
						holdingPlate = true;
						holdingAnimation = new PlateMoveAnimation(this, gc, plate);
						holdingAnimation.start();
					}
				} else {
					Block[][] blocks = board.getBlocks();
					for (int i = 0; i < board.getColumn(); i++) {
						for (int j = 0; j < board.getRow(); j++) {
							try {
								Block b = blocks[i][j];
								if (b.isMouseOver() && b.isEmpty()) {
									b.setPlate(holdingAnimation.getPlate());
									pickedBlock.setPlate(NumberPlate.generateRandom());
									holdingPlate = false;
									holdingAnimation.stop();
									drawScreen();
									((NumberPlate) b.getPlate()).work(board, i, j, this);
									return;
								}
							} catch (IndexOutOfBoundsException e) {
								System.out.println("x = " + i + " : " + board.getColumn() + " // y = " + j + " : "
										+ board.getRow() + " // ");
							}
						}
					}
					pickedBlock.setPlate(holdingAnimation.getPlate());
					holdingPlate = false;
					holdingAnimation.stop();
					drawScreen();

				}
			}
			MouseUtility.setMousePressed(true);
		});
		this.setOnMouseReleased(event -> {
			MouseUtility.setMousePressed(false);
		});
	}

	public void increaseScore(int amount) {
		score += amount;
	}

	public void drawScreen() {
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.rgb(220, 237, 200));
		gc.fillRect(0, 0, screenWidth, screenHeight);
		board.draw(gc, padding, padding);
		selectField.draw(gc, padding, padding + centerGap + board.getHeight());
		drawScore();
	}

	private void drawScore() {
		gc.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
		gc.setFill(Color.DODGERBLUE);
		gc.setStroke(Color.WHITE);
		String text = "Score : " + score;

		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth(text, gc.getFont());
		double fontHeight = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		int textLeftSidePadding = 10;
		int x = screenWidth - (textLeftSidePadding + (int) fontWidth);
		int y = (int) fontHeight;

		gc.fillText(text, x, y);
		gc.strokeText(text, x, y);
	}
}
