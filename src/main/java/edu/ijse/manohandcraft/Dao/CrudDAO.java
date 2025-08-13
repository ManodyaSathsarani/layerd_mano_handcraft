package edu.ijse.manohandcraft.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(T DTOs) throws SQLException, ClassNotFoundException;
    public boolean update(T DTOs) throws SQLException, ClassNotFoundException;
    boolean delete(String Id) throws SQLException, ClassNotFoundException;
    public String genarateNextId() throws SQLException , ClassNotFoundException;



}
