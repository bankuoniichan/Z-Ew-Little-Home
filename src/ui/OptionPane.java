package ui;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;

public class OptionPane extends GridPane {

	private Button startButton = new Button("Start");
	private Spinner<Integer> colSpinner;
	private Spinner<Integer> rowSpinner;

	public Button getStartButton() {
		return startButton;
	}

	public OptionPane() {
		super();

		this.setPrefHeight(150);
		this.setPrefWidth(400);
		this.setPadding(new Insets(20));

		HBox setRow = new HBox();
		HBox setCol = new HBox();
		HBox setting = new HBox();
		HBox buttonPane = new HBox();

		setRow.setPrefHeight(100);
		setRow.setPrefWidth(200);
		setCol.setPrefHeight(100);
		setCol.setPrefWidth(200);
		setting.setPrefHeight(100);
		setting.setPrefWidth(400);
		buttonPane.setPrefHeight(50);
		buttonPane.setPrefWidth(400);

		setRow.setPadding(new Insets(10));
		setCol.setPadding(new Insets(10));
		setting.setPadding(new Insets(10));
		buttonPane.setPadding(new Insets(10));

		Label rowLabel = new Label("Row");
		Label colLabel = new Label("Column");
		rowLabel.setPrefWidth(50);
		colLabel.setPrefWidth(50);

		rowSpinner = new Spinner<Integer>(3, 8, 5, 1);
		colSpinner = new Spinner<Integer>(3, 15, 5, 1);

		rowSpinner.setPrefWidth(90);
		colSpinner.setPrefWidth(90);

		setRow.getChildren().add(rowLabel);
		setRow.getChildren().add(rowSpinner);
		setCol.getChildren().add(colLabel);
		setCol.getChildren().add(colSpinner);

		setting.getChildren().add(setRow);
		setting.getChildren().add(setCol);

		startButton.setPrefWidth(80);
		
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().add(startButton);

		this.add(setting, 0, 0, 1, 1);
		this.add(buttonPane, 0, 1, 1, 1);

	}
	public int getColumnValue(){
		return colSpinner.getValue();
	}
	public int getRowValue(){
		return rowSpinner.getValue();
	}
}
