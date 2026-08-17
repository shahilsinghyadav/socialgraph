package org.example;
import java.util.List;
import java.util.Map;
public class GraphVisualizer {

    // ─── Print All Connections ─────────────────────────────────────────────────
    public static void printAllConnections(SocialGraph graph) {
        IO.println("\n╔══════════════════════════════════════════════════╗");
        IO.println(  "║            ALL CONNECTIONS IN GRAPH              ║");
        IO.println(  "╚══════════════════════════════════════════════════╝");

        if (graph.getAllConnections().isEmpty()) {
            IO.println("  (no connections yet)");
            return;
        }

        for (Connection c : graph.getAllConnections()) {
            IO.println(c.toString());
        }

    }

    // ─── Print Network Tree ────────────────────────────────────────────────────
    public static void printNetworkTree(SocialGraph graph, String rootId, int maxDepth) {
        Person root = graph.findById(rootId);
        if (root == null) {
            IO.println("Person not found.");
            return;
        }

        IO.println("\n╔══════════════════════════════════════════════════╗");
        IO.println(  "║             NETWORK TREE                         ║");
        IO.println(  "╚══════════════════════════════════════════════════╝");
        Map<Integer, List<Person>> network = graph.getPeopleWithinDegrees(rootId, maxDepth);
        IO.println("● " + root.getFullName() + " [" + root.getId() + "]");

        for (Map.Entry<Integer, List<Person>> entry : network.entrySet()) {
            int         degree  = entry.getKey();
            List<Person> people = entry.getValue();
            String       indent = "  ".repeat(degree);
            String       label  = degree == 1 ? "1st" : degree == 2 ? "2nd" : degree + "th";
            IO.println(indent + "└── [" + label + " degree]");
            for (Person p : people) {
                IO.println(indent + "    ├── " + p.getFullName()
                        + " (" + p.getCity() + ")");
            }
        }
    }

    // ─── Print Shortest Path ───────────────────────────────────────────────────

    public static void printShortestPath(SocialGraph graph, String fromId, String toId) {
        IO.println("\n╔══════════════════════════════════════════════════╗");
        IO.println(  "║             SHORTEST PATH                        ║");
        IO.println(  "╚══════════════════════════════════════════════════╝");
        List<Person> path = graph.shortestPath(fromId, toId);

        if (path == null) {
            IO.println("  No connection path found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).getFullName());
            if (i < path.size() - 1) sb.append("  →  ");
        }

        IO.println("  Path (" + (path.size() - 1) + " degree/s): " + sb);
    }

    // ─── Print Stats ───────────────────────────────────────────────────────────

    public static void printStats(SocialGraph graph) {
        IO.println("\n╔══════════════════════════════════════════════════╗");
        IO.println(  "║                  GRAPH STATS                     ║");
        IO.println(  "╚══════════════════════════════════════════════════╝");
        IO.println("  Total People      : " + graph.totalPeople());
        IO.println("  Total Connections : " + graph.totalConnections());
        IO.println("\n  ── Members ──");

        for (Person p : graph.getAllPeople()) {
            int degree = graph.getDirectConnections(p.getId()).size();
            IO.println("  " + p.toShortString() + "  →  " + degree + " connection(s)");
        }
    }
}
