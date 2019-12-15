package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DataDAO;
import dao.OrganizationDAO;
import dao.TicketsDAO;
import dao.impl.DataDAOImpl;
import dao.impl.OrganizationDAOImpl;
import dao.impl.TicketDAOImpl;
import model.OrganizationModel;
import model.TicketModel;
import org.springframework.stereotype.Service;
import service.OrganizationService;
import service.TicketService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final DataDAO dataDAO;
    private final String[] fields={"_id", "url", "external_id", "name", "domain_names", "created_at", "details", "shared_tickets", "tags"};

    public OrganizationServiceImpl() {
        dataDAO = DataDAOImpl.getInstance();
    }

    @Override
    public List<OrganizationModel> search(String field, String value) {
        final ObjectMapper objectMapper = new ObjectMapper();
        if (value.equalsIgnoreCase("*")) {
            return this.map(dataDAO.getOrganizationList());
        } else {
            return this.map(dataDAO.getOrganizationList().parallelStream().filter(organization -> {
                Map<String, Object> map = objectMapper.convertValue(organization, Map.class);
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

    private List<OrganizationModel> map(List<OrganizationModel> list){
        return list.parallelStream().map(organization -> {
            organization.setTickets(dataDAO.getTicketList().stream().filter(ticket -> ticket.getOrganization_id() == organization.get_id()).map(ticket->ticket.getSubject()).toArray(String[]::new));
            organization.setUsers(dataDAO.getUserList().stream().filter(user -> user.getOrganization_id() == organization.get_id()).map(user->user.getName()).toArray(String[]::new));
            return organization;
        }).collect(Collectors.toList());
    }
}
