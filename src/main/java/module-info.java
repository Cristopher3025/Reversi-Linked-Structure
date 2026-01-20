/**
 * Reversi game implemented using a linked node data structure.
 * The project is divided into UI, logic and model layers.
 */
module edu.una.datastructures.reversigame {

    requires javafx.controls;
    requires javafx.fxml;

    // Allows JavaFX to access UI classes (FXML-ready)
    opens edu.una.datastructures.ui to javafx.fxml;

    // Exported packages for documentation and external visibility
    exports edu.una.datastructures.ui;
    exports edu.una.datastructures.logic;
    exports edu.una.datastructures.model;
}
