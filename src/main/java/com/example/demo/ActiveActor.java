package com.example.demo;

import javafx.scene.image.*;

/**
 * This class represents an active actor in the game. It is an ImageView that can move around the screen.
 */
public abstract class ActiveActor extends ImageView {
	
	/**
	 * The location of the images for the actors.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Creates a new active actor with the given image name, image height, and initial position.
	 * @param imageName
	 * @param imageHeight
	 * @param initialXPos
	 * @param initialYPos
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor.
	 */
	public abstract void updatePosition();

	/**
	 * Updates the actor.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Updates the actor.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}

