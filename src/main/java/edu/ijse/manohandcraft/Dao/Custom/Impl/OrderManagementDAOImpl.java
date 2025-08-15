package edu.ijse.manohandcraft.Dao.Custom.Impl;

import edu.ijse.manohandcraft.Bo.Custom.OrderManagementBo;
import edu.ijse.manohandcraft.Dao.Custom.OrderManagementDAO;
import edu.ijse.manohandcraft.Entity.OrderManagement;
import edu.ijse.manohandcraft.Entity.UserList;
import edu.ijse.manohandcraft.Util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManagementDAOImpl implements OrderManagementDAO {

    @Override
    public ArrayList<OrderManagement> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM orders");
        ArrayList<OrderManagement> orderManagements = new ArrayList<>();
        while (rst.next()) {
            orderManagements.add(new OrderManagement(
                    rst.getString("order_id"),
                    rst.getString("customer_id"),
                    rst.getString("employee_id"),
                    rst.getString("order_date"),
                    rst.getDouble("total_amount"),
                    rst.getString("product_id"),
                    rst.getInt("quantity"),
                    rst.getDouble("price")));
        }
        return orderManagements;
    }

    @Override
    public boolean save(OrderManagement DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO orders (order_id, customer_id, employee_id, order_date, total_amount, product_id, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                DTOs.getOrder_id(),
                DTOs.getCustomer_id(),
                DTOs.getEmployee_id(),
                DTOs.getOrder_date(),
                DTOs.getTotal_amount(),
                DTOs.getProduct_id(),
                DTOs.getQuantity(),
                DTOs.getPrice());
    }

    @Override
    public boolean update(OrderManagement DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE orders SET customer_id = ?, employee_id = ?, order_date = ?, total_amount = ?, product_id = ?, quantity = ?, price = ? WHERE order_id = ?",
                DTOs.getCustomer_id(),
                DTOs.getEmployee_id(),
                DTOs.getOrder_date(),
                DTOs.getTotal_amount(),
                DTOs.getProduct_id(),
                DTOs.getQuantity(),
                DTOs.getPrice(),
                DTOs.getOrder_id());
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM orders WHERE order_id = ?", Id);
    }

    @Override
    public String genarateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        String tableCharacter = "OM";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);

            return nextIdString;
        }
        return tableCharacter + "001";
    }
}
