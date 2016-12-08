package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameScreen extends StackPane {
	private static final int CELL_SIZE = 50;
	private int screenWidth, screenHeight;
	public GameScreen() {
		super();
		screenWidth = calculateScreenWidth();
		screenHeight = calculateScreenHeight();
		Canvas background = new Canvas(screenWidth,screenHeight);
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRoundRect(0, 0, screenWidth, screenHeight, 20, 20);
		
		gc.setFill(Color.ROSYBROWN);
		gc.fillRoundRect(20, 20, CELL_SIZE, CELL_SIZE, 10, 10);
		
		this.getChildren().add(background);
		
	}
	private int calculateScreenWidth(){
		int boardColumn = Main.instance.getBoard().getWidth();
		return 50+30+10*(boardColumn-1)+CELL_SIZE*boardColumn;
	}
	private int calculateScreenHeight(){
		int boardRow = Main.instance.getBoard().getHeight();
		return 50+30+120+10*(boardRow-1)+CELL_SIZE*boardRow;
	}
}
