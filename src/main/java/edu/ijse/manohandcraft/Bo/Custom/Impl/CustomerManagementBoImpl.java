package edu.ijse.manohandcraft.Bo.Custom.Impl;

import edu.ijse.manohandcraft.Bo.Custom.CustomerManagementBo;
import edu.ijse.manohandcraft.Dao.Custom.CustomerManagementDAO;
import edu.ijse.manohandcraft.Dao.DAOFactory;
import edu.ijse.manohandcraft.Dto.CustomerManagementDto;
import edu.ijse.manohandcraft.Entity.CustomerManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagementBoImpl implements CustomerManagementBo {

    CustomerManagementDAO customerManagementDAO = (CustomerManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMERMANAGEMENT);

    @Override
    public ArrayList<CustomerManagementDto> getAllCustomerManagemnet() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerManagement> entity = customerManagementDAO.getAll();
        ArrayList<CustomerManagementDto> customerManagementDtos = new ArrayList<>();
        for (CustomerManagement CustomerManagement : entity) {
            customerManagementDtos.add(new CustomerManagementDto(
                    CustomerManagement.getCustomer_Id(),
                    CustomerManagement.getName(),
                    CustomerManagement.getPhone(),
                    CustomerManagement.getAddress()));
        }
        return customerManagementDtos;
    }

    @Override
    public boolean saveCustomer(CustomerManagementDto customerManagementDtos ) throws SQLException, ClassNotFoundException {
           return customerManagementDAO.save(new CustomerManagement(
                    customerManagementDtos.getCustomer_Id(),
                    customerManagementDtos.getName(),
                    customerManagementDtos.getPhone(),
                    customerManagementDtos.getAddress()));

    }

    @Override
    public boolean updateCustomer(CustomerManagementDto customerManagementDto) throws SQLException, ClassNotFoundException {
            return customerManagementDAO.save(new CustomerManagement(
                    customerManagementDto.getCustomer_Id(),
                    customerManagementDto.getName(),
                    customerManagementDto.getPhone(),
                    customerManagementDto.getAddress()));

    }

    @Override
    public boolean deleteCustomer(String CustomerId) throws SQLException, ClassNotFoundException {
        return customerManagementDAO.delete(CustomerId);
    }

    @Override
    public String genarateNewCustomerId() throws SQLException, ClassNotFoundException {
        return customerManagementDAO.genarateNextId();
    }
}
