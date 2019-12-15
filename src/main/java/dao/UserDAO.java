package dao;

import model.UserModel;

import java.util.List;

public interface UserDAO {
    List<UserModel> getAll();
}
