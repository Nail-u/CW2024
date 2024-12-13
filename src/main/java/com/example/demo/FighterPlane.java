package com.example.demo;

/**
 * Abstract class for fighter planes.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/**
	 * The health of the fighter plane.
	 */
	private int health;

	/**
	 * Constructor for the fighter plane.
	 * @param imageName The name of the image file.
	 * @param imageHeight The height of the image.
	 * @param initialXPos The initial x position.
	 * @param initialYPos The initial y position.
	 * @param health The health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile.
	 * @return The projectile that was fired.
	 */
	public abstract ActiveActorDestructible fireProjectile();
	
	/**
	 * Updates the health of the fighter plane.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Gets the x position of the projectile.
	 * @param xPositionOffset The x position offset.
	 * @return The x position of the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Gets the y position of the projectile.
	 * @param yPositionOffset The y position offset.
	 * @return The y position of the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health is at zero.
	 * @return Whether the health is at zero.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the health of the fighter plane.
	 * @return The health of the fighter plane.
	 */
	public int getHealth() {
		return health;
	}
		
}
