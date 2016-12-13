package ui;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class OptionPanel extends GridPane {

	private Button startButton = new Button("Start");
	private Spinner<Integer> colSpinner;
	private Spinner<Integer> rowSpinner;
	private Spinner<Integer> selSpinner;

	public OptionPanel() {
		super();

		this.setPrefHeight(150);
		this.setPrefWidth(600);
		this.setPadding(new Insets(20));

		HBox setRow = new HBox();
		HBox setCol = new HBox();
		HBox setting = new HBox();
		HBox buttonPane = new HBox();
		HBox setSel = new HBox();

		setRow.setPrefHeight(100);
		setRow.setPrefWidth(200);
		setCol.setPrefHeight(100);
		setCol.setPrefWidth(200);
		setSel.setPrefHeight(100);
		setSel.setPrefWidth(200);
		setting.setPrefHeight(100);
		setting.setPrefWidth(600);
		buttonPane.setPrefHeight(50);
		buttonPane.setPrefWidth(400);

		setRow.setPadding(new Insets(10));
		setCol.setPadding(new Insets(10));
		setSel.setPadding(new Insets(10));
		setting.setPadding(new Insets(10));
		buttonPane.setPadding(new Insets(10));

		Label rowLabel = new Label("Row");
		Label colLabel = new Label("Column");
		Label selLabel = new Label("Amount of\nSelect Box");
		rowLabel.setPrefWidth(50);
		colLabel.setPrefWidth(50);
		selLabel.setPrefWidth(70);

		rowSpinner = new Spinner<Integer>(2, 8, 5, 1);
		colSpinner = new Spinner<Integer>(2, 15, 5, 1);
		selSpinner = new Spinner<Integer>(1, 6, 4, 1);

		rowSpinner.setPrefWidth(90);
		colSpinner.setPrefWidth(90);
		selSpinner.setPrefWidth(90);

		setRow.getChildren().add(rowLabel);
		setRow.getChildren().add(rowSpinner);
		setCol.getChildren().add(colLabel);
		setCol.getChildren().add(colSpinner);
		setSel.getChildren().add(selLabel);
		setSel.getChildren().add(selSpinner);

		setting.getChildren().add(setRow);
		setting.getChildren().add(setCol);
		setting.getChildren().add(setSel);
		startButton.setPrefWidth(80);

		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().add(startButton);

		this.add(setting, 0, 0, 1, 1);
		this.add(buttonPane, 0, 1, 1, 1);

	}

	public Button getStartButton() {
		return startButton;
	}

	public int getColumnValue() {
		return colSpinner.getValue();
	}

	public int getRowValue() {
		return rowSpinner.getValue();
	}

	public int getSelValue() {
		int min = Integer.MAX_VALUE;
		if (getColumnValue() < selSpinner.getValue())
			min = getColumnValue();

		if (selSpinner.getValue() < min)
			min = selSpinner.getValue();
		
		return min;
	}
}
