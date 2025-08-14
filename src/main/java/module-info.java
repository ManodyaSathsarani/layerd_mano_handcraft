module edu.ijse.manohandcraft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.ijse.manohandcraft to javafx.fxml;
    opens edu.ijse.manohandcraft.Controller to javafx.fxml;
    opens edu.ijse.manohandcraft.View.tm to javafx.fxml;
    opens edu.ijse.manohandcraft.db to javafx.fxml;


    exports edu.ijse.manohandcraft;
    exports edu.ijse.manohandcraft.Controller;
    exports edu.ijse.manohandcraft.View.tm;
    exports edu.ijse.manohandcraft.Dto;
    exports edu.ijse.manohandcraft.Util;
    opens edu.ijse.manohandcraft.Util to javafx.fxml;
}