package object;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Block[][] blocks;
	private int width;
	private int height;

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
	
	public void remove(int x,int y){
		blocks[x][y].remove();
	}
}
