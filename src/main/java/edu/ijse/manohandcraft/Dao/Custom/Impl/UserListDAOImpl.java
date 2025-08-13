package edu.ijse.manohandcraft.Dao.Custom.Impl;

import edu.ijse.manohandcraft.Dao.Custom.UserListDAO;
import edu.ijse.manohandcraft.Entity.UserList;
import edu.ijse.manohandcraft.Util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserListDAOImpl implements UserListDAO {
    @Override
    public ArrayList<UserList> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Users");
        ArrayList<UserList> userList = new ArrayList<>();
        while (rst.next()) {
            userList.add(new UserList(
                    rst.getString("user_Id"),
                    rst.getString("name"),
                    rst.getString("userName"),
                    rst.getString("Password"),
                    rst.getString("role"),
                    rst.getString("registration_Date")));
        }
        return userList;
    }

    @Override
    public boolean save(UserList DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Users (user_id, name, username, password, role, Registration_date, Status) VALUES (?, ?, ?, ?, ?, ?,)",
                DTOs.getUser_Id(),
                DTOs.getName(),
                DTOs.getUserName(),
                DTOs.getPassword(),
                DTOs.getRole(),
                DTOs.getRegistration_Date());
    }
    @Override
    public boolean update(UserList DTOs) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO Users (user_id, name, username, password, role, Registration_date, Status) VALUES (?, ?, ?, ?, ?, ?,)",
                DTOs.getUser_Id(),
                DTOs.getName(),
                DTOs.getUserName(),
                DTOs.getPassword(),
                DTOs.getRole(),
                DTOs.getRegistration_Date());

    }

    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM Users WHERE user_id = ?", userId);
    }

    @Override
    public String genarateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.executeQuery("SELECT user_id FROM Users ORDER BY user_id DESC LIMIT 1");
        char tableCharacter = 'U';

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
