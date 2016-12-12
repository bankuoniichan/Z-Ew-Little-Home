package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import animation.MergeAnimation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import ui.GameScreen;
import utility.AudioUtility;

public class NumberPlate {
	private static int generateMax = 4;
	private int label;
	private int x, y;
	private GraphicsContext gc;
	private static Random rand = new Random();

	public NumberPlate(int number) {
		label = number;
	}

	public int getLabel() {
		return label;
	}

	public void increaseLabel() {
		label++;
		if (label > generateMax)
			generateMax = label;
	}

	public boolean isSameLabel(NumberPlate otherPlate) {
		return this.label == otherPlate.label;
	}

	public void work(Board board, int x, int y, GameScreen gameScreen) {
		List<Block> nearBlocks = board.getNearBlocks(x, y);
		List<NumberPlate> sameLabelPlates = new ArrayList<>();
		for (Block block : nearBlocks) {
			if (!block.isEmpty())
				if (((NumberPlate) block.getPlate()).isSameLabel(this)) {
					sameLabelPlates.add((NumberPlate) block.getPlate());
					block.remove();
				}
		}

		if (sameLabelPlates.size() > 0) {
			AudioUtility.playMulti(sameLabelPlates.size());
			new MergeAnimation(this, sameLabelPlates, gameScreen, gc, board, x, y).start();
		} else {
			if (board.isFull()) {
				String showScore = "Your score is : " + gameScreen.getScore();
				Alert alert = new Alert(AlertType.INFORMATION, showScore, ButtonType.CLOSE);
				alert.setHeaderText(null);
				alert.setTitle("GGWP");
				alert.showAndWait();
			}
		}

	}

	public void draw(GraphicsContext gc, int x, int y) {
		this.gc = gc;

		gc.setFill(Color.rgb(118, 255, 3));
		gc.setStroke(Color.BLACK);
		gc.fillRoundRect(x, y, 50, 50, 10, 10);
		gc.strokeRoundRect(x, y, 50, 50, 10, 10);
		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth("" + label, font);
		double fontHeight = fontLoader.getFontMetrics(font).getLineHeight();
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		gc.fillText("" + label, x + (50 - fontWidth) / 2, y + (110 - fontHeight) / 2);

		this.x = x;
		this.y = y;

	}

	public static NumberPlate generateRandom() {
		return new NumberPlate(1 + rand.nextInt(generateMax - 1));
	}

	public static void setGenerateMax(int value) {
		generateMax = value;
	}

	public boolean isSamePosition(NumberPlate n) {
		return this.x == n.x && this.y == n.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
