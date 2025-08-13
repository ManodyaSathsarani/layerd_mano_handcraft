module edu.ijse.manohandcraft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens edu.ijse.manohandcraft to javafx.fxml;
    exports edu.ijse.manohandcraft;
}