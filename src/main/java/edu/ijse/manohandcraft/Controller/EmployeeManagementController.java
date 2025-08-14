package edu.ijse.manohandcraft.Controller;

import edu.ijse.manohandcraft.Bo.BoFactory;
import edu.ijse.manohandcraft.Bo.Custom.CustomerManagementBo;
import edu.ijse.manohandcraft.Bo.Custom.EmployeeManagementBo;
import edu.ijse.manohandcraft.Dto.CustomerManagementDto;
import edu.ijse.manohandcraft.Dto.EmployeeManagementDto;
import edu.ijse.manohandcraft.View.tm.CustomerManagementTM;
import edu.ijse.manohandcraft.View.tm.EmployeeManagementTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeManagementcontroller implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtRole;
    public TextField txtHireDate ;
    public TextField txtPhone;
    public TextField txtAddress;

    public TableView<EmployeeManagementTM> tblEmployeeManagement;

    EmployeeManagementBo employeeManagementBo =(EmployeeManagementBo) BoFactory.getInstance().getBO(BoFactory.BOType.EMPLOYEEMANAGEMENT);





    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblEmployeeManagement.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblEmployeeManagement.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployeeManagement.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("role"));
        tblEmployeeManagement.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("hire_date"));
        tblEmployeeManagement.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblEmployeeManagement.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("assress"));
        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblEmployeeManagement.getItems().clear();
        ArrayList<EmployeeManagementDto> allEmployees = employeeManagementBo.getAllEmployeeManagement();
        for (EmployeeManagementDto employeeManagementDto : allEmployees) {
            tblEmployeeManagement.getItems().add(
                    new EmployeeManagementTM(
                            employeeManagementDto.getEmployee_Id(),
                            employeeManagementDto.getName(),
                            employeeManagementDto.getRole(),
                            employeeManagementDto.getHire_date(),
                            employeeManagementDto.getPhone(),
                            employeeManagementDto.getAddress()));


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
            txtRole.setText(null);
            txtHireDate.setText(null);
            txtPhone.setText(null);
            txtAddress.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean validateInputs(String name, String role, String hireDate, String phone, String address) {
        if (name.isEmpty() || role.isEmpty() || hireDate.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All fields must be filled out!").show();
            return false;
        }

        if (!validatePhoneNumber(phone)) {
            new Alert(Alert.AlertType.WARNING, "Invalid phone number! Must be 10 digits.").show();
            return false;
        }

        if (!validateDate(hireDate)) {
            new Alert(Alert.AlertType.WARNING, "Invalid date format! Use yyyy-MM-dd.").show();
            return false;
        }

        return true;
    }

    private boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean validateDate(String dateStr) {
        try {
            LocalDate.parse(dateStr); // Assumes format yyyy-MM-dd
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String employeeId = lblId.getText();
        String employeeName = txtName.getText();
        String employeeRole = txtRole.getText();
        String employeeHireDate = txtHireDate.getText();
        String employeePhone = txtPhone.getText();
        String employeeAddress = txtAddress.getText();


//        if (!validateInputs(employeeName, employeeRole, employeeHireDate, employeePhone, employeeAddress)) {
//            return;
//        }


        try {
            employeeManagementBo.saveEmployee(new EmployeeManagementDto(
                    employeeId ,employeeName,employeeRole,employeeHireDate,employeePhone,employeeAddress));
            resetPage();


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = lblId.getText();
        String employeeName = txtName.getText();
        String employeeRole = txtRole.getText();
        String employeeHireDate = txtHireDate.getText();
        String employeePhone = txtPhone.getText();
        String employeeAddress = txtAddress.getText();

//        if (!validateInputs(employeeName, employeeRole, employeeHireDate, employeePhone, employeeAddress)) {
//            return;
//        }


        try {
            employeeManagementBo.saveEmployee(new EmployeeManagementDto(
                    employeeId ,employeeName,employeeRole,employeeHireDate,employeePhone,employeeAddress));
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
            String employeeId = lblId.getText();
            try {
                employeeManagementBo.saveEmployee(employeeId);
                resetPage();
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
        String nextId = employeeMnagementModel.getEmployeeId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        EmployeeManagementTM selectedItem = tblEmployeeManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getEmployee_Id());
            txtName.setText(selectedItem.getName());
            txtRole.setText(selectedItem.getRole());
            txtHireDate.setText(selectedItem.getHire_date());
            txtPhone.setText(selectedItem.getPhone());
            txtAddress.setText(selectedItem.getAddress());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
