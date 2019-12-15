package service;

import model.TicketModel;

import java.util.List;

public interface TicketService {
    List<TicketModel> search(String field, String value);
    List<String> getSerchableField();
    boolean isValidField(String field);
}
