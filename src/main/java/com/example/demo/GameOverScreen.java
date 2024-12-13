package com.example.demo;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * This class creates the game over screen that is displayed when the player loses the game.
 * It contains a background image, a game over image, a retry button and a quit button.
 * The retry button restarts the game and the quit button closes the game.
 */
public class GameOverScreen extends Pane {
	
	/**
	 * The image file name for the game over screen.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * The width of the screen.
	 */
	private static final int SCREEN_WIDTH = 1300;

	/**
	 * The height of the screen.
	 */
	private static final int SCREEN_HEIGHT = 750;

	/**
	 * The main menu object.
	 */
	private MainMenu mainMenu;
		
			/*
			 * Creates the game over screen.
			 */
			public GameOverScreen(double xPosition, double yPosition, MainMenu mainMenu) {
			this.mainMenu = mainMenu;
	
			/**
			 * The background rectangle for the game over screen.
			 */
			Rectangle background = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
			background.setOpacity(0.5);
	
			/**
			 * The game over image.
			 */
			ImageView gameoverimg = new ImageView(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
			gameoverimg.setPreserveRatio(true);
			gameoverimg.setSmooth(true);
			gameoverimg.setLayoutX((SCREEN_WIDTH - gameoverimg.getImage().getWidth()) / 2);
			gameoverimg.setLayoutY((SCREEN_HEIGHT - gameoverimg.getImage().getHeight()) / 2 - 50);

			/**
			 * The retry button.
			 */
			Button retryButton = new Button("Retry");
			retryButton.setOnAction(e -> retryGame());
			retryButton.setPrefSize(200, 50);
			retryButton.setLayoutX((SCREEN_WIDTH - retryButton.getPrefWidth()) / 2);
			retryButton.setLayoutY((SCREEN_HEIGHT - retryButton.getPrefHeight()) / 2 - 30);
	
			/**
			 * The quit button.
			 */
			Button quitButton = new Button("Quit");
			quitButton.setOnAction(e -> System.exit(0));
			quitButton.setPrefSize(200, 50);
			quitButton.setLayoutX((SCREEN_WIDTH - quitButton.getPrefWidth()) / 2);
			quitButton.setLayoutY((SCREEN_HEIGHT - quitButton.getPrefHeight()) / 2 + 30);
			
			/**
			 * Adds the background, game over image, retry button and quit button to the game over screen.
			 */
			this.getChildren().addAll(background, gameoverimg, retryButton, quitButton);

		}

		/**
		 * Restarts the game.
		 */
	private void retryGame() {
		mainMenu.startGame(mainMenu.getStage());
	}




}
