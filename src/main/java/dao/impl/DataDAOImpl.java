package dao.impl;

import dao.DataDAO;
import dao.OrganizationDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import model.OrganizationModel;
import model.TicketModel;
import model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class DataDAOImpl implements DataDAO {

    private final TicketsDAO ticketsDAO;
    private final UserDAO userDAO;
    private final OrganizationDAO organizationDAO;
    private List<UserModel> users;
    private List<TicketModel> tickets;
    private List<OrganizationModel> organizations;
    public static DataDAOImpl DATA_DAO_IMPL=new DataDAOImpl();

    private DataDAOImpl() {
        organizationDAO = new OrganizationDAOImpl();
        userDAO = new UserDAOImpl();
        ticketsDAO = new TicketDAOImpl();
        this.init();
    }

    @Override
    public List<UserModel> getUserList() {
        return users;
    }

    @Override
    public List<TicketModel> getTicketList() {
        return tickets;
    }

    @Override
    public List<OrganizationModel> getOrganizationList() {
        return organizations;
    }

    private void init() {
        this.users=userDAO.getAll();
        this.organizations=organizationDAO.getAll();
        this.tickets=ticketsDAO.getAll();
    }

    public static DataDAOImpl getInstance(){
        return DATA_DAO_IMPL;
    };
}
