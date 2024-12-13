package com.example.demo;

import java.util.List;

/**
 * Handles collisions between actors in the game.
 */
public class CollisionHandler {
	/*
	 * The lists of actors that can collide with each other.
	 */
    private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	/*
	 * The user plane and the screen width.
	 */
	private final UserPlane user;
	private final double screenWidth;

	/**
	 * Creates a new CollisionHandler.
	 * 
	 * @param friendlyUnits the list of friendly units
	 * @param enemyUnits the list of enemy units
	 * @param userProjectiles the list of user projectiles
	 * @param enemyProjectiles the list of enemy projectiles
	 * @param user the user plane
	 * @param screenWidth the screen width
	 */
	public CollisionHandler(List<ActiveActorDestructible> friendlyUnits, 
	List<ActiveActorDestructible> enemyUnits, 
	List<ActiveActorDestructible> userProjectiles,
	List<ActiveActorDestructible> enemyProjectiles, 
	UserPlane user, double screenWidth){

		this.friendlyUnits = friendlyUnits;
		this.enemyUnits = enemyUnits;
		this.userProjectiles = userProjectiles;
		this.enemyProjectiles = enemyProjectiles;
		this.user = user;
		this.screenWidth = screenWidth;
	}


	/**
	 * Handles collisions between actors in the game.
	 */
	public void handleCollisions() {
		handlePlaneCollisions();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handleEnemyPenetration();
	}


	/**
	 * Handles collisions between the user plane and enemy units.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles collisions between two lists of actors.
	 * 
	 * @param actors1 the first list of actors
	 * @param actors2 the second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Handles if enemy has gone past the user plane into the user's territory.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Checks if an enemy has penetrated the user's defenses.
	 * 
	 * @param enemy the enemy unit
	 * @return true if the enemy has penetrated the user's defenses, false otherwise
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}



}
