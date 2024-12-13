package com.example.demo;

import javafx.scene.Group;

/**
 * This class is responsible for displaying the game elements on the screen.
 */
public class LevelView {
	
	/**
	 * The x position of the heart display.
	 */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/**
	 * The y position of the heart display.
	 */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/**
	 * The x position of the win image.
	 */
	private static final int WIN_IMAGE_X_POSITION = 355;

	/**
	 * The y position of the win image.
	 */
	private static final int WIN_IMAGE_Y_POSITION = 175;

	/**
	 * The x position of the loss screen.
	 */
	private static final int LOSS_SCREEN_X_POSITION = 0;

	/**
	 * The y position of the loss screen.
	 */
	private static final int LOSS_SCREEN_Y_POSISITION = -290;

	/**
	 * The root of the scene.
	 */
	private final Group root;

	/**
	 * The win image.
	 */
	private final WinImage winImage;

	/**
	 * The game over screen.
	 */
	private final GameOverScreen gameOverScreen;

	/**
	 * The heart display.
	 */
	private final HeartDisplay heartDisplay;
	

	/**
	 * Creates a new LevelView object.
	 * @param root The root of the scene.
	 * @param heartsToDisplay The number of hearts to display.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverScreen = new GameOverScreen(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION, null);
	}
	

	/**
	 * Shows the heart display.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Shows the win image.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	/**
	 * Shows the game over image.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverScreen);
	}
	
	/**
	 * Removes hearts from the display.
	 * @param heartsRemaining The number of hearts remaining.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
