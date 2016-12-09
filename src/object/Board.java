package object;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private Block[][] blocks;
	private int width;
	private int height;
	private int padding, gap;

	public Board() {
		this(5);
	}

	public Board(int size) {
		this(size, size);
	}

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		blocks = new Block[width][height];
		padding = 25;
		gap = 15;
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

		for (int x = 0; x < width; x++) {
			if (!blocks[x][y].isEmpty()) {
				list.add((NumberPlate) blocks[x][y].getPlate());
			}
		}

		return list;
	}

	public List<NumberPlate> getInColumnPlates(int x) {
		List<NumberPlate> list = new ArrayList<NumberPlate>();

		for (int y = 0; y < height; y++) {
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
		gc.fillRoundRect(x, y, 2*padding+(width-1)*gap+width*50, 2*padding+(height-1)*gap+height*50, 20, 20);
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				blocks[i][j].draw(gc, padding+i*(50+gap), padding+j*(50+gap));
			}
		}
	}
}
