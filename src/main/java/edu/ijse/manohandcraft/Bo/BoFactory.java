package edu.ijse.manohandcraft.Bo;

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

    }

    public SuperBo getBO(BOType type) {
        switch (type) {
            case USER:
                return new UserListBoImpl();

            default:
                return null;
        }
    }
}
