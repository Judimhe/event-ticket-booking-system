package com.mycompany.finalcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String name;
    private String email;
    private String password;
    private String id;

    // Default constructor
    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.id = "";
    }

    public User(String name, String email, String password, String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public void login() {
        System.out.println("User " + name + " has logged in.");
    }

    public void logout() {
        System.out.println("User " + name + " has logged out.");
    }

    public void displayUserInfo() {
        System.out.println("User Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("ID: " + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

class Admin extends User {
    private String adminRole;

    public Admin() {
        super();
    }

    public Admin(String name, String email, String password, String id, String adminRole) {
        super(name, email, password, id);
        this.adminRole = adminRole;
    }

    public String getAdminRole() { 
        return adminRole; 
    }
    
    public void setAdminRole(String adminRole) { 
        this.adminRole = adminRole; 
    }

    public void addEvent(List<Events> events, Events event) {
        events.add(event);
    }

    public void updateEvent(Events event, String newName, String newDate, double newPrice) {
        event.eventName = newName;
        event.date = newDate;
        event.price = newPrice;
    }

    public void deleteEvent(List<Events> events, Events event) {
        events.remove(event);
    }

    public void manageTickets(List<Ticket> tickets) {
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }

    public void viewStatistics(List<Ticket> tickets) {
        System.out.println("Total Tickets Booked: " + tickets.size());
    }

    @Override
    public String toString() {
        return "Admin: " + getName() + " - " + adminRole;
    }
}

class Customer extends User {
    private int age;
    private String gender;
    private String[] hobbies;
    private String location;
    private List<Ticket> bookingHistory = new ArrayList<>();

    public Customer() {
        super();
    }

    public Customer(String name, String email, String password, String id,
                    int age, String gender, String[] hobbies, String location) {
        super(name, email, password, id);
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
        this.location = location;
    }

    public int getAge() { 
        return age; 
    }
    
    public void setAge(int age) { 
        this.age = age; 
    }

    public String getGender() { 
        return gender; 
    }
    
    public void setGender(String gender) { 
        this.gender = gender; 
    }

    public String[] getHobbies() { 
        return hobbies; 
    }
    
    public void setHobbies(String[] hobbies) { 
        this.hobbies = hobbies; 
    }

    public String getLocation() { 
        return location; 
    }
    
    public void setLocation(String location) { 
        this.location = location; 
    }

    public List<Ticket> getBookingHistory() {
        return bookingHistory;
    }

    public void browseEvents(List<Events> events) {
        for (Events e : events) {
            System.out.println(e.eventName + " - " + e.date + " - " + e.price + " SAR");
        }
    }

    public void searchEvents(List<Events> events, String keyword) {
        for (Events e : events) {
            if (e.eventName.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(e.eventName + " - " + e.date + " - " + e.price + " SAR");
            }
        }
    }

    public Ticket bookTicket(Events event, int ticketId) {
        Ticket t = new Ticket(ticketId, "A" + (bookingHistory.size() + 1), event.price, event);
        bookingHistory.add(t);
        return t;
    }

    public void cancelTicket(int ticketId) {
        Ticket ticketToRemove = null;
        for (Ticket t : bookingHistory) {
            if (t.getTicketId() == ticketId) {
                ticketToRemove = t;
                break;
            }
        }
        if (ticketToRemove != null) {
            bookingHistory.remove(ticketToRemove);
            ticketToRemove.cancel();
        }
    }

    public void viewRecommendations(List<Events> events) {
        System.out.println("Recommended events based on your hobbies:");
        for (Events event : events) {
            for (String hobby : hobbies) {
                if (event.eventName.toLowerCase().contains(hobby.toLowerCase())) {
                    System.out.println(event.eventName + " - " + event.date + " - " + event.price + " SAR");
                    break;
                }
            }
        }
    }

    public void viewBookingHistory() {
        System.out.println("Your Booking History:");
        for (Ticket t : bookingHistory) {
            System.out.println("Ticket ID: " + t.getTicketId() + 
                             ", Event: " + t.getEvent().eventName + 
                             ", Price: " + t.getPrice() + " SAR");
        }
    }

    @Override
    public String toString() {
        return "Customer: " + getName();
    }
}

class Events {
    public String eventName;
    public String date;
    public double price;

    public Events(String eventName, String date, double price) {
        this.eventName = eventName;
        this.date = date;
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Event: " + eventName);
        System.out.println("Date: " + date);
        System.out.println("Price: " + price + " SAR");
    }
}

class Concert extends Events {
    private String artist;

    public Concert(String eventName, String date, double price, String artist) {
        super(eventName, date, price);
        this.artist = artist;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Artist: " + artist);
    }
}

class SportEvent extends Events {
    private String teamA;
    private String teamB;

    public SportEvent(String eventName, String date, double price, String teamA, String teamB) {
        super(eventName, date, price);
        this.teamA = teamA;
        this.teamB = teamB;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Teams: " + teamA + " vs " + teamB);
    }
}

class Ticket {
    private int ticketId;
    private String seatNumber;
    private double price;
    private String status;
    private Events event;

    public Ticket(int ticketId, String seatNumber, double price, Events event) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.price = price;
        this.event = event;
        this.status = "BOOKED";
    }

    public int getTicketId() { 
        return ticketId; 
    }
    
    public String getSeatNumber() { 
        return seatNumber; 
    }
    
    public double getPrice() { 
        return price; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public Events getEvent() { 
        return event; 
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", seatNumber='" + seatNumber + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", event=" + (event != null ? event.eventName : "null") +
                '}';
    }

    public void cancel() {
        this.status = "CANCELLED";
    }
}

public class FinalCode {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<Events> events = new ArrayList<>();
    private static List<Ticket> allTickets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Customer currentCustomer = null;
    private static Admin currentAdmin = null;

    public static void main(String[] args) {
        System.out.println(" Welcome to Event & Ticket Booking System! ");
        
        // إنشاء بيانات أولية فقط لمسؤول واحد
        createInitialAdmin();

        while (true) {
            showMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> customerMenu();
                case 2 -> adminLogin();
                case 3 -> {
                    System.out.println("Thank you for using our system! 👋");
                    return;
                }
                default -> System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Customer Menu");
        System.out.println("2. Admin Login");
        System.out.println("3. Exit");
        System.out.print("Please choose an option: ");
    }

    private static void customerMenu() {
        while (true) {
            System.out.println("\n----- CUSTOMER MENU -----");
            System.out.println("1. Register New Customer");
            System.out.println("2. Login as Customer");
            System.out.println("3. Browse Events");
            System.out.println("4. Search Events");
            System.out.println("5. Book Ticket");
            System.out.println("6. View Recommendations");
            System.out.println("7. View My Bookings");
            System.out.println("8. Cancel Ticket");
            System.out.println("9. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = getUserChoice();
            switch (choice) {
                case 1 -> registerCustomer();
                case 2 -> loginCustomer();
                case 3 -> browseEvents();
                case 4 -> searchEvents();
                case 5 -> bookTicket();
                case 6 -> viewRecommendations();
                case 7 -> viewBookings();
                case 8 -> cancelTicket();
                case 9 -> {
                    System.out.println("Returning to main menu...");
                    currentCustomer = null;
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                currentAdmin = admin;
                admin.login();
                System.out.println("✅ Welcome Admin: " + admin.getName() + "!");
                adminMenu();
                return;
            }
        }
        System.out.println("❌ Invalid admin credentials!");
    }

    private static void adminMenu() {
        while (currentAdmin != null) {
            System.out.println("\n----- ADMIN MENU -----");
            System.out.println("1. Add New Event");
            System.out.println("2. Update Event");
            System.out.println("3. Delete Event");
            System.out.println("4. View All Events");
            System.out.println("5. Manage Tickets");
            System.out.println("6. View Statistics");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int choice = getUserChoice();
            switch (choice) {
                case 1 -> addEvent();
                case 2 -> updateEvent();
                case 3 -> deleteEvent();
                case 4 -> viewAllEvents();
                case 5 -> manageTickets();
                case 6 -> viewStatistics();
                case 7 -> {
                    System.out.println("Logging out...");
                    currentAdmin.logout();
                    currentAdmin = null;
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void registerCustomer() {
        System.out.println("\n--- Customer Registration ---");
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = getValidIntInput();
        
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        
        System.out.print("How many hobbies do you have? ");
        int numHobbies = getValidIntInput();
        scanner.nextLine(); // Clear buffer
        
        String[] hobbies = new String[numHobbies];
        for (int i = 0; i < numHobbies; i++) {
            System.out.print("Enter hobby " + (i + 1) + ": ");
            hobbies[i] = scanner.nextLine();
        }
        
        Customer newCustomer = new Customer(name, email, password, id, age, gender, hobbies, location);
        customers.add(newCustomer);
        
        System.out.println("✅ Customer registered successfully!");
        newCustomer.displayUserInfo();
    }

    private static void loginCustomer() {
        if (customers.isEmpty()) {
            System.out.println("❌ No customers registered yet. Please register first.");
            return;
        }
        
        System.out.println("\n--- Customer Login ---");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                currentCustomer = customer;
                customer.login();
                System.out.println("✅ Welcome, " + customer.getName() + "!");
                return;
            }
        }
        System.out.println("❌ Invalid email or password!");
    }

    private static void addEvent() {
        System.out.println("\n--- Add New Event ---");
        System.out.println("1. Concert");
        System.out.println("2. Sport Event");
        System.out.print("Choose Event Type: ");

        int type = getValidIntInput();
        scanner.nextLine(); // Clear buffer
        
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();
        
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Price: ");
        double price = getValidDoubleInput();
        scanner.nextLine(); // Clear buffer
        
        Events newEvent;

        if (type == 1) {
            System.out.print("Enter Artist: ");
            String artist = scanner.nextLine();
            newEvent = new Concert(eventName, date, price, artist);
        } else if (type == 2) {
            System.out.print("Enter Team A: ");
            String teamA = scanner.nextLine();
            System.out.print("Enter Team B: ");
            String teamB = scanner.nextLine();
            newEvent = new SportEvent(eventName, date, price, teamA, teamB);
        } else {
            System.out.println("❌ Invalid event type!");
            return;
        }

        currentAdmin.addEvent(events, newEvent);
        System.out.println("✅ Event added successfully!");
    }

    private static void updateEvent() {
        if (events.isEmpty()) {
            System.out.println("❌ No events available to update.");
            return;
        }

        viewAllEvents();
        System.out.print("Select Event Number to Update: ");
        int eventIndex = getValidIntInput() - 1;
        scanner.nextLine(); // Clear buffer

        if (eventIndex < 0 || eventIndex >= events.size()) {
            System.out.println("❌ Invalid event selection.");
            return;
        }

        Events selectedEvent = events.get(eventIndex);
        
        System.out.print("Enter New Event Name (current: " + selectedEvent.eventName + "): ");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = selectedEvent.eventName;
        }
        
        System.out.print("Enter New Date (current: " + selectedEvent.date + "): ");
        String newDate = scanner.nextLine();
        if (newDate.isEmpty()) {
            newDate = selectedEvent.date;
        }
        
        System.out.print("Enter New Price (current: " + selectedEvent.price + "): ");
        String priceInput = scanner.nextLine();
        double newPrice;
        if (priceInput.isEmpty()) {
            newPrice = selectedEvent.price;
        } else {
            newPrice = Double.parseDouble(priceInput);
        }
        
        currentAdmin.updateEvent(selectedEvent, newName, newDate, newPrice);
        System.out.println("✅ Event updated successfully!");
    }

    private static void deleteEvent() {
        if (events.isEmpty()) {
            System.out.println("❌ No events available to delete.");
            return;
        }

        viewAllEvents();
        System.out.print("Select Event Number to Delete: ");
        int eventIndex = getValidIntInput() - 1;
        scanner.nextLine(); // Clear buffer

        if (eventIndex < 0 || eventIndex >= events.size()) {
            System.out.println("❌ Invalid event selection.");
            return;
        }

        Events selectedEvent = events.get(eventIndex);
        currentAdmin.deleteEvent(events, selectedEvent);
        System.out.println("✅ Event deleted successfully!");
    }

    private static void browseEvents() {
        System.out.println("\n--- Available Events ---");
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        if (currentCustomer != null) {
            currentCustomer.browseEvents(events);
        } else {
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i).eventName +
                        " - " + events.get(i).date +
                        " - " + events.get(i).price + " SAR");
            }
        }
    }

    private static void searchEvents() {
        System.out.println("\n--- Search Events ---");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        
        if (currentCustomer != null) {
            currentCustomer.searchEvents(events, keyword);
        } else {
            System.out.println("Please login first to search events.");
        }
    }

    private static void bookTicket() {
        if (currentCustomer == null) {
            System.out.println("❌ Please login as a customer first.");
            return;
        }

        if (events.isEmpty()) {
            System.out.println("❌ No events available for booking.");
            return;
        }

        browseEvents();
        System.out.print("Select Event Number: ");
        int eventIndex = getValidIntInput() - 1;
        scanner.nextLine(); // Clear buffer

        if (eventIndex < 0 || eventIndex >= events.size()) {
            System.out.println("❌ Invalid event selection.");
            return;
        }

        Events selectedEvent = events.get(eventIndex);
        int ticketId = generateTicketId();
        Ticket ticket = currentCustomer.bookTicket(selectedEvent, ticketId);
        allTickets.add(ticket);
        
        System.out.println("✅ Ticket booked successfully!");
        System.out.println("🎫 Ticket ID: " + ticket.getTicketId());
        System.out.println("💰 Total Amount: " + ticket.getPrice() + " SAR");
        System.out.println("📅 Event: " + selectedEvent.eventName);
    }

    private static void viewRecommendations() {
        if (currentCustomer == null) {
            System.out.println("❌ Please login as a customer first.");
            return;
        }

        currentCustomer.viewRecommendations(events);
    }

    private static void viewBookings() {
        if (currentCustomer == null) {
            System.out.println("❌ Please login as a customer first.");
            return;
        }

        currentCustomer.viewBookingHistory();
    }

    private static void cancelTicket() {
        if (currentCustomer == null) {
            System.out.println("❌ Please login as a customer first.");
            return;
        }

        currentCustomer.viewBookingHistory();
        System.out.print("Enter Ticket ID to Cancel: ");
        int ticketId = getValidIntInput();
        scanner.nextLine(); // Clear buffer
        
        currentCustomer.cancelTicket(ticketId);
        System.out.println("✅ Ticket cancelled successfully!");
    }

    private static void viewAllEvents() {
        System.out.println("\n--- All Events ---");
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).eventName);
            events.get(i).displayInfo();
            System.out.println("------------------------");
        }
    }

    private static void manageTickets() {
        System.out.println("\n--- Manage All Tickets ---");
        if (allTickets.isEmpty()) {
            System.out.println("No tickets found.");
            return;
        }

        currentAdmin.manageTickets(allTickets);
    }

    private static void viewStatistics() {
        System.out.println("\n--- Statistics ---");
        currentAdmin.viewStatistics(allTickets);
        
        System.out.println("Total Events: " + events.size());
        System.out.println("Total Customers: " + customers.size());
        
        double totalRevenue = 0;
        for (Ticket ticket : allTickets) {
            if (ticket.getStatus().equals("BOOKED")) {
                totalRevenue += ticket.getPrice();
            }
        }
        System.out.println("Total Revenue: " + totalRevenue + " SAR");
    }

    private static void createInitialAdmin() {
        System.out.println("\n--- Initial Admin Setup ---");
        System.out.println("Please create the first admin account:");
        
        System.out.print("Enter Admin Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Admin Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Admin Role: ");
        String role = scanner.nextLine();
        
        Admin admin = new Admin(name, email, password, id, role);
        admins.add(admin);
        System.out.println("✅ Admin account created successfully!");
    }

    private static int getValidIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input! Please enter a valid number: ");
            }
        }
    }

    private static double getValidDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input! Please enter a valid number: ");
            }
        }
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int generateTicketId() {
        return 1000 + allTickets.size() + 1;
    }
}