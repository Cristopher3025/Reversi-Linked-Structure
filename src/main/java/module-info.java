module edu.una.datastructures.reversigame {

    requires javafx.controls;
    requires javafx.fxml;

    
    opens edu.una.datastructures.ui to javafx.fxml;

   
    exports edu.una.datastructures.ui;
}
