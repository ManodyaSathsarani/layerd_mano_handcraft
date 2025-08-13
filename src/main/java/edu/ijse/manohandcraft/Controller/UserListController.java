package edu.ijse.manohandcraft.Controller;

import edu.ijse.manohandcraft.Bo.BoFactory;
import edu.ijse.manohandcraft.Bo.Custom.UserListBo;
import edu.ijse.manohandcraft.Dto.UserListDto;
import edu.ijse.manohandcraft.Entity.UserList;
import edu.ijse.manohandcraft.View.tm.UserListTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtRole;
    public TextField txtRegistrationDate;
    public TableView<UserListTM> tblUserList;

    UserListBo userListBo = (UserListBo) BoFactory.getInstance().getBO(BoFactory.BOType.USER);





    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblUserList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblUserList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUserList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("username"));
        tblUserList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("password"));
        tblUserList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("role"));
        tblUserList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("registration_date"));
        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblUserList.getItems().clear();
        ArrayList<UserListDto> allUsers = userListBo.getAllUserList();
        for (UserListDto userListDto : allUsers) {
            tblUserList.getItems().add(
                    new UserListTM(
                            userListDto.getUser_Id(),
                            userListDto.getName(),
                            userListDto.getUserName(),
                            userListDto.getPassword(),
                            userListDto.getRole(),
                            userListDto.getRegistration_Date()));

        }
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.setText(null);
            txtUserName.setText(null);
            txtPassword.setText(null);
            txtRole.setText(null);
            txtRegistrationDate.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    private boolean validateInputs() {
        String name = txtName.getText().trim();
        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        String role = txtRole.getText().trim();
        String regDate = txtRegistrationDate.getText().trim();

        if (name.isEmpty() || userName.isEmpty() || password.isEmpty() || role.isEmpty() || regDate.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields are required.");
            return false;
        }

        if (!name.matches("[A-Za-z ]+")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Name must only contain letters and spaces.");
            return false;
        }

        if (password.length() < 6) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Password must be at least 6 characters.");
            return false;
        }

        if (!role.matches("(?i)(admin|user|manager|staff)")) {  // Example roles
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Role must be one of: Admin, User, Manager, Staff.");
            return false;
        }

        if (!regDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Registration date must be in format YYYY-MM-DD.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = lblId.getText();
        String userName = txtName.getText();
        String userUserName = txtUserName.getText();
        String userPassword = txtPassword.getText();
        String userRole = txtRole.getText();
        String userRegistrationDate = txtRegistrationDate.getText();

        if (!validateInputs())
            return;


        try {
            userListBo.saveUser(new UserListDto(
                    userId,userName,userUserName,userPassword,userRole,userRegistrationDate));
            resetPage();


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        String userId = lblId.getText();
        String userName = txtName.getText();
        String userUserName = txtUserName.getText();
        String userPassword = txtPassword.getText();
        String userRole = txtRole.getText();
        String userRegistrationDate = txtRegistrationDate.getText();




        try {
            userListBo.updateUser(new UserListDto(
                    userId,userName,userUserName,userPassword,userRole,userRegistrationDate));
            resetPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are You Sure ? ",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String userId = lblId.getText();
            try {
                boolean isDeleted = userListBo.deleteUser(userId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = userListBo.generateNewUserId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        UserListTM selectedItem = tblUserList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getUser_Id());
            txtUserName.setText(selectedItem.getName());
            txtName.setText(selectedItem.getUserName());
            txtPassword.setText(selectedItem.getPassword());
            txtRole.setText(selectedItem.getRole());
            txtRegistrationDate.setText(selectedItem.getRegistration_Date());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
