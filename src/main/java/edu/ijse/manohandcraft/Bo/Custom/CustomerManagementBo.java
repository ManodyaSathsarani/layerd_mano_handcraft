package edu.ijse.manohandcraft.Bo.Custom;

import edu.ijse.manohandcraft.Bo.SuperBo;
import edu.ijse.manohandcraft.Dto.CustomerManagementDto;
import edu.ijse.manohandcraft.Entity.CustomerManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerManagementBo extends SuperBo {
    public ArrayList<CustomerManagementDto> getAllCustomerManagemnet() throws SQLException, ClassNotFoundException;
    public boolean saveCustomer(CustomerManagementDto customerManagementDto) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerManagementDto customerManagementDto ) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String CustomerId) throws SQLException, ClassNotFoundException;
    public String genarateNewCustomerId() throws SQLException, ClassNotFoundException;
}
