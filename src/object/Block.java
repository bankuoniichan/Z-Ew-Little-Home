package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block {
	/* isPastable == isEmpty ?? */
	protected boolean isPastable;
	private boolean isPickable;
	private boolean isEmpty;
	private Plate plate;

	public Block(boolean isPickable, boolean isPastable) {
		this.isPastable = isPastable;
		this.isPickable = isPickable;
		this.isEmpty = true;
		plate = null;
	}

	public boolean isPickable() {
		return isPickable;
	}

	public boolean isPastable() {
		return isPastable;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		if (isPastable) {
			this.plate = plate;
			isEmpty = false;
			isPastable = false;
		}
	}

	public void remove() {
		isEmpty = true;
		plate = null;
		isPastable = true;
	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.ROSYBROWN);
		gc.fillRoundRect(x, y, 50, 50, 10, 10);
		gc.setStroke(Color.BROWN);
		gc.strokeRoundRect(x, y, 50, 50, 10, 10);
	}
}
