package dao;

import model.TicketModel;

import java.util.List;

public interface TicketsDAO {
    List<TicketModel> getAll();
}
