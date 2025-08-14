package edu.ijse.manohandcraft.Bo.Custom.Impl;

import edu.ijse.manohandcraft.Bo.Custom.EmployeeManagementBo;
import edu.ijse.manohandcraft.Dao.Custom.EmployeeManagementDAO;
import edu.ijse.manohandcraft.Dao.DAOFactory;
import edu.ijse.manohandcraft.Dto.CustomerManagementDto;
import edu.ijse.manohandcraft.Dto.EmployeeManagementDto;
import edu.ijse.manohandcraft.Entity.CustomerManagement;
import edu.ijse.manohandcraft.Entity.EmployeeManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagementBoImpl implements EmployeeManagementBo {

    EmployeeManagementDAO employeeManagementDAO = (EmployeeManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEEMANAGEMENT);
    @Override
    public ArrayList<EmployeeManagementDto> getAllEmployeeManagement() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeManagement> entity =employeeManagementDAO .getAll();
        ArrayList<EmployeeManagementDto> employeeManagementDtos = new ArrayList<>();
        for (EmployeeManagement employeeManagement : entity) {
            employeeManagementDtos.add(new EmployeeManagementDto(
                    employeeManagement.getEmployee_Id(),
                    employeeManagement.getName(),
                    employeeManagement.getRole(),
                    employeeManagement.getHire_date(),
                    employeeManagement.getPhone(),
                    employeeManagement.getPhone());

        }
        return employeeManagementDtos;
    }

    @Override
    public boolean saveEmployee(EmployeeManagementDto employeeManagementDto) throws SQLException, ClassNotFoundException {
        return employeeManagementDAO.save(new EmployeeManagement(
                employeeManagementDto.getEmployee_Id(),
                employeeManagementDto.getName(),
                employeeManagementDto.getRole(),
                employeeManagementDto.getHire_date(),
                employeeManagementDto.getPhone(),
                employeeManagementDto.getPhone()));
    }

    @Override
    public boolean updateEmployee(EmployeeManagementDto employeeManagementDto ) throws SQLException, ClassNotFoundException {
        return employeeManagementDAO.save(new EmployeeManagement(
                employeeManagementDto.getEmployee_Id(),
                employeeManagementDto.getName(),
                employeeManagementDto.getRole(),
                employeeManagementDto.getHire_date(),
                employeeManagementDto.getPhone(),
                employeeManagementDto.getPhone()));

    }


    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return employeeManagementDAO.delete(Id);
    }

    @Override
    public String genarateNextId() throws SQLException, ClassNotFoundException {
        return employeeManagementDAO.genarateNextId();
    }
}
