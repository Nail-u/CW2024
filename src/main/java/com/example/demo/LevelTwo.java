package com.example.demo;

/**
 * Level two of the game.
 */
public class LevelTwo extends LevelParent {

    /**
     * Boolean to check if the level is transitioning.
     */
	private boolean LevelTransition = false;
	
    /**
     * String representing the background image name.
     */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/leveltwobg.jpg";
    
    /**
     * String representing the next level.
     */
	private static final String NEXT_LEVEL = "com.example.demo.LevelThree";

    /**
     * Integer representing the total number of enemies.
     */
	private static final int TOTAL_ENEMIES = 10;

    /**
     * Integer representing the number of kills to advance.
     */
	private static final int KILLS_TO_ADVANCE = 10;

    /**
     * Double representing the enemy spawn probability.
     */
	private static final double ENEMY_SPAWN_PROBABILITY = .20;

    /**
     * Integer representing the player's initial health.
     */
	private static final int PLAYER_INITIAL_HEALTH = 10;


    /**
     * Constructor for level two.
     * @param screenHeight The height of the screen.
     * @param screenWidth The width of the screen.
     */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}


    /**
     * Checks if the game is over.
     */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget() && !LevelTransition) {
			goToNextLevel(NEXT_LEVEL);
			LevelTransition = true;
		}	
	}


    /**
     * Initializes the friendly units for this level.
     */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}


    /**
     * Spawns the enemy units for this level.
     */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}


    /**
	 * Gets the number of health points the player has
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

    /**
	 * Checks if the user has reached the kill target.
	 * @return True if the user has reached the kill target, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}