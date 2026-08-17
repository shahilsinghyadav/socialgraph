package org.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final SocialGraph graph   = new SocialGraph();
    private static final Scanner     scanner = new Scanner(System.in);

    // ═══════════════════════════════════════════════════════════════════════════
    //  ENTRY POINT
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        printBanner();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = prompt("Choose an option").trim();

            switch (choice) {
                case "1"  -> registerPerson();
                case "2"  -> connectPeople();
                case "3"  -> viewPerson();
                case "4"  -> viewDirectConnections();
                case "5"  -> findShortestPath();
                case "6"  -> viewNetworkTree();
                case "7"  -> viewMutualConnections();
                case "8"  -> viewFriendSuggestions();
                case "9"  -> searchPerson();
                case "10" -> GraphVisualizer.printAllConnections(graph);
                case "11" -> GraphVisualizer.printStats(graph);
                case "12" -> loadSampleData();
                case "0"  -> { IO.println("Goodbye! 👋"); running = false; }
                default   -> IO.println("⚠  Invalid option. Try again.");
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  MENU
    // ═══════════════════════════════════════════════════════════════════════════

    static void printBanner() {
        IO.println("""

                ╔══════════════════════════════════════════════════╗
                ║         SOCIAL NETWORK GRAPH SYSTEM              ║
                ║     Track & Discover People Connections          ║
                ╚══════════════════════════════════════════════════╝
                """);
    }

    static void printMenu() {
        IO.println("""

                ┌─────────────────────────────────────────────────┐
                │                    MAIN MENU                    │
                ├─────────────────────────────────────────────────┤
                │  1.  Register new person                        │
                │  2.  Connect two people                         │
                │  3.  View person profile                        │
                │  4.  View direct connections                    │
                │  5.  Find shortest path between two people      │
                │  6.  View network tree (degrees)                │
                │  7.  Find mutual connections                    │
                │  8.  Get friend suggestions                     │
                │  9.  Search person by name / city               │
                │  10. View all connections                       │
                │  11. Graph statistics                           │
                │  12. Load sample data                           │
                │  0.  Exit                                       │
                └─────────────────────────────────────────────────┘
                """);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  1. REGISTER PERSON
    // ═══════════════════════════════════════════════════════════════════════════

    static void registerPerson() {
        IO.println("\n── Register New Person ──────────────────────────");

        Person p = new Person();

        p.setFirstName(prompt("  First Name"));
        p.setLastName(prompt("  Last Name"));

        // Age validation
        while (true) {
            try {
                p.setAge(Integer.parseInt(prompt("  Age")));
                break;
            } catch (NumberFormatException e) {
                IO.println("  ⚠  Please enter a valid number.");
            }
        }

        p.setEmail(prompt("  Email"));
        p.setPhone(prompt("  Phone"));
        p.setCity(prompt("  City"));
        p.setOccupation(prompt("  Occupation"));

        String interestsRaw = prompt("  Interests (comma-separated, e.g. coding,music,travel)");
        for (String interest : interestsRaw.split(",")) {
            p.addInterest(interest);
        }

        graph.addPerson(p);
        IO.println(p.toString());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  2. CONNECT TWO PEOPLE
    // ═══════════════════════════════════════════════════════════════════════════

    static void connectPeople() {
        IO.println("\n── Connect Two People ──────────────────────────");
        listAllIds();

        String idA = prompt("  Enter ID of Person A").toUpperCase();
        String idB = prompt("  Enter ID of Person B").toUpperCase();

        IO.println("""
                  Relation types:
                  1. FRIEND
                  2. COLLEAGUE
                  3. FAMILY
                  4. ACQUAINTANCE
                  5. FOLLOWED
                """);

        Connection.RelationType type = switch (prompt("  Choose relation type (1-5)").trim()) {
            case "2" -> Connection.RelationType.COLLEAGUE;
            case "3" -> Connection.RelationType.FAMILY;
            case "4" -> Connection.RelationType.ACQUAINTANCE;
            case "5" -> Connection.RelationType.FOLLOWED;
            default  -> Connection.RelationType.FRIEND;
        };

        graph.connect(idA, idB, type);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  3. VIEW PERSON PROFILE
    // ═══════════════════════════════════════════════════════════════════════════

    static void viewPerson() {
        IO.println("\n── View Person Profile ──────────────────────────");
        listAllIds();
        String id = prompt("  Enter person ID").toUpperCase();
        Person p  = graph.findById(id);
        if (p == null) {
            IO.println("⚠  Person not found.");
        } else {
            IO.println(p.toString());
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  4. VIEW DIRECT CONNECTIONS
    // ═══════════════════════════════════════════════════════════════════════════

    static void viewDirectConnections() {
        IO.println("\n── Direct Connections ──────────────────────────");
        listAllIds();
        String      id      = prompt("  Enter person ID").toUpperCase();
        Person      person  = graph.findById(id);
        List<Person> direct = graph.getDirectConnections(id);

        if (person == null) { IO.println("⚠  Not found."); return; }

        IO.println("\n  " + person.getFullName() + " is directly connected to:");
        if (direct.isEmpty()) {
            IO.println("  (no connections yet)");
        } else {
            direct.forEach(p -> IO.println("   • " + p.toShortString()));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  5. SHORTEST PATH
    // ═══════════════════════════════════════════════════════════════════════════

    static void findShortestPath() {
        IO.println("\n── Shortest Path ──────────────────────────────");
        listAllIds();
        String fromId = prompt("  From person ID").toUpperCase();
        String toId   = prompt("  To person ID").toUpperCase();
        GraphVisualizer.printShortestPath(graph, fromId, toId);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  6. NETWORK TREE
    // ═══════════════════════════════════════════════════════════════════════════

    static void viewNetworkTree() {
        IO.println("\n── Network Tree ────────────────────────────────");
        listAllIds();
        String id = prompt("  Enter root person ID").toUpperCase();
        int depth;
        try {
            depth = Integer.parseInt(prompt("  Max degrees (e.g. 2 or 3)"));
        } catch (NumberFormatException e) {
            depth = 2;
        }
        GraphVisualizer.printNetworkTree(graph, id, depth);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  7. MUTUAL CONNECTIONS
    // ═══════════════════════════════════════════════════════════════════════════

    static void viewMutualConnections() {
        IO.println("\n── Mutual Connections ──────────────────────────");
        listAllIds();
        String       idA    = prompt("  Person A ID").toUpperCase();
        String       idB    = prompt("  Person B ID").toUpperCase();
        List<Person> mutual = graph.getMutualConnections(idA, idB);

        IO.println("\n  Mutual connections between "
                + graph.findById(idA).getFullName()
                + " and "
                + graph.findById(idB).getFullName() + ":");

        if (mutual.isEmpty()) {
            IO.println("  (none)");
        } else {
            mutual.forEach(p -> IO.println("  • " + p.toShortString()));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  8. FRIEND SUGGESTIONS
    // ═══════════════════════════════════════════════════════════════════════════

    static void viewFriendSuggestions() {
        IO.println("\n── Friend Suggestions ──────────────────────────");
        listAllIds();
        String       id          = prompt("  Enter person ID").toUpperCase();
        Person       person      = graph.findById(id);
        List<Person> suggestions = graph.getSuggestions(id);

        if (person == null) { IO.println("⚠  Not found."); return; }

        IO.println("\n  Suggested connections for " + person.getFullName() + ":");
        if (suggestions.isEmpty()) {
            IO.println("  (no suggestions available yet)");
        } else {
            suggestions.forEach(p -> IO.println("  👤 " + p.toShortString()));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  9. SEARCH PERSON
    // ═══════════════════════════════════════════════════════════════════════════

    static void searchPerson() {
        IO.println("\n── Search Person ───────────────────────────────");
        IO.println("  1. By Name");
        IO.println("  2. By City");
        String opt = prompt("  Choose").trim();

        if (opt.equals("1")) {
            String       name   = prompt("  Enter name");
            List<Person> result = graph.findByName(name);
            printSearchResult(result);
        } else {
            String       city   = prompt("  Enter city");
            List<Person> result = graph.findByCity(city);
            printSearchResult(result);
        }
    }

    private static void printSearchResult(List<Person> result) {
        if (result.isEmpty()) {
            IO.println("  No results found.");
        } else {
            IO.println("\n  Results found: " + result.size());
            result.forEach(p -> IO.println("  " + p.toShortString()));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  12. LOAD SAMPLE DATA
    // ═══════════════════════════════════════════════════════════════════════════

    static void loadSampleData() {
        IO.println("\n── Loading Sample Data ─────────────────────────");

        // Create people
        Person alice = buildPerson("Alice","Reyes", 28, "alice@mail.com",
                "09111111111", "Manila",    "Software Engineer", "coding,hiking");
        Person bob   = buildPerson("Bob",  "Cruz",  32, "bob@mail.com",
                "09222222222", "Cebu",      "Designer",          "art,travel");
        Person carol = buildPerson("Carol","Santos",25, "carol@mail.com",
                "09333333333", "Manila",    "Data Analyst",      "data,music");
        Person dave  = buildPerson("Dave", "Lim",   30, "dave@mail.com",
                "09444444444", "Davao",     "Engineer",          "coding,gaming");
        Person eve   = buildPerson("Eve",  "Torres",27, "eve@mail.com",
                "09555555555", "Cebu",      "Teacher",           "reading,travel");
        Person frank = buildPerson("Frank","Gomez", 35, "frank@mail.com",
                "09666666666", "Manila",    "Manager",           "sports,cooking");

        // Add to graph
        graph.addPerson(alice);
        graph.addPerson(bob);
        graph.addPerson(carol);
        graph.addPerson(dave);
        graph.addPerson(eve);
        graph.addPerson(frank);

        // Connect
        graph.connect(alice.getId(), bob.getId(),   Connection.RelationType.FRIEND);
        graph.connect(alice.getId(), carol.getId(),  Connection.RelationType.COLLEAGUE);
        graph.connect(bob.getId(),   dave.getId(),   Connection.RelationType.FRIEND);
        graph.connect(carol.getId(), dave.getId(),   Connection.RelationType.ACQUAINTANCE);
        graph.connect(dave.getId(),  eve.getId(),    Connection.RelationType.FRIEND);
        graph.connect(eve.getId(),   frank.getId(),  Connection.RelationType.COLLEAGUE);
        graph.connect(alice.getId(), frank.getId(),  Connection.RelationType.FAMILY);

        IO.println("\n✔  Sample data loaded! Use the menu to explore.");
        GraphVisualizer.printStats(graph);
    }

    private static Person buildPerson(String first, String last, int age,
                                      String email, String phone,
                                      String city, String occupation,
                                      String interests) {
        Person p = new Person();
        p.setFirstName(first);
        p.setLastName(last);
        p.setAge(age);
        p.setEmail(email);
        p.setPhone(phone);
        p.setCity(city);
        p.setOccupation(occupation);
        for (String i : interests.split(",")) p.addInterest(i);
        return p;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  HELPERS
    // ═══════════════════════════════════════════════════════════════════════════

    static void listAllIds() {
        if (graph.totalPeople() == 0) {
            IO.println("  (no people registered yet — try option 12 to load sample data)");
            return;
        }
        IO.println("  Registered people:");
        graph.getAllPeople().forEach(p -> IO.println("    " + p.toShortString()));
    }

    static String prompt(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
}