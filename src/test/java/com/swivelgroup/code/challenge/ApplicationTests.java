package com.swivelgroup.code.challenge;

import model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.OrganizationService;
import service.TicketService;
import service.UserService;
import service.impl.OrganizationServiceImpl;
import service.impl.TicketServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;


@SpringBootTest(properties = {"job.autorun.enabled=false"})
class ApplicationTests {
    private final UserService userService;
    private final TicketService ticketService;
    private final OrganizationService organizationService;

    ApplicationTests() {
        userService = new UserServiceImpl();
        ticketService = new TicketServiceImpl();
        organizationService = new OrganizationServiceImpl();
    }

    @Test
    void searchUserWithId(){
    	List<UserModel> userList=userService.search("_id","13");
    	Assertions.assertEquals(userList.get(0).get_id(),13);
	}

	@Test
	void serchUserWithName(){
		List<UserModel> userList=userService.search("name","Jeri Estrada");
		Assertions.assertEquals(userList.get(0).getName(),"Jeri Estrada");
	}

	@Test
	void getSerachableFields(){
       List<String> list= organizationService.getSerchableField();
       Assertions.assertEquals(list, Arrays.asList(new String[]{"_id", "url", "external_id", "name", "domain_names", "created_at", "details", "shared_tickets", "tags"}));
    }

}
