package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameScreen extends StackPane {
	private static final int CELL_SIZE = 50;
	private int screenWidth, screenHeight;
	private int padding = 40;
	
	public GameScreen() {
		super();
		screenWidth = calculateScreenWidth();
		screenHeight = calculateScreenHeight();
		Canvas background = new Canvas(screenWidth,screenHeight);
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setFill(Color.GAINSBORO);
		gc.fillRect(0, 0, screenWidth, screenHeight);
		Main.instance.getBoard().draw(gc, padding, padding);
		
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
