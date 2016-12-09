package object;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import ui.Main;

public class NumberPlate extends Plate {
	private int label;
	private int x, y;
	// private boolean isMerge;

	public NumberPlate(int number) {
		label = number;
	}

	public int getLabel() {
		return label;
	}

	public boolean isSameLabel(NumberPlate otherPlate) {
		return this.label == otherPlate.label;
	}

	public void place(Board board, int x, int y) {
		this.x = x;
		this.y = y;
		board.place(this, x, y);
		work();
	}

	public void work() {
		List<NumberPlate> nearPlates = Main.instance.getBoard().getNearPlates(this.x, this.y);
		int count = 0;
		boolean worked = false;
		List<Thread> listThread = new ArrayList<Thread>();

		for (NumberPlate plate : nearPlates) {
			if (this.isSameLabel(plate)) {
				count += 1;
				worked = true;
				Main.instance.getBoard().remove(plate.x, plate.y);

				listThread.add(new Thread(() -> {
					/*
					 * fill code >> Merge it
					 */
				}));

			}
		}

		this.label += count;
		listThread.add(new Thread(() -> {
			/*
			 * fill code >> increase number >> draw label effect if count > 1
			 */
		}));

		if (worked)
			work();

	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.WHITESMOKE);
		gc.setStroke(Color.LAVENDER);
		gc.fillRoundRect(x, y, 50, 50, 10, 10);
		
		Font font = Font.font("Tahoma", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth("" + label, font);
		double fontHeight = fontLoader.getFontMetrics(font).getLineHeight();
		gc.setFont(font);
		gc.setFill(Color.DODGERBLUE);
		gc.fillText(""+label, x+(50-fontWidth)/2, y+(50-fontHeight)/2);
		
	}
}
