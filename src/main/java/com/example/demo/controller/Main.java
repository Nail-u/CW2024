package com.example.demo.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.MainMenu;

/**
 * Main class to start the game.
 */
public class Main extends Application {

	/**
	 * Screen width and height.
	 */
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;

	/**
	 * Title of the game.
	 */
	private static final String TITLE = "Sky Battle";

	/**
	 * Starts the game.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
				
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		MainMenu menu = new MainMenu();
		menu.start(stage);

	}

	/**
	 * Main method to launch the game.
	 * @param args The arguments.
	 */
	public static void main(String[] args) {
		launch();
	}
}