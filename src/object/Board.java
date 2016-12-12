package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private Block[][] blocks;
	private int column, row;
	private int width, height;
	private int padding, gap;

	public Board() {
		this(5);
	}

	public Board(int size) {
		this(size, size);

	}

	public Board(int column, int row) {
		this.row = row;
		this.column = column;
		blocks = new Block[column][row];
		padding = 25;
		gap = 15;
		width = 2 * padding + (column - 1) * gap + column * 50;
		height = 2 * padding + (row - 1) * gap + row * 50;

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				blocks[i][j] = new Block(false, true);
			}
		}
		generateInitialPlates();
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public List<Block> getNearBlocks(int col, int row) {
		List<Block> list = new ArrayList<>();
		if (col - 1 >= 0)
			list.add(blocks[col - 1][row]);
		if (row - 1 >= 0)
			list.add(blocks[col][row - 1]);
		if (col + 1 < column)
			list.add(blocks[col + 1][row]);
		if (row + 1 < this.row)
			list.add(blocks[col][row + 1]);
		return list;
	}

	public List<NumberPlate> getInRowPlates(int y) {
		List<NumberPlate> list = new ArrayList<NumberPlate>();

		for (int x = 0; x < column; x++) {
			if (!blocks[x][y].isEmpty()) {
				list.add((NumberPlate) blocks[x][y].getPlate());
			}
		}

		return list;
	}

	public List<NumberPlate> getInColumnPlates(int x) {
		List<NumberPlate> list = new ArrayList<NumberPlate>();

		for (int y = 0; y < row; y++) {
			if (!blocks[x][y].isEmpty()) {
				list.add((NumberPlate) blocks[x][y].getPlate());
			}
		}

		return list;
	}

	public void remove(int x, int y) {
		blocks[x][y].remove();
	}

	public void place(Plate plate, int x, int y) {
		blocks[x][y].setPlate(plate);
	}

	public Plate getPlate(int x, int y) {
		return blocks[x][y].getPlate();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public void generateInitialPlates() {
		int blocksCount = column * row - 1;
		int platesCount = (int) Math.ceil((blocksCount) / 12);
		Random rand = new Random();
		while (platesCount > 0 && blocksCount >= 0) {
			if (blocksCount == 0) {
				if (platesCount > 0)
					blocks[0][0].setPlate(NumberPlate.generateRandom());
			} else if (rand.nextInt(blocksCount) < platesCount) {
				blocks[(blocksCount - (blocksCount % row)) / row][blocksCount % row]
						.setPlate(NumberPlate.generateRandom());
				platesCount--;
			}
			blocksCount--;
		}
	}

	public void draw(GraphicsContext gc, int x, int y) {
		//gc.setFill(Color.rgb(255, 255, 255));
		//gc.fillRoundRect(x, y, width, height, 20, 20);
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				blocks[i][j].draw(gc, x + padding + i * (50 + gap), y + padding + j * (50 + gap));
			}
		}
	}

	public boolean isFull() {
		for (Block[] block1 : this.blocks) {
			for (Block block2 : block1) {
				if (block2.isEmpty())
					return false;
			}
		}

		return true;
	}
}
