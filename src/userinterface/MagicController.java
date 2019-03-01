package userinterface;

import javax.swing.JOptionPane;

import customExceptions.NotOddException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.SquareMagic;

public class MagicController {

	@FXML
	private BorderPane borderPane;

	@FXML
	private javafx.scene.layout.Pane panelCentral;

	@FXML
	private TextField tfOrden;

	@FXML
	private TextField tfConstante;

	@FXML
	private Button btCrear;

	@FXML
	private GridPane gridPane;

	@FXML
	private ComboBox<String> cbPosition;

	@FXML
	private ComboBox<String> cbOrientation;

	boolean position = false;
	boolean orientation = false;

	SquareMagic squareMagic = new SquareMagic();

	@FXML
	public void initialize() {
		squareMagic = new SquareMagic();
		cbPosition.getItems().addAll(SquareMagic.UP, SquareMagic.DOWN, SquareMagic.LEFT, SquareMagic.RIGHT);
		cbPosition.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				position = true;
				cbOrientation.getItems().clear();
				if (newValue.equals(SquareMagic.UP)) {
					cbOrientation.getItems().addAll(SquareMagic.NO, SquareMagic.NE);
				} else if (newValue.equals(SquareMagic.DOWN)) {
					cbOrientation.getItems().addAll(SquareMagic.SO, SquareMagic.SE);
				} else if (newValue.equals(SquareMagic.LEFT)) {
					cbOrientation.getItems().addAll(SquareMagic.NO, SquareMagic.SO);
				} else if (newValue.equals(SquareMagic.RIGHT)) {
					cbOrientation.getItems().addAll(SquareMagic.SE, SquareMagic.NE);
				}
			}

		});
		cbOrientation.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				orientation = true;
			}
		});
	}

	@FXML
	public void preconditions(ActionEvent event) throws NotOddException {

		int order = 0;
		boolean pass = true;

		if (!(tfOrden.getText().equals(""))) {

			try {
				order = Integer.parseInt(tfOrden.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "The order number must be WHOLE", "Exception",
						JOptionPane.ERROR_MESSAGE);
				tfOrden.requestFocus();
				pass = false;
			}

			if (pass) {
				try {
					if (squareMagic.checkOdd(order)) {
						if (!position) {
							cbPosition.requestFocus();
						} else if (!orientation) {
							cbOrientation.requestFocus();
						} else {
							buildMatrix(order);
						}
					} else {
						tfOrden.requestFocus();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
				}
			}

		} else {
			tfOrden.requestFocus();
		}
	}

	public void buildMatrix(int order) {

		panelCentral.getChildren().remove(gridPane);
		gridPane = new GridPane();

		int[][] matrix = squareMagic.fillSquare(order, cbPosition.getValue(), cbOrientation.getValue());
		Button[][] buttonMatrix = new Button[order][order];
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++) {
				Button bt = new Button(String.valueOf(matrix[i][j]));
				bt.setId(String.valueOf(i) + "-" + String.valueOf(j));
				bt.setPrefWidth(50);
				gridPane.add(bt, j, i);
				buttonMatrix[i][j] = bt;
				bt.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						String id = bt.getId();
						String[] parts = id.split("-");
						String part1 = parts[0];
						String part2 = parts[1];
						int row = Integer.parseInt(part1);
						int colum = Integer.parseInt(part2);
						for (int k = 0; k < buttonMatrix.length; k++) {
							for (int l = 0; l < buttonMatrix[0].length; l++) {
								if (k == row) {
									buttonMatrix[k][l].setStyle("-fx-background-color: Yellow");
								}
								if (l == colum) {
									buttonMatrix[k][l].setStyle("-fx-background-color: Yellow");
								}
							}
						}
						Label lbC = new Label(String.valueOf(squareMagic.calculateConstant(order)));
						Label lbR = new Label(String.valueOf(squareMagic.calculateConstant(order)));
						gridPane.add(lbC, colum, order);
						gridPane.add(lbR, order, row);
					}
				});
			}
		}
		gridPane.layoutXProperty().bind(panelCentral.widthProperty().subtract(gridPane.widthProperty()).divide(2));
		gridPane.layoutYProperty().bind(panelCentral.heightProperty().subtract(gridPane.heightProperty()).divide(2));
		panelCentral.getChildren().add(gridPane);
		tfConstante.setText(String.valueOf(squareMagic.calculateConstant(order)));
	}

}
