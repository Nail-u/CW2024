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

public class MainMenu extends Application {

    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
	private static final String BACKGROUND_IMAGE = "/com/example/demo/images/MenuBackground.jpg";
    private Controller MenuController;

    @Override
    public void start(Stage primaryStage) {
        // Create the Start button
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startGame(primaryStage));
        startButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

        // Create the Quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());
        quitButton.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

        // Arrange buttons in a vertical layout
        VBox layout = new VBox(10); // Spacing of 10 pixels
        layout.getChildren().addAll(startButton, quitButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Create the background image
        ImageView background = new ImageView(new Image(getClass().getResource(BACKGROUND_IMAGE).toExternalForm()));
        background.setFitWidth(SCREEN_WIDTH);
        background.setFitHeight(SCREEN_HEIGHT);

        // Create a StackPane to center the VBox
        StackPane Stack = new StackPane();
        Stack.getChildren().addAll(background,layout);

        // Create the scene with the StackPane as the root
        Scene scene = new Scene(Stack, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Set up the stage
        primaryStage.setTitle("Sky Battle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Placeholder for starting the game
    private void startGame(Stage primaryStage) {
        System.out.println("Game Started!");
        MenuController = new Controller(primaryStage);
        try {
            MenuController.launchGame();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
}
