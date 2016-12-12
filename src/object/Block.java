package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.MouseUtility;

public class Block {
	/* isPastable == isEmpty ?? */
	protected boolean isPastable;
	private boolean isPickable;
	private boolean isEmpty;
	private NumberPlate plate;
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

	public NumberPlate getPlate() {
		return plate;
	}

	public void setPlate(NumberPlate plate) {
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
			gc.setFill(Color.rgb(30, 130, 76));
			gc.fillRoundRect(x, y, 50, 50, 10, 10);
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
