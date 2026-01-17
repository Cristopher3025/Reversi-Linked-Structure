package edu.una.datastructures.ui;

import edu.una.datastructures.model.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 400);
Board board = new Board();
board.checkLinks();

        stage.setTitle("Reversi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
