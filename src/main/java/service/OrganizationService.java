package service;

import model.OrganizationModel;

import java.util.List;

public interface OrganizationService {
    List<OrganizationModel> search(String field, String value);
    List<String> getSerchableField();
    boolean isValidField(String field);
}
