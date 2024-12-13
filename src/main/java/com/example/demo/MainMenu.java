package com.example.demo;

import java.lang.reflect.InvocationTargetException;
import com.example.demo.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.*;

/**
 * Main menu for the game.
 */
public class MainMenu extends Application {

    /**
     * The width of the screen.
     */
    private static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the screen.
     */
    private static final int SCREEN_HEIGHT = 750;
    
    /**
     * The background image of the menu.
     */
	private static final String BACKGROUND_IMAGE = "/com/example/demo/images/MenuBackground.jpg";

    /**
     * The controller for the MainMenu.
     */
    private Controller MenuController;

    /**
     * The primary stage for the MainMenu.
     */
    Stage primaryStage;

    /**
     * Launches the main menu.
     * @param args The arguments for the main menu.
     */
    @Override
    public void start(Stage primaryStage) {

        /**
         * The start game button.
         */
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startGame(primaryStage));
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

        
        /**
         * The quit button.
         */
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());
        quitButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

        /**
         * The layout for the main menu.
         */
        VBox layout = new VBox(10); // Spacing of 10 pixels
        layout.getChildren().addAll(startButton, quitButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        /**
         * The background image of the main menu.
         */
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);


        /**
         * The StackPane that contains the background and layout to be displayed.
         */
        StackPane Stack = new StackPane();
        Stack.getChildren().addAll(background,layout);

        /**
         * The scene for the main menu.
         */
        Scene scene = new Scene(Stack, SCREEN_WIDTH, SCREEN_HEIGHT);

        /**
         * The primary stage for the main menu.
         */
        primaryStage.setTitle("Sky Battle");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Stage getStage() {
        return primaryStage;
    }


    /**
     * Starts the game.
     * @param primaryStage The primary stage for the game.
     */
    public void startGame(Stage primaryStage) {
        System.out.println("Game Started!");
        MenuController = new Controller(primaryStage);
        try {
            MenuController.launchGame();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    
    
}
