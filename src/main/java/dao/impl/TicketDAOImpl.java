package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TicketsDAO;
import model.TicketModel;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketsDAO {
    @Override
    public List<TicketModel> getAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<TicketModel>> typeReference = new TypeReference<List<TicketModel>>() {
        };
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/tickets.json")){
            List<TicketModel> list = objectMapper.readValue(inputStream, typeReference);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
