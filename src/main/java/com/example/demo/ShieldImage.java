package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to represent the shield image.
 */
public class ShieldImage extends ImageView {
	
	/**
	 * The name of the image file.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";

	/**
	 * The size of the shield.
	 */
	private static final int SHIELD_SIZE = 200;
	
	/**
	 * Constructor for the shield image.
	 * @param xPosition
	 * @param yPosition
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		//this.setImage(new Image(IMAGE_NAME));
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Shows the shield.
	 */
	public void showShield() {
		this.setVisible(true);
	}
	
	/**
	 * Hides the shield.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

	/**
	 * Updates the position of the shield.
	 * @param xPosition The x position of the shield.
	 * @param yPosition The y position of the shield.
	 */
	public void updatePosition(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);	
		
	}

}
