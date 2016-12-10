package animation;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import object.Plate;
import ui.GameScreen;
import ui.Main;
import utility.MouseUtility;

public class PlateMoveAnimation extends AnimationTimer {
	private Plate plate;
	private GraphicsContext gc;
	private GameScreen gameScreen;

	public PlateMoveAnimation(GameScreen gameScreen, GraphicsContext gc, Plate plate) {
		this.plate = plate;
		this.gc = gc;
		this.gameScreen = gameScreen;
	}

	@Override
	public void handle(long currentTime) {
		gameScreen.drawBackgroundAndBoard();
		plate.draw(gc, MouseUtility.getMouseX()-25, MouseUtility.getMouseY()-25);
	}
	
	public Plate getPlate(){
		return plate;
	}
}
