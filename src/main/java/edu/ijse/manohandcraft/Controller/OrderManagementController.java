package edu.ijse.manohandcraft.Controller;

import edu.ijse.manohandcraft.Bo.BoFactory;
import edu.ijse.manohandcraft.Bo.Custom.OrderManagementBo;
import edu.ijse.manohandcraft.Dto.OrderManagementDto;
import edu.ijse.manohandcraft.View.tm.OrderManagementTM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderManagementController implements Initializable {
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblId;

    @FXML
    private TableView<OrderManagementTM> tblOrderManagement;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTotalAmount;


    OrderManagementBo orderManagementBo =(OrderManagementBo) BoFactory.getInstance().getBO(BoFactory.BOType.ORDERMANAGEMENT);


    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblOrderManagement.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order_id"));
        tblOrderManagement.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tblOrderManagement.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblOrderManagement.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("order_date"));
        tblOrderManagement.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total_amount"));
        tblOrderManagement.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tblOrderManagement.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblOrderManagement.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblOrderManagement.getItems().clear();
        ArrayList<OrderManagementDto> allOrders = orderManagementBo.getAllOrders();
        for (OrderManagementDto orderManagementDto : allOrders) {
            tblOrderManagement.getItems().add(
                    new OrderManagementTM(
                            orderManagementDto.getOrder_id(),
                            orderManagementDto.getCustomer_id(),
                            orderManagementDto.getEmployee_id(),
                            orderManagementDto.getOrder_date(),
                            orderManagementDto.getTotal_amount(),
                            orderManagementDto.getProduct_id(),
                            orderManagementDto.getQuantity(),
                            orderManagementDto.getPrice()
                    ));

        }






    }

    private void resetPage() {
        try {

            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtCustomerId.clear();
            txtEmployeeId.clear();
            txtOrderDate.clear();
            txtTotalAmount.clear();
            txtProductId.clear();
            txtQuantity.clear();
            txtPrice.clear();




        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    private boolean validateAllInputs() {
        String customerId = txtCustomerId.getText().trim();
        String employeeId = txtEmployeeId.getText().trim();
        String orderDate = txtOrderDate.getText().trim();
        String totalAmount = txtTotalAmount.getText().trim();
        String productId = txtProductId.getText().trim();
        String quantity = txtQuantity.getText().trim();

        if (customerId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Customer ID is required.");
            return false;
        }

        if (employeeId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Employee ID is required.");
            return false;
        }

        if (orderDate.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Order Date is required.");
            return false;
        }

        try {
            java.time.LocalDate.parse(orderDate); // expects yyyy-MM-dd
        } catch (java.time.format.DateTimeParseException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Order Date must be in yyyy-MM-dd format.");
            return false;
        }

        if (totalAmount.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Total Amount is required.");
            return false;
        }
        if (productId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Product ID is required.");
            return false;
        }
        if (quantity.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Quantity is required.");
            return false;
        }
        try {
            int qty = Integer.parseInt(quantity);
            if (qty <= 0) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Quantity must be greater than zero.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Quantity must be a valid integer.");
            return false;
        }

        try {
            double amount = Double.parseDouble(totalAmount);
            if (amount < 0) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Total Amount cannot be negative.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Total Amount must be a number.");
            return false;
        }

        return true;
    }

    // Helper alert method
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateAllInputs()) return;
        String orderId = lblId.getText();
        String customer_id = txtCustomerId.getText();
        String employee_id = txtEmployeeId.getText();
        String order_date = txtOrderDate.getText();
        Double total_amount = Double.valueOf(txtTotalAmount.getText());
        String product_id = txtProductId.getText();
        Integer quantity = Integer.valueOf(txtQuantity.getText());
        Double price = Double.valueOf(txtPrice.getText());
        try {
            orderManagementBo.saveOrders(new OrderManagementDto(
                    orderId,
                    customer_id,
                    employee_id,
                    order_date,
                    total_amount,
                    product_id,
                    quantity,
                    price
            ));
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (!validateAllInputs()) return;
        String orderId = lblId.getText();
        String customer_id = txtCustomerId.getText();
        String employee_id = txtEmployeeId.getText();
        String order_date = txtOrderDate.getText();
        Double total_amount = Double.valueOf(txtTotalAmount.getText());
        String product_id = txtProductId.getText();
        Integer quantity = Integer.valueOf(txtQuantity.getText());
        Double price = Double.valueOf(txtPrice.getText());
        try {
            orderManagementBo.updateOrders(new OrderManagementDto(
                    orderId,
                    customer_id,
                    employee_id,
                    order_date,
                    total_amount,
                    product_id,
                    quantity,
                    price
            ));
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
            String order_Id = lblId.getText();
            try {
                orderManagementBo.deleteOrders(order_Id);
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
        String nextId = orderManagementBo.genarateOrderNextId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        OrderManagementTM selectedItem = tblOrderManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getOrder_id());
            txtCustomerId.setText(selectedItem.getCustomer_id());
            txtEmployeeId.setText(selectedItem.getEmployee_id());
            txtOrderDate.setText(selectedItem.getOrder_date());
            txtTotalAmount.setText(String.valueOf(selectedItem.getTotal_amount()));
            txtProductId.setText(selectedItem.getProduct_id());
            txtQuantity.setText(String.valueOf(selectedItem.getQuantity()));
            txtPrice.setText(String.valueOf(selectedItem.getPrice()));


            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }

}
