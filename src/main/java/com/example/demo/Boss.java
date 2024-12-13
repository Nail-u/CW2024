package com.example.demo;

import java.util.*;

/**
 * Represents the boss enemy in the game. The boss has a unique move pattern and fires projectiles at a higher rate than other enemies.
 * The boss also has a shield that can be activated for a limited time.
 */
public class Boss extends FighterPlane {

	/**
	 * The name of the image file for the boss.
	 */
	private static final String IMAGE_NAME = "bossplane.png";
	 
	/**
	 * The initial x position of the boss.
	 */
	private static final double INITIAL_X_POSITION = 1000.0;

	/**
	 * The initial y position of the boss.
	 */
	private static final double INITIAL_Y_POSITION = 400;

	/**
	 * The y position offset for the boss's projectiles.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/**
	 * The rate at which the boss fires projectiles.
	 */
	private static final double BOSS_FIRE_RATE = .07;

	/**
	 * The probability that the boss will activate its shield.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = 0.02;

	/**
	 * The height of the boss's image.
	 */
	private static final int IMAGE_HEIGHT = 300;

	/**
	 * The vertical velocity of the boss.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The number of moves the boss makes in a single cycle.
	 */
	private static final int HEALTH = 50;

	/**
	 * The number of moves the boss makes in a single cycle.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private static final int ZERO = 0;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private static final int Y_POSITION_UPPER_BOUND = -100;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 475;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 100;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private final List<Integer> movePattern;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private boolean isShielded;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private int consecutiveMovesInSameDirection;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private int indexOfCurrentMove;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private int framesWithShieldActivated;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */
	private ShieldImage shield;

	/**
	 * The maximum number of frames the boss can have the shield activated.
	 */


	/**
	 * Creates a new boss enemy.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
		shield = new ShieldImage(getLayoutX(), getLayoutY());
	}

	/**
	 * Updates the position of the boss.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
		updateShieldPosition();
	}
	
	/**
	 * Updates the boss's position and shield.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile from the boss.
	 * @return The projectile fired by the boss.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}
	
	/**
	 * Takes damage to the boss.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Destroys the boss.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * if boss is shielded then increment the frames with shield activated
	 * Showinf the shield if the boss is shielded
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			shield.showShield();
		}
		else if (shieldShouldBeActivated()){
	 		activateShield();	
		}
		if (shieldExhausted()){
			deactivateShield();
			shield.hideShield();
		}
	}

	/**
	 * Creeating movement of the boss
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * The boss fires a projectile in the current frame with a certain probability.
	 * @return
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Returns the initial y position of the projectile.
	 * @return The initial y position of the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Returns whether the boss should activate its shield.
	 * @return Whether the boss should activate its shield.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Returns whether the boss's shield is exhausted.
	 * @return Whether the boss's shield is exhausted.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the boss's shield.
	 */
	private void activateShield() {
		isShielded = true;
		shield.showShield();
	}

	/**
	 * Deactivates the boss's shield.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		shield.hideShield();
	}

	/**
	 * Returns the shield image of the boss.
	 * @return The shield image of the boss.
	 */
	public ShieldImage getShieldImage() {
		return shield;
	}

	/**
	 * Updates the position of the boss's shield.
	 */
	private void updateShieldPosition() {
		shield.setLayoutX(getLayoutX() + getTranslateX() - 95);
		shield.setLayoutY(getLayoutY() + getTranslateY() );
	}


}
