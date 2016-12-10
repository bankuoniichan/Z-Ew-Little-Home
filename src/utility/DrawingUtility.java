package utility;

import javafx.scene.canvas.GraphicsContext;
import ui.GameScreen;
import ui.Main;

public class DrawingUtility {
	private static GameScreen gameScreen = Main.instance.getGameScreen();
	private static GraphicsContext gc = Main.instance.getGameScreen().getCanvas().getGraphicsContext2D();

	public static GameScreen getGameScreen() {
		return gameScreen;
	}

	public static GraphicsContext getGraphicsContext() {
		return gc;
	}

}
