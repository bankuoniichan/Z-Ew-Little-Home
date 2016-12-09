package object;

import java.util.ArrayList;
import java.util.List;

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
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public List<NumberPlate> getNearPlates(int x, int y) {
		List<NumberPlate> list = new ArrayList<NumberPlate>();

		if (!blocks[x - 1][y].isEmpty()) {
			list.add((NumberPlate) blocks[x - 1][y].getPlate());
		}

		if (!blocks[x + 1][y].isEmpty()) {
			list.add((NumberPlate) blocks[x + 1][y].getPlate());
		}

		if (!blocks[x][y - 1].isEmpty()) {
			list.add((NumberPlate) blocks[x][y - 1].getPlate());
		}

		if (!blocks[x][y + 1].isEmpty()) {
			list.add((NumberPlate) blocks[x][y + 1].getPlate());
		}

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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRoundRect(x, y, width, height, 20, 20);
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				blocks[i][j].draw(gc, x + padding + i * (50 + gap), y + padding + j * (50 + gap));
			}
		}
	}
}
