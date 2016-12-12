package utility;

import javafx.scene.canvas.GraphicsContext;
import ui.GameScreen;
import ui.Main;

public class DrawingUtility {
	public static final int CELL_SIZE = 50;
	public static final int CELL_ARC = 10;
	
	private static GameScreen gameScreen;
	private static GraphicsContext gc;

	public static GameScreen getGameScreen() {
		return gameScreen;
	}

	public static GraphicsContext getGraphicsContext() {
		return gc;
	}

}
