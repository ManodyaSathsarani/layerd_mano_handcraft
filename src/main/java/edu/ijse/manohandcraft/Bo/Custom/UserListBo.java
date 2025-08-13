package edu.ijse.manohandcraft.Bo.Custom;

import edu.ijse.manohandcraft.Bo.SuperBo;
import edu.ijse.manohandcraft.Dto.UserListDto;
import edu.ijse.manohandcraft.Entity.UserList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserListBo extends SuperBo {
    public ArrayList<UserListDto> getAllUserList() throws SQLException, ClassNotFoundException;
    public boolean saveUser(UserListDto userListDto) throws SQLException, ClassNotFoundException;
    public boolean updateUser(UserListDto userListDto) throws SQLException, ClassNotFoundException;
    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException;
    public String generateNewUserId() throws SQLException, ClassNotFoundException;

}
