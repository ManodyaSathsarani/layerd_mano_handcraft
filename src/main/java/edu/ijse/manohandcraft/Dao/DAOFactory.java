package edu.ijse.manohandcraft.Dao;

import edu.ijse.manohandcraft.Dao.Custom.Impl.UserListDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        USERLIST

    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case USERLIST:
                return new UserListDAOImpl();
            default:
                return null;
        }
    }
}
