package com.example.demo;


/**
 * Abstract class for projectiles.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructor for a projectile.
	 * @param imageName The name of the image file.
	 * @param imageHeight The height of the image.
	 * @param initialXPos The initial x position.
	 * @param initialYPos The initial y position.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Takes damage to the projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 */
	@Override
	public abstract void updatePosition();

}
