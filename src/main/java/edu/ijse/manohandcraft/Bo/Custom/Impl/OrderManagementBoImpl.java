package edu.ijse.manohandcraft.Bo.Custom.Impl;

import edu.ijse.manohandcraft.Bo.Custom.OrderManagementBo;
import edu.ijse.manohandcraft.Dao.Custom.OrderManagementDAO;
import edu.ijse.manohandcraft.Dao.DAOFactory;
import edu.ijse.manohandcraft.Dto.OrderManagementDto;
import edu.ijse.manohandcraft.Entity.OrderManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManagementBoImpl implements OrderManagementBo {


    OrderManagementDAO orderManagementDAO = (OrderManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERMANAGEMENT);
    @Override
    public ArrayList<OrderManagementDto> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderManagement> entity = orderManagementDAO.getAll();
        ArrayList<OrderManagementDto> orderManagementDtos = new ArrayList<>();
        for (OrderManagement orderManagement : entity) {
            orderManagementDtos.add(new OrderManagementDto(
                    orderManagement.getOrder_id(),
                    orderManagement.getCustomer_id(),
                    orderManagement.getEmployee_id(),
                    orderManagement.getOrder_date(),
                    orderManagement.getTotal_amount(),
                    orderManagement.getProduct_id(),
                    orderManagement.getQuantity(),
                    orderManagement.getPrice()));
        }
        return orderManagementDtos;
    }

    @Override
    public boolean saveOrders(OrderManagementDto orderManagementDto) throws SQLException, ClassNotFoundException {
        return orderManagementDAO.save(new OrderManagement(
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

    @Override
    public boolean updateOrders(OrderManagementDto orderManagementDto) throws SQLException, ClassNotFoundException {
        return orderManagementDAO.update(new OrderManagement(
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

    @Override
    public boolean deleteOrders(String Id) throws SQLException, ClassNotFoundException {
        return orderManagementDAO.delete(Id);
    }

    @Override
    public String genarateOrderNextId() throws SQLException, ClassNotFoundException {
        return orderManagementDAO.genarateNextId();
    }
}
