package edu.ijse.manohandcraft.Dao.Custom.Impl;

import edu.ijse.manohandcraft.Dao.Custom.EmployeeManagementDAO;
import edu.ijse.manohandcraft.Entity.EmployeeManagement;
import edu.ijse.manohandcraft.Util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagementDAOImpl implements EmployeeManagementDAO {
    @Override
    public ArrayList<EmployeeManagement> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM employees");
        ArrayList<EmployeeManagement> employeeManagement = new ArrayList<>();
        while (rst.next()) {
            employeeManagement.add(new EmployeeManagement(
                    rst.getString("employee_id"),
                    rst.getString("employee_name"),
                    rst.getString("role"),
                    rst.getString("hire_date"),
                    rst.getString("phone"),
                    rst.getString("address")));
        }
        return employeeManagement;
    }

    @Override
    public boolean save(EmployeeManagement DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Employees (employee_id, employee_name, role, hire_date,phone,phone) VALUES (?, ?, ?, ?,?,?)",
                DTOs.getEmployee_Id(),
                DTOs.getName(),
                DTOs.getRole(),
                DTOs.getHire_date(),
                DTOs.getPhone(),
                DTOs.getPhone());
    }

    @Override
    public boolean update(EmployeeManagement DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE employees SET (employee_id, employee_name, role, hire_date,phone,) VALUES (?, ?, ?, ?,?)",
                DTOs.getEmployee_Id(),
                DTOs.getName(),
                DTOs.getRole(),
                DTOs.getHire_date(),
                DTOs.getPhone());
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Employees WHERE employee_id = ?",Id);
    }


    @Override
    public String genarateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT employee_id FROM Employees ORDER BY employee_id DESC LIMIT 1");
        char tableCharacter = 'E';

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);

            return nextIdString;
        }
        return tableCharacter + "001";
    }
}
