package service;

import model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> search(String field,String value);
    List<String> getSerchableField();
    boolean isValidField(String field);
}
