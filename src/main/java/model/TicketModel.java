package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Data
@Entity
@AllArgsConstructor
public class TicketModel {

    @Id
    private String _id;
    private String url;
    private String external_id;
    private String created_at;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private int submitter_id;
    private int assignee_id;
    private int organization_id;
    private String[] tags;
    private boolean has_incidents;
    private String due_at;
    private String via;

    private OrganizationModel organization;
    private UserModel submitter;
    private UserModel assignee;

    public TicketModel() {}

    public int getSubmitter_id() {
        return submitter_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setOrganization(OrganizationModel organization) {
        this.organization = organization;
    }

    public void setSubmitter(UserModel submitter) {
        this.submitter = submitter;
    }

    public void setAssignee(UserModel assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "_id\t\t\t\t\t" + _id +
                "\n url\t\t\t\t" + url +
                "\n external_id\t\t" + external_id +
                "\n created_at\t\t\t" + created_at +
                "\n type\t\t\t\t" + type +
                "\n subject\t\t\t" + subject +
                "\n description\t\t" + description +
                "\n priority\t\t\t" + priority +
                "\n status\t\t\t\t" + status +
                "\n submitter_id\t\t" + submitter_id +
                "\n assignee_id\t\t" + assignee_id +
                "\n organization_id\t" + organization_id +
                "\n tags\t\t\t\t" + Arrays.toString(tags) +
                "\n has_incidents\t\t" + has_incidents +
                "\n due_at\t\t\t\t" + due_at +
                "\n via\t\t\t\t" + via+
                "\n organization_name\t"+((organization!=null)?organization.getName():"")+
                "\n submitter_name\t\t"+((submitter!=null)?submitter.getName():"")+
                "\n assignee_name\t\t"+((assignee!=null)?assignee.getName():"");
    }
}


