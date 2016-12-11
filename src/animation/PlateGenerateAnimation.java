package animation;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import object.Plate;
import object.NumberPlate;

public class PlateGenerateAnimation extends AnimationTimer {
	private Plate plate;
	private GraphicsContext gc;
	private double x, y;
	private int label;
	private int count;

	public PlateGenerateAnimation(Plate plate, GraphicsContext gc, int x, int y) {
		this.plate = plate;
		this.gc = gc;
		label = ((NumberPlate) plate).getLabel();
		this.x = (double) x;
		this.y = (double) y;
		count = 0;
	}

	@Override
	public void handle(long now) {
		gc.setFill(Color.WHITESMOKE);
		gc.setStroke(Color.LAVENDER);
		gc.fillRoundRect(x + 0.1 * count * 25, y + 0.1 * count * 25, 0.1 * count * 50, 0.1 * count * 50,
				0.1 * count * 10, 0.1 * count * 10);

		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 0.1 * count * 30);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth("" + ((NumberPlate) plate).getLabel(), font);
		double fontHeight = fontLoader.getFontMetrics(font).getLineHeight();
		gc.setFont(font);
		gc.setFill(Color.DODGERBLUE);
		gc.fillText("" + label, x + (50 - fontWidth) / 2, y + (110 - fontHeight) / 2);
		if (count++ == 10)
			this.stop();
	}
}
