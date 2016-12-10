package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import animation.MergeAnimation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import ui.GameScreen;
import ui.Main;
import utility.DrawingUtility;

public class NumberPlate extends Plate {
	private int label;
	private int x, y;
	private GraphicsContext gc;
	private GameScreen gameScreen;
	private static Random rand = new Random();

	public NumberPlate(int number) {
		label = number;
	}

	public int getLabel() {
		return label;
	}

	public void increaseLabel() {
		label++;
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
			new MergeAnimation(this, sameLabelPlates, gameScreen, gc, board, x, y).start();
		}

	}

	public void draw(GraphicsContext gc, int x, int y) {
		this.gc = gc;
		gc.setFill(Color.WHITESMOKE);
		gc.setStroke(Color.LAVENDER);
		gc.fillRoundRect(x, y, 50, 50, 10, 10);

		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth("" + label, font);
		double fontHeight = fontLoader.getFontMetrics(font).getLineHeight();
		gc.setFont(font);
		gc.setFill(Color.DODGERBLUE);
		gc.fillText("" + label, x + (50 - fontWidth) / 2, y + (110 - fontHeight) / 2);

		this.x = x;
		this.y = y;

	}

	public static NumberPlate generateRandom() {
		return new NumberPlate(1 + rand.nextInt(3));
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
