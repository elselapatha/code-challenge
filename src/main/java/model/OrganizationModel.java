package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "_id",
        scope     = Integer.class)
public class OrganizationModel {

    @Id
    private int _id;
    private String url;
    private String external_id;
    private String name;
    private String[] domain_names;
    private String created_at;
    private String details;
    private boolean shared_tickets;
    private String[] tags;


    private String[] tickets;
    private String[] users;

    public OrganizationModel() {}

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "_id\t\t\t\t\t" + _id +
                "\n url\t\t\t\t" + url +
                "\n external_id\t\t" + external_id +
                "\n name\t\t\t\t" + name +
                "\n domain_names\t\t" + Arrays.toString(domain_names) +
                "\n created_at\t\t\t" + created_at +
                "\n details\t\t\t" + details +
                "\n shared_tickets\t\t" + shared_tickets +
                "\n tags\t\t\t\t" + Arrays.toString(tags) +
                "\n tickets\t\t\t" + Arrays.toString(tickets) +
                "\n users\t\t\t\t" + Arrays.toString(users) ;
    }
}
