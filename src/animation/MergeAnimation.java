package animation;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import object.NumberPlate;
import object.Plate;
import ui.GameScreen;

public class MergeAnimation extends AnimationTimer {
	private List<NumberPlate> aroundPlates;
	private NumberPlate mainPlate, currentMovingPlate;
	private int dx, dy;
	private GameScreen gameScreen;
	private GraphicsContext gc;
	private int count = 0;

	public MergeAnimation(NumberPlate mainPlate, List<NumberPlate> aroundPlates, GameScreen gameScreen,
			GraphicsContext gc) {
		this.mainPlate = mainPlate;
		this.aroundPlates = aroundPlates;
		this.gc = gc;
		this.gameScreen = gameScreen;
		currentMovingPlate = aroundPlates.get(0);
		this.aroundPlates.remove(0);
		generateCurrentDirection();
	}

	@Override
	public void handle(long now) {
		if (count++ < 200)
			return;
		gameScreen.drawBackgroundAndBoard();
		currentMovingPlate.draw(gc, currentMovingPlate.getX() + dx, currentMovingPlate.getY() + dy);
		for (NumberPlate p : aroundPlates)
			p.draw(gc, p.getX(), p.getY());
		mainPlate.draw(gc, mainPlate.getX(), mainPlate.getY());
		if (currentMovingPlate.isSamePosition(mainPlate)) {
			mainPlate.increaseLabel();
			if (aroundPlates.size() == 0) {
				this.stop();
			} else {
				currentMovingPlate = aroundPlates.get(0);
				aroundPlates.remove(0);
				generateCurrentDirection();
			}
		}
	}

	private void generateCurrentDirection() {
		if (currentMovingPlate.getX() > mainPlate.getX()) {
			dx = -1;
			dy = 0;
		} else if (currentMovingPlate.getX() < mainPlate.getX()) {
			dx = 1;
			dy = 0;
		} else if (currentMovingPlate.getY() > mainPlate.getY()) {
			dx = 0;
			dy = -1;
		} else {
			dx = 0;
			dy = 1;
		}
	}
}
