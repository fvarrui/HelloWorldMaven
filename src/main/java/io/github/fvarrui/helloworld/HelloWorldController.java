package io.github.fvarrui.helloworld;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloWorldController implements Initializable {
	
	@FXML
	private VBox root;
	
	@FXML
	private Label infoLabel, pathLabel;
	
	public HelloWorldController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HelloWorldView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		File info = new File("info.txt");
		pathLabel.setText(info.getAbsolutePath());

		try {
			String content = FileUtils.readFileToString(info, Charset.forName("UTF-8"));
			infoLabel.setText(content);
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error reading file " + info.getAbsolutePath());
			alert.setContentText(e.getMessage());
		}
		
	}
	
	public VBox getRoot() {
		return root;
	}

}
