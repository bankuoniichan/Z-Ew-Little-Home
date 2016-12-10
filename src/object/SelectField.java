package object;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SelectField {

	private int width;
	private int height;
	private Block[] blocks;

	public SelectField(Board board, int n) {
		this.width = board.getWidth();
		this.height = 100;
		if (n < 1) {
			n = 1;
		}
		if (n > 4) {
			n = 4;
		}
		createBlocks(n);

	}

	public void createBlocks(int n) {
		blocks = new Block[n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			blocks[i] = new Block(true, false);
			blocks[i].setPlate(new NumberPlate(1+(int)(3*Math.random())));
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Block[] getBlocks() {
		return blocks;
	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRoundRect(x, y, width, height, 20, 20);
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].draw(gc, x + width / (blocks.length + 1) * (i + 1) - 25, y + 25);
		}
	}
}
