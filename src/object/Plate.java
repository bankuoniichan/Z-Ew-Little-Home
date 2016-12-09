package object;

import javafx.scene.canvas.GraphicsContext;

public abstract class Plate {

	public abstract void work(Board board);

	public abstract void draw(GraphicsContext gc, int x, int y);

}
