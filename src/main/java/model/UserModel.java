package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
public class UserModel {

    @Id
    private int _id;
    private String url;
    private String external_id;
    private String name;
    private String alias;
    private String created_at;
    private boolean active;
    private boolean verified;
    private boolean shared;
    private String locale;
    private String timezone;
    private String last_login_at;
    private String email;
    private String phone;
    private String signature;

    private int organization_id;
    private String[] tags;
    private boolean suspended;
    private String role;

    private OrganizationModel organization;
    private String[] submitted_tickets;
    private String[] assignee_tickets;

    public UserModel(){}

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization(OrganizationModel organization) {
        this.organization = organization;
    }

    public void setSubmitted_tickets(String[] submitted_tickets) {
        this.submitted_tickets = submitted_tickets;
    }

    public void setAssignee_tickets(String[] assignee_tickets) {
        this.assignee_tickets = assignee_tickets;
    }

    @Override
    public String toString() {
        return "_id\t\t\t\t\t" + _id +
                "\n url\t\t\t\t" + url +
                "\n external_id\t\t" + external_id +
                "\n name\t\t\t\t" + name +
                "\n alias\t\t\t\t" + alias +
                "\n created_at\t\t\t" + created_at +
                "\n active\t\t\t\t" + active +
                "\n verified\t\t\t" + verified +
                "\n shared\t\t\t\t" + shared +
                "\n locale\t\t\t\t" + locale +
                "\n timezone\t\t\t" + timezone +
                "\n last_login_at\t\t" + last_login_at +
                "\n email\t\t\t\t" + email +
                "\n phone\t\t\t\t" + phone +
                "\n signature\t\t\t" + signature +
                "\n organization_id\t" + organization_id +
                "\n tags\t\t\t\t" + Arrays.toString(tags) +
                "\n suspended\t\t\t" + suspended +
                "\n role\t\t\t\t" + role  +
                "\n organization_id\t" + ((organization!=null)?organization.getName():"")  +
                "\n submitted_tickets\t" + Arrays.toString(submitted_tickets) +
                "\n assignee_tickets\t" + Arrays.toString(assignee_tickets) ;
    }
}
