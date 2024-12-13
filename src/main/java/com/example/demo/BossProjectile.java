package com.example.demo;

/**
 * A class representing a projectile fired by the boss.
 */
public class BossProjectile extends Projectile {
	/**
	 * The name of the image file for the boss projectile.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the image of the boss projectile.
	 */
	private static final int IMAGE_HEIGHT = 75;

	/**
	 * The horizontal velocity of the boss projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial x position of the boss projectile.
	 */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Creates a new BossProjectile with the given initial y position.
	 * @param initialYPos The initial y position of the boss projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the boss projectile.
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
