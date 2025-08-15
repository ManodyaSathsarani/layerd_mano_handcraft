package edu.ijse.manohandcraft.Controller;

import edu.ijse.manohandcraft.Bo.BoFactory;
import edu.ijse.manohandcraft.Bo.Custom.CustomerManagementBo;
import edu.ijse.manohandcraft.Dto.CustomerManagementDto;
import edu.ijse.manohandcraft.Dto.UserListDto;
import edu.ijse.manohandcraft.View.tm.CustomerManagementTM;
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

public class CustomerManagementController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtAddress;


    public TableView<CustomerManagementTM> tblCustomer;

    CustomerManagementBo customerManagementBo =(CustomerManagementBo) BoFactory.getInstance().getBO(BoFactory.BOType.CUSTOMERMANAGEMENT);




    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
       tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
       tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
       tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("phone"));
       tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblCustomer.getItems().clear();
        ArrayList<CustomerManagementDto> allCustomers = customerManagementBo.getAllCustomerManagemnet();
        for (CustomerManagementDto customerManagementDto : allCustomers) {
            tblCustomer.getItems().add(
                    new CustomerManagementTM(
                            customerManagementDto.getCustomer_Id(),
                            customerManagementDto.getName(),
                            customerManagementDto.getPhone(),
                            customerManagementDto.getAddress()));


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
            txtPhone.setText(null);
            txtAddress.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    private boolean validateInputs() {
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            showValidationAlert("Name is required.");
            return false;
        }

        if (!name.matches("^[A-Za-z ]+$")) {
            showValidationAlert("Name must only contain letters and spaces.");
            return false;
        }

        // Validate phone (Sri Lankan format: starts with 07 and has 10 digits)
        if (phone == null || phone.trim().isEmpty()) {
            showValidationAlert("Phone number is required.");
            return false;
        }

        if (!phone.matches("^07[0-9]{8}$")) {
            showValidationAlert("Phone number must be a valid Sri Lankan mobile number (e.g., 0771234567).");
            return false;
        }

        // Validate address
        if (address == null || address.trim().isEmpty()) {
            showValidationAlert("Address is required.");
            return false;
        }

        return true;
    }

    private void showValidationAlert(String message) {
        new Alert(Alert.AlertType.WARNING, message).show();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        String customer = lblId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();

        if (!validateInputs())
            return;
        try {
            customerManagementBo.saveCustomer(new CustomerManagementDto(
                   customer ,name,phone,address));
            resetPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        String customerId = lblId.getText();
        String customerName = txtName.getText();
        String customerPhone = txtPhone.getText();
        String customerAddress = txtAddress.getText();

        if (!validateInputs()) return;



        //

        try {
            customerManagementBo.saveCustomer(new CustomerManagementDto(
                    customerId ,customerName,customerPhone,customerAddress));
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
            String customerId = lblId.getText();
            try {
                customerManagementBo.deleteCustomer(customerId);
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
        String nextId = customerManagementBo.genarateNewCustomerId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        CustomerManagementTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getCustomer_Id());
            txtName.setText(selectedItem.getName());
            txtPhone.setText(selectedItem.getPhone());
            txtAddress.setText(selectedItem.getAddress());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
