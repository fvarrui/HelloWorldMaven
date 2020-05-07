package io.github.helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloWorldApp extends Application {
	
	private HelloWorldController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new HelloWorldController();
		
		Scene escena = new Scene(controller.getRoot());
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("HelloWorld");
		primaryStage.getIcons().add(new Image("images/world.png"));
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
