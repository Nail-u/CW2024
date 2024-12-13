package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class that represents the display of the hearts in the game.
 */
public class HeartDisplay {
	
	/**
	 * Constants for the heart image.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/**
	 * Constants for the heart display.
	 */
	private static final int HEART_HEIGHT = 50;

	/**
	 * Constants for the heart display.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * The container for the hearts.
	 */
	private HBox container;

	/**
	 * The x position of the container.
	 */
	private double containerXPosition;

	/**
	 * The y position of the container.
	 */
	private double containerYPosition;

	/**
	 * The number of hearts to display.
	 */
	private int numberOfHeartsToDisplay;
	
	/**
	 * Constructor for the heart display.
	 * @param xPosition The x position of the container.
	 * @param yPosition The y position of the container.
	 * @param heartsToDisplay The number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}
	
	/**
	 * Initializes the container for the hearts.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}
	
	/**
	 * Initializes the hearts.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}
	
	/**
	 * Removes a heart from the display.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}
	
	public HBox getContainer() {
		return container;
	}

}
