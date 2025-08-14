package edu.ijse.manohandcraft.Bo.Custom.Impl;

import edu.ijse.manohandcraft.Bo.Custom.UserListBo;
import edu.ijse.manohandcraft.Dao.Custom.UserListDAO;
import edu.ijse.manohandcraft.Dao.DAOFactory;
import edu.ijse.manohandcraft.Dto.UserListDto;
import edu.ijse.manohandcraft.Entity.UserList;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserListBoImpl implements UserListBo {

    UserListDAO userListDAO =(UserListDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USERLIST);
    @Override
    public ArrayList<UserListDto> getAllUserList() throws SQLException, ClassNotFoundException {
        ArrayList<UserList> entity = userListDAO.getAll();
        ArrayList<UserListDto> userDTOS = new ArrayList<>();
        for (UserList UserList : entity) {
            userDTOS.add(new UserListDto(
                    UserList.getUser_Id(),
                    UserList.getName(),
                    UserList.getUserName(),
                    UserList.getPassword(),
                    UserList.getRole(),
                    UserList.getRegistration_Date()));
        }
        return userDTOS;
    }

    @Override
    public boolean saveUser(UserListDto userListDto) throws SQLException, ClassNotFoundException {
        return userListDAO.save(new UserList(
                userListDto.getUser_Id(),
                userListDto.getName(),
                userListDto.getUserName(),
                userListDto.getPassword(),
                userListDto.getRole(),
                userListDto.getRegistration_Date()));
    }

    @Override
    public boolean updateUser(UserListDto userListDto) throws SQLException, ClassNotFoundException {
        return userListDAO.update(new UserList(
                userListDto.getUser_Id(),
                userListDto.getName(),
                userListDto.getUserName(),
                userListDto.getPassword(),
                userListDto.getRole(),
                userListDto.getRegistration_Date()));
    }

    @Override
    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return userListDAO.delete(userId);
    }

    @Override
    public String generateNewUserId() throws SQLException, ClassNotFoundException {
        return userListDAO.genarateNextId();
    }
}
