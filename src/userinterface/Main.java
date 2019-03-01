package userinterface;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		boolean start = true;
		boolean finish = false;
		String archive = "SquareMagic";
		do {
			try {
				Parent root = FXMLLoader.load(getClass().getResource(archive + ".fxml"));
				Scene scene = new Scene(root);
				stage.setTitle("Magic Square");
				stage.setScene(scene);
				start = true;
			} catch (NullPointerException e) {
				start = false;
				JOptionPane.showMessageDialog(null, "FILE " + archive + " NOT FOUND", "Exception",
						JOptionPane.ERROR_MESSAGE);
				int input = JOptionPane.showConfirmDialog(null, "Do you know and want to write the name of the file?",
						"Exception Manager", JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					archive = JOptionPane
							.showInputDialog("Write the name of the file fxml" + "\n(Without the extension .fmxl)");
				} else {
					start = true;
					finish = true;
				}
			}
		} while (!start);
		
		if (!finish)
			stage.show();
	}

}
