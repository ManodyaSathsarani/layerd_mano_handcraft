package edu.ijse.manohandcraft.Bo.Custom;

import edu.ijse.manohandcraft.Bo.SuperBo;
import edu.ijse.manohandcraft.Dto.EmployeeManagementDto;
import edu.ijse.manohandcraft.Entity.EmployeeManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeManagementBo extends SuperBo {
    public ArrayList<EmployeeManagementDto> getAllEmployeeManagement() throws SQLException, ClassNotFoundException;
    public boolean saveEmployee(EmployeeManagementDto DTOs) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeeManagementDto DTOs) throws SQLException, ClassNotFoundException;
    public boolean delete(String Id) throws SQLException, ClassNotFoundException;
    public String genarateNextId() throws SQLException, ClassNotFoundException;

}
