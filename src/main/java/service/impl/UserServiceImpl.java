package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DataDAO;
import dao.impl.DataDAOImpl;
import model.UserModel;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final DataDAO dataDAO;
    private final String[] fields = {"_id", "url", "external_id", "name", "alias", "created_at", "active", "verified", "shared", "locale", "timezone", "last_login_at", "email", "phone", "signature", "organization_id", "tags", "suspended", "role"};

    public UserServiceImpl() {
        dataDAO = DataDAOImpl.getInstance();
    }

    @Override
    public List<UserModel> search(String field, String value) {
        final ObjectMapper objectMapper = new ObjectMapper();
        if (value.equalsIgnoreCase("*")) {
            return this.map(dataDAO.getUserList());
        } else {
            return this.map(dataDAO.getUserList().parallelStream().filter(user -> {
                Map<String, Object> map = objectMapper.convertValue(user, Map.class);
                return String.valueOf(map.get(field.toLowerCase())).contains(value);
            }).collect(Collectors.toList()));
        }
    }

    @Override
    public List<String> getSerchableField() {
        return Arrays.asList(fields);
    }

    @Override
    public boolean isValidField(String field) {
        return Arrays.asList(fields).contains(field);
    }

    private List<UserModel> map(List<UserModel> list){
        return list.parallelStream().map(user -> {
            user.setAssignee_tickets(dataDAO.getTicketList().stream().filter(ticket -> ticket.getAssignee_id() == user.get_id()).map(ticket->ticket.getSubject()).toArray(String[]::new));
            user.setSubmitted_tickets(dataDAO.getTicketList().stream().filter(ticket -> ticket.getSubmitter_id() == user.get_id()).map(ticket->ticket.getSubject()).toArray(String[]::new));
            user.setOrganization(dataDAO.getOrganizationList().stream().filter(organization -> organization.get_id() == user.getOrganization_id()).findFirst().orElse(null));
            return user;
        }).collect(Collectors.toList());
    }
}
