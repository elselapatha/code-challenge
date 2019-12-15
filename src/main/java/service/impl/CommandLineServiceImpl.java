package service.impl;

import model.OrganizationModel;
import model.TicketModel;
import model.UserModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import service.CommandLineService;
import service.OrganizationService;
import service.TicketService;
import service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandLineServiceImpl implements CommandLineService, CommandLineRunner {

    private final UserService userService;
    private final TicketService ticketService;
    private final OrganizationService organizationService;

    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_BLACK = "\u001B[30m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_WHITE = "\u001B[37m";

    private final Scanner scanner = new Scanner(System.in);
    private final String[] entities = {"1", "2", "3"};

    public CommandLineServiceImpl() {
        userService = new UserServiceImpl();
        ticketService = new TicketServiceImpl();
        organizationService = new OrganizationServiceImpl();
    }

    @Override
    public void start() {
        System.out.println("Type 'quit' to exit at any time, Press Enter to Continue.");
        this.read(scanner.nextLine());
        String command = null;
        while ((command = scanner.next()) != null) {
            if (this.isQuit(command)) {
                return;
            }
            if (this.read(command)) {
                return;
            } else {
                System.out.println();
                this.mainManu();
            }
        }
    }

    private boolean read(String command) {
        switch (command) {
            case "1":
                return this.search();
            case "2":
                System.out.println(ANSI_BLUE + "------------------ Searchable Fields ------------------" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "<Search Users with>" +ANSI_YELLOW);
                userService.getSerchableField().forEach(System.out::println);
                System.out.println( ANSI_RESET+"=======================================================");
                System.out.println(ANSI_CYAN + "<Search Organization with>" + ANSI_YELLOW);
                organizationService.getSerchableField().forEach(System.out::println);
                System.out.println( ANSI_RESET+"=======================================================");
                System.out.println(ANSI_CYAN + "<Search Tickets with>" + ANSI_YELLOW);
                ticketService.getSerchableField().forEach(System.out::println);
                System.out.println( ANSI_RESET+"=======================================================");
                return false;
            default:
                this.mainManu();
                return false;
        }
    }

    private boolean search() {
        System.out.println("Select Entity:");
        System.out.println("\t1) Users");
        System.out.println("\t2) Tickets");
        System.out.println("\t3) Organizations");
        System.out.println("\t*) Go Back");
        String entity = null;
        while (!Arrays.asList(entities).contains(entity = scanner.next())) {
            if (isQuit(entity)) {
                return true;
            } else if (entity.equalsIgnoreCase("*")) {
                return false;
            }
            this.printError("Invalid Input. Please enter valid Input.");
            System.out.println("Select Entity:");
            System.out.println("\t1) Users");
            System.out.println("\t2) Tickets");
            System.out.println("\t3) Organizations");
        }

        System.out.println("Enter Search Term");
        String term = null;
        List<String> fields = entity.equals("1") ? userService.getSerchableField() : entity.equals("2") ? ticketService.getSerchableField() : entity.equalsIgnoreCase("3") ? organizationService.getSerchableField() : null;
        while (!fields.contains((term = scanner.next()))) {
            if (term.equalsIgnoreCase("-f")) {
                fields.forEach(System.out::println);
            } else {
                this.printError("Invalid Input. Please enter valid Field.");
                System.out.println("Enter -f for list of searchable fields");
            }
        }

        System.out.println("Enter Search Value");
        String value =scanner.next();
        System.out.println(ANSI_CYAN+"Criteria : Entity - '" + entity + "', Term - '" + term + "', Value - '" + value + "'"+ANSI_RESET);
        switch (entity) {
            case "1":
                List<UserModel> users = userService.search(term, value);
                if(users.isEmpty())
                    this.printError("No Records Found.");
                for (UserModel user : users) {
                    System.out.println(ANSI_YELLOW + user + ANSI_RESET);
                    System.out.println("----------------------------------------------------------------------");
                }
                break;
            case "2":
                List<TicketModel> tickets = ticketService.search(term, value);
                if(tickets.isEmpty())
                    this.printError("No Records Found.");
                for (TicketModel ticket : tickets) {
                    System.out.println(ANSI_YELLOW + ticket + ANSI_RESET);
                    System.out.println("----------------------------------------------------------------------");
                }
                break;
            case "3":
                List<OrganizationModel> organizations = organizationService.search(term, value);
                if(organizations.isEmpty())
                   this.printError("No Records Found.");
                for (OrganizationModel organization : organizations) {
                    System.out.println(ANSI_YELLOW + organization + ANSI_RESET);
                    System.out.println("----------------------------------------------------------------------");
                }
                break;
        }
        return false;
    }

    private void mainManu() {
        System.out.println("\tSelect Search Options:");
        System.out.println("\t* Press 1 to Search.");
        System.out.println("\t* Press 2 to View a list of searchable fields.");
        System.out.println("\t* Type 'quit' to Exit.");
    }

    private boolean isQuit(String command) {
        return command.equalsIgnoreCase("quit");
    }

    private void printError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
