package com.example.demo;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;


public class GameOverImage extends Pane {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private MainMenu mainMenu;
	private Stage primaryStage;
	private Controller retryController;
	private Main main;
		
		
			public GameOverImage(double xPosition, double yPosition, MainMenu mainMenu, Stage primaryStage) {
			this.mainMenu = mainMenu;
			this.primaryStage = primaryStage;
	
			Rectangle background = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
			background.setOpacity(0.5);
	
			ImageView gameoverimg = new ImageView(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
			gameoverimg.setPreserveRatio(true);
			gameoverimg.setSmooth(true);
			gameoverimg.setLayoutX((SCREEN_WIDTH - gameoverimg.getImage().getWidth()) / 2);
			gameoverimg.setLayoutY((SCREEN_HEIGHT - gameoverimg.getImage().getHeight()) / 2 - 50);

			Button retryButton = new Button("Retry");
			retryButton.setOnAction(e -> retryGame());
			retryButton.setPrefSize(200, 50);
			retryButton.setLayoutX((SCREEN_WIDTH - retryButton.getPrefWidth()) / 2);
			retryButton.setLayoutY((SCREEN_HEIGHT - retryButton.getPrefHeight()) / 2 - 30);
	
			Button quitButton = new Button("Quit");
			quitButton.setOnAction(e -> System.exit(0));
			quitButton.setPrefSize(200, 50);
			quitButton.setLayoutX((SCREEN_WIDTH - quitButton.getPrefWidth()) / 2);
			quitButton.setLayoutY((SCREEN_HEIGHT - quitButton.getPrefHeight()) / 2 + 30);
			
			this.getChildren().addAll(background, gameoverimg, retryButton, quitButton);

		}

	private void retryGame() {
		MainMenu restart = new MainMenu();
		restart.start(primaryStage);
	}




}
