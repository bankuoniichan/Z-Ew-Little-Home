package object;

import java.util.ArrayList;
import java.util.List;

import ui.Main;

public class NumberPlate extends Plate {
	private int label;
	private int x, y;
	// private boolean isMerge;

	public NumberPlate(int number) {
		label = number;
	}

	public int getLabel() {
		return label;
	}

	public boolean isSameLabel(NumberPlate otherPlate) {
		return this.label == otherPlate.label;
	}

	public void place(Board board,int x, int y) {
		this.x = x;
		this.y = y;
		board.place(this, x, y);
		work();
	}

	public void work() {
		List<NumberPlate> nearPlates = Main.instance.getBoard().getNearPlates(this.x, this.y);
		int count = 0;
		boolean worked = false;
		List<Thread> listThread = new ArrayList<Thread>();

		for (NumberPlate plate : nearPlates) {
			if (this.isSameLabel(plate)) {
				count += 1;
				worked = true;
				Main.instance.getBoard().remove(plate.x, plate.y);

				listThread.add(new Thread(() -> {
					/*
					 * fill code >> Merge it
					 */
				}));

			}
		}

		this.label += count;
		listThread.add(new Thread(() -> {
			/*
			 * fill code >> increase number >> draw label effect if count > 1
			 */
		}));

		if (worked)
			work();

	}

}
