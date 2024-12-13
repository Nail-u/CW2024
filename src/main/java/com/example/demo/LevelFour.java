package com.example.demo;

/**
 * Level four of the game.
 */
public class LevelFour extends LevelParent {

	/**
	 * The name of the background image for this level.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/**
	 * The initial health of the player.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 10;

	/**
	 * The boss for this level.
	 */
	private final Boss boss;

	/**
	 * The view for this level.
	 */
	private LevelViewLevelFour levelView;


	/**
	 * Constructor for level four.
	 * @param screenHeight The height of the screen.
	 * @param screenWidth The width of the screen.
	 */
	public LevelFour(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}


	/**
	 * Initializes the friendly units for this level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns the enemy units for this level.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getRoot().getChildren().add(boss.getShieldImage());
		}
	}

	/**
	 * Instantiates the level view for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelFour(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
