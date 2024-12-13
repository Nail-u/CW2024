package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class to display the win image.
 */
public class WinImage extends ImageView {
	
	/**
	 * The name of the image file.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";

	/**
	 * The height of the image.
	 */
	private static final int HEIGHT = 500;

	/**
	 * The width of the image.
	 */
	private static final int WIDTH = 600;
	
	/**
	 * Constructor for the win image.
	 * @param xPosition The x position of the win image.
	 * @param yPosition The y position of the win image.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}
	
	/**
	 * Shows the win image.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}

}
