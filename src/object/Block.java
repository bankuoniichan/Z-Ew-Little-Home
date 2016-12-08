package object;

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

	public void remove() {
		isEmpty = true;
		plate = null;
		isPastable = true;
	}
}
