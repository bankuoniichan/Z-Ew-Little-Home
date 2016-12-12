package animation;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import object.Board;
import object.NumberPlate;
import ui.GameScreen;

public class MergeAnimation extends AnimationTimer {
	private int dx, dy;
	private int x, y, score;
	private List<NumberPlate> aroundPlates;
	private NumberPlate mainPlate, currentMovingPlate;
	private GameScreen gameScreen;
	private Board board;
	private GraphicsContext gc;

	public MergeAnimation(NumberPlate mainPlate, List<NumberPlate> aroundPlates, GameScreen gameScreen,
			GraphicsContext gc, Board board, int x, int y) {
		this.mainPlate = mainPlate;
		this.aroundPlates = aroundPlates;
		this.gc = gc;
		this.board = board;
		this.x = x;
		this.y = y;
		this.score = mainPlate.getLabel() * (aroundPlates.size() + 1);
		this.gameScreen = gameScreen;
		currentMovingPlate = aroundPlates.get(0);
		this.aroundPlates.remove(0);
		generateCurrentDirection();
	}

	@Override
	public void handle(long now) {
		gameScreen.drawScreen();
		currentMovingPlate.draw(gc, currentMovingPlate.getX() + 5 * dx, currentMovingPlate.getY() + 5 * dy);
		for (NumberPlate p : aroundPlates)
			p.draw(gc, p.getX(), p.getY());
		mainPlate.draw(gc, mainPlate.getX(), mainPlate.getY());
		if (currentMovingPlate.isSamePosition(mainPlate)) {
			mainPlate.increaseLabel();
			if (aroundPlates.size() == 0) {
				gameScreen.increaseScore(score);
				gameScreen.drawScreen();
				this.stop();
				mainPlate.work(board, x, y, gameScreen);
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
