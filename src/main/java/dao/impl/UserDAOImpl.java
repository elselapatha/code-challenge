package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import model.UserModel;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public List<UserModel> getAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserModel>> typeReference = new TypeReference<List<UserModel>>() {
        };
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json")) {
            List<UserModel> list = objectMapper.readValue(inputStream, typeReference);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
