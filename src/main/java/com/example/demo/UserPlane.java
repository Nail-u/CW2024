package com.example.demo;

/**
 * Class representing the user-controlled plane.
 */
public class UserPlane extends FighterPlane {

	/**
	 * The name of the image file for the user plane.
	 */
	private static final String IMAGE_NAME = "userplane.png";

	/**
	 * The maximum the user plane can move up.
	 */
	private static final double Y_UPPER_BOUND = -40;

	/**
	 * The maximum the user plane can move down.
	 */
	private static final double Y_LOWER_BOUND = 600.0;

	/**
	 * The maximum the user plane can move right.
	 */
	private static final double X_UPPER_BOUND = 1125.0;

	/**
	 * The maximum the user plane can move left.
	 */
	private static final double X_LOWER_BOUND = 0.0;

	/**
	 * The initial x position of the user plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;

	/**
	 * The initial y position of the user plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;

	/**
	 * The height of the user plane image.
	 */
	private static final int IMAGE_HEIGHT = 150;

	/**
	 * The vertical velocity of the user plane.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The horizontal velocity of the user plane.
	 */
	private static final int HORIZONTAL_VELOCITY = 8;

	/**
	 * The x position offset for the user plane's projectiles.
	 */
	private static final int PROJECTILE_X_POSITION_OFFSET = 70;

	/**
	 * The y position offset for the user plane's projectiles.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	/**
	 * The multiplier for the vertical velocity of the user plane.
	 */
	private int verticalVelocityMultiplier;

	/**
	 * The multiplier for the horizontal velocity of the user plane.
	 */
	private int horizontalVelocityMultiplier;

	/**
	 * The number of kills the user plane has.
	 */
	private int numberOfKills;


	/**
	 * Constructor for the user plane.
	 * @param initialHealth The initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}
	

	/**
	 * Updates the position of the user plane.
	 */
	@Override
	public void updatePosition() {
		if (isMovingVertical()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newPositionY = getLayoutY() + getTranslateY();
			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontal()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX > X_UPPER_BOUND || newPositionX < X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}
	

	/**
	 * Fires a projectile.
	 * @return The projectile that was fired.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	

	/**
	 * Fires a projectile.
	 * @return The projectile that was fired.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}


	/**
	 * Checks if the user plane is moving horizontally.
	 * @return Whether the user plane is moving horizontally.
	 */
	private boolean isMovingHorizontal() {
		return horizontalVelocityMultiplier != 0;
	}


	/**
	 * Checks if the user plane is moving vertically.
	 * @return Whether the user plane is moving vertically.
	 */
	private boolean isMovingVertical() {
		return verticalVelocityMultiplier != 0;
	}

	/**
	 * Moves the user plane up.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane down.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user plane left.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user plane right.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops the user plane from moving vertically.
	 */
	public void verticalStop() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the user plane from moving horizontally.
	 */
	public void horizontalStop() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Gets the number of kills the user plane has.
	 * @return The number of kills the user plane has.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the number of kills the user plane has.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

}
