package edu.ijse.manohandcraft.Bo.Custom;

import edu.ijse.manohandcraft.Bo.SuperBo;
import edu.ijse.manohandcraft.Dto.OrderManagementDto;
import edu.ijse.manohandcraft.Entity.OrderManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderManagementBo extends SuperBo {
    public ArrayList<OrderManagementDto> getAllOrders() throws SQLException, ClassNotFoundException;
    public boolean saveOrders(OrderManagementDto orderManagementDto) throws SQLException, ClassNotFoundException;
    public boolean updateOrders(OrderManagementDto orderManagementDto) throws SQLException, ClassNotFoundException;
    public boolean deleteOrders(String Id) throws SQLException, ClassNotFoundException;
    public String genarateOrderNextId() throws SQLException, ClassNotFoundException;
}
