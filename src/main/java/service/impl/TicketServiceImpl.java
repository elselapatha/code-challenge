package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DataDAO;
import dao.OrganizationDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import dao.impl.DataDAOImpl;
import dao.impl.OrganizationDAOImpl;
import dao.impl.TicketDAOImpl;
import dao.impl.UserDAOImpl;
import model.OrganizationModel;
import model.TicketModel;
import model.UserModel;
import org.springframework.stereotype.Service;
import service.TicketService;
import service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final DataDAO dataDAO;
    private final String[] fields = {"_id", "url", "external_id", "created_at", "type", "subject", "description", "priority", "status", "submitter_id", "assignee_id", "organization_id", "tags", "has_incidents", "due_at", "via"};

    public TicketServiceImpl() {
        dataDAO = DataDAOImpl.getInstance();
    }

    @Override
    public List<TicketModel> search(String field, String value) {
        final ObjectMapper objectMapper = new ObjectMapper();
        if (value.equalsIgnoreCase("*")) {
            return this.map(dataDAO.getTicketList());
        } else {
            return this.map(dataDAO.getTicketList().parallelStream().filter(ticket -> {
                Map<String, Object> map = objectMapper.convertValue(ticket, Map.class);
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

    private List<TicketModel> map(List<TicketModel> list){
        return list.parallelStream().map(ticket -> {
            ticket.setAssignee(dataDAO.getUserList().stream().filter(user -> user.get_id() == ticket.getAssignee_id()).findFirst().orElse(null));
            ticket.setSubmitter(dataDAO.getUserList().stream().filter(user -> user.get_id() == ticket.getSubmitter_id()).findFirst().orElse(null));
            ticket.setOrganization(dataDAO.getOrganizationList().stream().filter(organization -> organization.get_id() == ticket.getOrganization_id()).findFirst().orElse(null));
            return ticket;
        }).collect(Collectors.toList());
    }
}
