package edu.ijse.manohandcraft.Dao.Custom.Impl;

import edu.ijse.manohandcraft.Dao.Custom.CustomerManagementDAO;
import edu.ijse.manohandcraft.Dto.CustomerManagementDto;
import edu.ijse.manohandcraft.Entity.CustomerManagement;
import edu.ijse.manohandcraft.Entity.UserList;
import edu.ijse.manohandcraft.Util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagementDAOImpl implements CustomerManagementDAO {
    @Override
    public ArrayList<CustomerManagement> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM customers");
        ArrayList<CustomerManagement> customerManagements = new ArrayList<>();
        while (rst.next()) {
            customerManagements.add(new CustomerManagement(
                    rst.getString("customer_Id"),
                    rst.getString("name"),
                    rst.getString("phone"),
                    rst.getString("address")));
        }
        return customerManagements;
    }

    @Override
    public boolean save(CustomerManagement DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Users (customer_id, name, phone, address) VALUES (?, ?, ?, ?)",
                DTOs.getCustomer_Id(),
                DTOs.getName(),
                DTOs.getPhone(),
                DTOs.getAddress());
    }

    @Override
    public boolean update(CustomerManagement DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Users (customer_id, name, phone, address) VALUES (?, ?, ?, ?)",
                DTOs.getCustomer_Id(),
                DTOs.getName(),
                DTOs.getPhone(),
                DTOs.getAddress());
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Users WHERE customer_id = ?, customerId");
    }

    @Override
    public String genarateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT customer_id FROM customers ORDER BY customer_id DESC LIMIT 1");
        char tableCharacter = 'C';

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
