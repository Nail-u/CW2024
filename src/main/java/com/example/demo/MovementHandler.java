package com.example.demo;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Class to handle the movement of the user plane.
 */
public class MovementHandler{

    /**
     * The user plane.
     */
    private UserPlane user;

    /**
     * The level.
     */
    private LevelParent level;
    

    /**
     * Creates a new MovementHandler.
     * 
     * @param user the user plane
     * @param level the level
     */
    public MovementHandler(UserPlane user, LevelParent level) {
        this.user = user;
        this.level = level;
    }


    /**
     * Fires a projectile.
     */
    private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		if (projectile != null) {
            level.spawningProjectile(projectile);
        }
	}


    /**
     * Handles key presses.
     * 
     * @param e the key event
     */
    public void KeyPress(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP) user.moveUp();
        if (kc == KeyCode.DOWN) user.moveDown();
        if (kc == KeyCode.SPACE) fireProjectile();
        if (kc == KeyCode.RIGHT) user.moveRight();
        if (kc == KeyCode.LEFT) user.moveLeft();
    }


    /**
     * Handles key releases.
     * 
     * @param e the key event
     */
    public void KeyRelease(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.verticalStop();
        if (kc == KeyCode.RIGHT || kc == KeyCode.LEFT) user.horizontalStop();
    }


}
