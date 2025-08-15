package edu.ijse.manohandcraft.Bo;

import edu.ijse.manohandcraft.Bo.Custom.Impl.CustomerManagementBoImpl;
import edu.ijse.manohandcraft.Bo.Custom.Impl.EmployeeManagementBoImpl;
import edu.ijse.manohandcraft.Bo.Custom.Impl.OrderManagementBoImpl;
import edu.ijse.manohandcraft.Bo.Custom.Impl.UserListBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        return (boFactory == null) ? new BoFactory() : boFactory;
    }

    public enum BOType {
        USER,
        CUSTOMERMANAGEMENT,
        EMPLOYEEMANAGEMENT,
        ORDERMANAGEMENT

    }

    public SuperBo getBO(BOType type) {
        switch (type) {
            case USER:
                return new UserListBoImpl();
            case CUSTOMERMANAGEMENT:
                return new CustomerManagementBoImpl();
            case EMPLOYEEMANAGEMENT:
                return  new EmployeeManagementBoImpl();
            case ORDERMANAGEMENT:
                return new OrderManagementBoImpl();

            default:
                return null;
        }
    }
}
