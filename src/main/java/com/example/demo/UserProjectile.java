package com.example.demo;

/**
 * Interface for objects that can take damage and be destroyed.
 */
public class UserProjectile extends Projectile {

	/**
	 * The name of the image file.
	 */
	private static final String IMAGE_NAME = "userfire.png";

	/**
	 * The height of the image.
	 */
	private static final int IMAGE_HEIGHT = 125;

	/**
	 * The horizontal velocity of the projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructor for a user projectile.
	 * @param initialXPos The initial x position.
	 * @param initialYPos The initial y position.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	/**
	 * Updates the actor.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
