# DMS Coursework 2024

## Name
Nail Utteenun 20595025

## Github
https://github.com/Nail-u/CW2024

## Compilation instruction
1. Ensure Java and Maven is installed on your computer
2. Clone the repository
3. Open project in preferred IDE
4. Compile the project
5. Run the project

## Implemented and working properly
- Logic for movement on the X-axis which also allows for diagonal movement
- The boss can activate shield and it is displayed when activated
- Level 3 where there are more enemies and more kills are required
- Level 4 which is a stronger level
- JUnit tests

## Implemented but not working properly
- A "retry" button after dying on a level was created but does not work. The problem might be that the scene of the level is not being called by the function

## Features not implemented 
- Pause Menu not implemented due to time constraints
- Sound FX not implemented due to time constraints and complexity
- Score counter not implemented due to time constraints

## New Java classes
- Collison Handler handles all the collision logic of the game
- Movement Handler handles all the movements logic and key presses
- LevelThree, additional level
- LevelFour. additional level
- GameOverScreen is showed when user dies and provides with the option to retry the level or quit the game
- MainMenu is the screen which greets the player when the game is ran


## Modified Java classes

## Removed
- GameOverImage

## Boss.java
- Added a method to show the shield and update its position relative to the boss

## LevelParent.java
- Destroyed Userplane instance on level up
- Moved functions to CollisionHandler.java and MovementHandler.java
- Added CollisionHandler instance to manage collisions
- Added MovementHandler instance to manage movement 

## Main.java
- Modified so that the MainMenu screen would show when program was ran

## LevelView.java
- Changed gameOverImage instance to gameOverScreen

## LevelOne.java
- Added LevelTransition boolean to ensure goToNextLevel() does not run repeatedly

## LevelTwo.java
- Added LevelTransition boolean to ensure goToNextLevel() does not run repeatedly
- Changed amount of health of player
- Increased enemy spawn rate
- Increased kill count number to level up

## LevelThree.java
- Added LevelTransition boolean to ensure goToNextLevel() does not run repeatedly
- Changed amount of health of player
- Increased enemy spawn rate
- Increased kill count number to level up

## LevelFour.java
- Changed boss' shield spawn probability

## LevelViewLevelTwo.java
- Removed shield instances and logic and moved them into boss class

## Userplane.java
- Added logic to enable horizontal movement
- Added logc to move the projectile fired relative to the movement of the plane

## Unexpected problems

- A few unexpected problems were encountered. One of which was the class diagram not being generated.
- The hitboxes of the actors were bigger than what they should have been. It was fixed as best as possible by cropping the images.
- There was a lot of difficulties in implementing the JUnit tests
