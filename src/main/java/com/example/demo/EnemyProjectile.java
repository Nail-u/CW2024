package com.example.demo;

/**
 * Class for enemy projectiles.
 */
public class EnemyProjectile extends Projectile {
	
	/**
	 * Image name for the enemy projectile.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";

	/**
	 * Constants for the enemy projectile.
	 */
	private static final int IMAGE_HEIGHT = 35;

	/**
	 * Horizontal velocity of the enemy projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructor for the enemy projectile.
	 * @param initialXPos The initial x position of the enemy projectile.
	 * @param initialYPos The initial y position of the enemy projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile.
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
