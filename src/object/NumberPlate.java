package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class NumberPlate extends Plate {
	private int label;
	private int x, y;

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
	}

	public void work(Board board) {
		List<NumberPlate> nearPlates = board.getNearPlates(this.x, this.y);
		int count = 0;
		boolean worked = false;
		List<Thread> listThread = new ArrayList<Thread>();

		for (NumberPlate plate : nearPlates) {
			if (this.isSameLabel(plate)) {
				count += 1;
				worked = true;
				board.remove(plate.x, plate.y);

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
			work(board);

	}

	public void draw(GraphicsContext gc, int x, int y) {
		gc.setFill(Color.WHITESMOKE);
		gc.setStroke(Color.LAVENDER);
		gc.fillRoundRect(x, y, 50, 50, 10, 10);

		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontWidth = fontLoader.computeStringWidth("" + label, font);
		double fontHeight = fontLoader.getFontMetrics(font).getLineHeight();
		gc.setFont(font);
		gc.setFill(Color.DODGERBLUE);
		gc.fillText("" + label, x + (50 - fontWidth) / 2, y + (110 - fontHeight) / 2);

	}
	
}
