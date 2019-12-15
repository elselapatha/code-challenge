package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.OrganizationDAO;
import model.OrganizationModel;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
    @Override
    public List<OrganizationModel> getAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<OrganizationModel>> typeReference = new TypeReference<List<OrganizationModel>>() {
        };
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/organizations.json")){
            List<OrganizationModel> list = objectMapper.readValue(inputStream, typeReference);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
