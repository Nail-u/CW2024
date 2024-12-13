package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;


/**
 * This class is the parent class for all levels in the game. 
 */
public abstract class LevelParent extends Observable {

	/**
	 * The height of the screen.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * The delay between each frame of the game.
	 */
	private static final int MILLISECOND_DELAY = 50;

	/**
	 * The height of the scene.
	 */
	private final double screenHeight;

	/**
	 * The width of the screen.
	 */
	private final double screenWidth;

	/**
	 * The maximum y position of the enemy units.
	 */
	private final double enemyMaximumYPosition;

	/**
	 * The root of the scene.
	 */
	private final Group root;

	/**
	 * The timeline of the game.
	 */
	private final Timeline timeline;

	/**
	 * The user plane.
	 */
	private final UserPlane user;

	/**
	 * The scene of the game.
	 */
	private final Scene scene;

	/**
	 * The background image of the game.
	 */
	private final ImageView background;

	/**
	 * The current number of enemies.
	 */
	private int currentNumberOfEnemies;

	/**
	 * The level view of the game.
	 */
	private LevelView levelView;


	/**
	 * The list of objects on a level.
	 */
	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;


	/**
	 * Collision logic for the game.
	 */
	private final CollisionHandler collisionHandler; // Added the collision handler

	/**
	 * Movement logic for the game.
	 */
	private MovementHandler movementHandler; // Added the movement handler


	/**
	 * Constructor for the parent level.
	 * @param backgroundImageName The name of the background image.
	 * @param screenHeight The height of the screen.
	 * @param screenWidth The width of the screen.
	 * @param playerInitialHealth The initial health of the player.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);

		/**
		 * Added the collision handler
		 */
		this.collisionHandler = new CollisionHandler(friendlyUnits, enemyUnits, userProjectiles, enemyProjectiles, user,
				screenWidth);

		friendlyUnits.add(user);

		/**
		 * Added the movement handler
		 */
		movementHandler = new MovementHandler(user, this);
	}

	/**
	 * Initializes the friendly units for this level.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game is over.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns the enemy units for this level.
	 */
	protected abstract void spawnEnemyUnits();


	/**
	 * Instantiates the level view for this level.
	 * @return The level view for this level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene for the level.
	 * @return The scene for the level.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Goes to next level.
	 */
	public void goToNextLevel(String levelName) {
		user.destroy();
		setChanged();
		notifyObservers(levelName);
	}


	/**
	 * Updates the scene.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		collisionHandler.handleCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the timeline for the game.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background of the game.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(movementHandler :: KeyPress); //use function from movement handler
		background.setOnKeyReleased(movementHandler :: KeyRelease); //use function from movement handler
		root.getChildren().add(background);
	}

	/**
	 * Spawns a projectile.
	 * @param projectile The projectile to spawn.
	 */
	public void spawningProjectile(ActiveActorDestructible projectile) {
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile.
	 * @param projectile The projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the actors in the game.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes the actors that are destroyed.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes the destroyed actors.
	 * @param actors The list of actors.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	
	/**
	 * Removes hearts after user has collided with an enemy projectile, an enemy, or had its defence penetrated.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the kill count.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Shows the win image.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Shows the game over image.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Gets the user plane.
	 * @return The user plane.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Gets the root of the scene.
	 * @return The root of the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Gets the current number of enemies.
	 * @return The current number of enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit.
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Gets the enemy maximum y position.
	 * @return The enemy maximum y position.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Gets the screen width.
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user is destroyed.
	 * @return True if the user is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the number of enemies.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
