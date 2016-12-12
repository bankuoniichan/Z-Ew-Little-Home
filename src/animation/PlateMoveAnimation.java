package animation;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import object.NumberPlate;
import ui.GameScreen;
import utility.MouseUtility;

public class PlateMoveAnimation extends AnimationTimer {
	private NumberPlate plate;
	private GraphicsContext gc;
	private GameScreen gameScreen;

	public PlateMoveAnimation(GameScreen gameScreen, GraphicsContext gc, NumberPlate plate) {
		this.plate = plate;
		this.gc = gc;
		this.gameScreen = gameScreen;
	}

	@Override
	public void handle(long currentTime) {
		gameScreen.drawScreen();
		plate.draw(gc, MouseUtility.getMouseX()-25, MouseUtility.getMouseY()-25);
	}
	
	public NumberPlate getPlate(){
		return plate;
	}
}
