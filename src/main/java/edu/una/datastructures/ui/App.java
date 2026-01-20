package edu.una.datastructures.ui;

import edu.una.datastructures.logic.GameController;
import edu.una.datastructures.model.Board;
import edu.una.datastructures.model.Node;
import edu.una.datastructures.model.PieceColor;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;

/**
 * JavaFX application entry point for the Reversi game.
 * <p>
 * This class initializes the user interface and delegates all game logic
 * responsibilities to the controller layer.
 */
public class App extends Application {

    private static final int CELL_SIZE = 60;

    @Override
    public void start(Stage stage) {
        showGameModeMenu(stage);
    }

    /**
     * Initial menu to select game mode.
     */
    private void showGameModeMenu(Stage stage) {

        Button hvhButton = new Button("Human vs Human");
        Button hvbButton = new Button("Human vs Bot");

        hvhButton.setOnAction(e -> startGame(stage, false));
        hvbButton.setOnAction(e -> startGame(stage, true));

        VBox menu = new VBox(20, hvhButton, hvbButton);
        menu.setAlignment(Pos.CENTER);

        Scene menuScene = new Scene(menu, 300, 200);
        stage.setTitle("Reversi - Select Game Mode");
        stage.setScene(menuScene);
        stage.show();
    }

    /**
     * Starts the game after selecting the mode.
     */
    private void startGame(Stage stage, boolean vsBot) {

        Board board = new Board();
        GameController controller = new GameController(board);

        if (vsBot) {
            controller.setGameMode(true, PieceColor.WHITE); // Bot plays WHITE
        } else {
            controller.setGameMode(false, null);
        }

        GridPane grid = buildGrid(controller);

        VBox root = new VBox(grid);
        root.setAlignment(Pos.CENTER);

        Scene gameScene = new Scene(
                root,
                CELL_SIZE * 8 + 40,
                CELL_SIZE * 8 + 40
        );

        stage.setTitle("Reversi");
        stage.setScene(gameScene);
        stage.show();

        // Bot may need to play first
        controller.playBotMoveIfNeeded();
        refresh(gameScene, controller);
    }

    /**
     * Builds the visual board from the linked structure.
     */
    private GridPane buildGrid(GameController controller) {

        GridPane grid = new GridPane();
        Board board = controller.getBoard();

        Node rowStart = board.getTopLeft();
        int row = 0;

        while (rowStart != null) {
            Node current = rowStart;
            int col = 0;

            while (current != null) {
                StackPane cell = createCell(current, controller);
                grid.add(cell, col, row);

                current = current.getEast();
                col++;
            }

            rowStart = rowStart.getSouth();
            row++;
        }

        return grid;
    }

    /**
     * Creates a single board cell bound to a node.
     */
    private StackPane createCell(Node node, GameController controller) {

        StackPane cell = new StackPane();
        cell.setPrefSize(CELL_SIZE, CELL_SIZE);

        Rectangle background = new Rectangle(CELL_SIZE, CELL_SIZE);
        background.setFill(Color.DARKGREEN);
        background.setStroke(Color.BLACK);
        cell.getChildren().add(background);

        // Draw piece
        if (node.getPiece() == PieceColor.BLACK) {
            Circle piece = new Circle(22);
            piece.setFill(Color.BLACK);
            cell.getChildren().add(piece);

        } else if (node.getPiece() == PieceColor.WHITE) {
            Circle piece = new Circle(22);
            piece.setFill(Color.WHITE);
            cell.getChildren().add(piece);

        } // Valid move indicator
        else if (node.isValidMove()) {
            Circle hint = new Circle(6);
            hint.setFill(Color.DODGERBLUE);
            cell.getChildren().add(hint);
        }

        // UI delegates action to controller
        cell.setOnMouseClicked(e -> {
            if (controller.playMove(node)) {
                controller.playBotMoveIfNeeded();
                refresh(cell.getScene(), controller);

                if (controller.isGameOver()) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("Game Finished");
                    alert.setContentText(controller.getWinnerText());
                    alert.showAndWait();
                }
            }
        });

        return cell;
    }

    /**
     * Refreshes the board UI.
     */
    private void refresh(Scene scene, GameController controller) {
        GridPane newGrid = buildGrid(controller);
        ((VBox) scene.getRoot()).getChildren().set(0, newGrid);
    }

    public static void main(String[] args) {
        launch();
    }
}
