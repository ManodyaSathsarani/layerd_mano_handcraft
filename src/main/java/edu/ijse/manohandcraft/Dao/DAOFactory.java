package edu.ijse.manohandcraft.Dao;

import edu.ijse.manohandcraft.Dao.Custom.Impl.CustomerManagementDAOImpl;
import edu.ijse.manohandcraft.Dao.Custom.Impl.EmployeeManagementDAOImpl;
import edu.ijse.manohandcraft.Dao.Custom.Impl.OrderManagementDAOImpl;
import edu.ijse.manohandcraft.Dao.Custom.Impl.UserListDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        USERLIST,
        CUSTOMERMANAGEMENT,
        EMPLOYEEMANAGEMENT,
        ORDERMANAGEMENT

    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case USERLIST:
                return new UserListDAOImpl();
            case CUSTOMERMANAGEMENT:
                return new CustomerManagementDAOImpl();
            case EMPLOYEEMANAGEMENT:
                return new EmployeeManagementDAOImpl();
            case ORDERMANAGEMENT:
                return new OrderManagementDAOImpl();
            default:
                return null;
        }
    }
}
