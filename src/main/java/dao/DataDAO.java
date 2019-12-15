package dao;

import dao.impl.DataDAOImpl;
import model.OrganizationModel;
import model.TicketModel;
import model.UserModel;

import java.util.List;

public interface DataDAO {
    List<UserModel> getUserList();
    List<TicketModel> getTicketList();
    List<OrganizationModel> getOrganizationList();
}
