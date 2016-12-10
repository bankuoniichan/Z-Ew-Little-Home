package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.MouseUtility;

public class Block {
	/* isPastable == isEmpty ?? */
	protected boolean isPastable;
	private boolean isPickable;
	private boolean isEmpty;
	private Plate plate;
	private int x, y;

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
		this.plate = plate;
		isEmpty = false;
		isPastable = false;
	}

	public void remove() {
		isEmpty = true;
		plate = null;
		isPastable = true;
	}

	public void draw(GraphicsContext gc, int x, int y) {
		if (isEmpty) {
			gc.setFill(Color.ROSYBROWN);
			gc.fillRoundRect(x, y, 50, 50, 10, 10);
			gc.setStroke(Color.LIGHTGREEN);
			gc.strokeRoundRect(x, y, 50, 50, 10, 10);
		} else {
			plate.draw(gc, x, y);
		}
		this.x = x;
		this.y = y;

	}

	public boolean isMouseOver() {
		int mouseX = MouseUtility.getMouseX();
		int mouseY = MouseUtility.getMouseY();
		return (x <= mouseX && mouseX <= x + 50 && y <= mouseY && mouseY <= y + 50);
	}
}
