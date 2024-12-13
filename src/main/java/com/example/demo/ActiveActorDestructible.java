package com.example.demo;

/**
 * An abstract class that represents an actor that can be destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * A boolean that represents whether the actor has been destroyed.
	 */
	private boolean isDestroyed;

	/**
	 * Creates a new ActiveActorDestructible with the given image name, image height, and initial x and y positions.
	 * @param imageName The name of the image file to use for this actor.
	 * @param imageHeight The height of the image to use for this actor.
	 * @param initialXPos The initial x position of this actor.
	 * @param initialYPos The initial y position of this actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor.
	 */
	public abstract void updateActor();

	/**
	 * Takes damage to the actor.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets whether the actor has been destroyed.
	 * @param isDestroyed Whether the actor has been destroyed.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	
	/**
	 * Returns whether the actor has been destroyed.
	 * @return Whether the actor has been destroyed.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
