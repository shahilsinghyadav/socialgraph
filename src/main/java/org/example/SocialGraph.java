package org.example;

import java.util.*;

public class SocialGraph {

    // Adjacency list: personId → list of Connections
    private final Map<String, Person>           people      = new LinkedHashMap<>();
    private final Map<String, List<Connection>> adjacency   = new LinkedHashMap<>();
    private final List<Connection>              connections = new ArrayList<>();

    // ─── Add Person ────────────────────────────────────────────────────────────

    public void addPerson(Person person) {
        people.put(person.getId(), person);
        adjacency.put(person.getId(), new ArrayList<>());
        IO.println("✔  Person added → " + person.toShortString());
    }

    // ─── Connect Two People ────────────────────────────────────────────────────

    public boolean connect(String idA, String idB, Connection.RelationType type) {
        Person a = people.get(idA);
        Person b = people.get(idB);

        if (a == null || b == null) {
            IO.println("✘  One or both IDs not found.");
            return false;
        }

        if (alreadyConnected(idA, idB)) {
            IO.println("⚠  Already connected.");
            return false;
        }

        Connection conn = new Connection(a, b, type);
        adjacency.get(idA).add(conn);
        adjacency.get(idB).add(conn);   // undirected graph
        connections.add(conn);

        IO.println("✔  Connected: " + a.getFullName() + " ↔ " + b.getFullName()
                + " [" + type + "]");
        return true;
    }

    private boolean alreadyConnected(String idA, String idB) {
        for (Connection c : adjacency.getOrDefault(idA, Collections.emptyList())) {
            if (c.involves(people.get(idB))) return true;
        }
        return false;
    }

    // ─── Find Person ───────────────────────────────────────────────────────────

    public Person findById(String id) {
        return people.get(id.toUpperCase());
    }

    public List<Person> findByName(String name) {
        List<Person> result = new ArrayList<>();
        String lower = name.toLowerCase();
        for (Person p : people.values()) {
            if (p.getFullName().toLowerCase().contains(lower)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Person> findByCity(String city) {
        List<Person> result = new ArrayList<>();
        for (Person p : people.values()) {
            if (p.getCity().equalsIgnoreCase(city)) result.add(p);
        }
        return result;
    }

    // ─── Direct Friends ────────────────────────────────────────────────────────

    public List<Person> getDirectConnections(String id) {
        List<Person> result = new ArrayList<>();
        Person p = people.get(id);
        if (p == null) return result;

        for (Connection c : adjacency.getOrDefault(id, Collections.emptyList())) {
            result.add(c.getOther(p));
        }
        return result;
    }

    // ─── BFS: Shortest Path ────────────────────────────────────────────────────

    public List<Person> shortestPath(String fromId, String toId) {
        if (!people.containsKey(fromId) || !people.containsKey(toId)) return null;

        Map<String, String> prev    = new HashMap<>();
        Queue<String>       queue   = new LinkedList<>();
        Set<String>         visited = new HashSet<>();

        queue.add(fromId);
        visited.add(fromId);
        prev.put(fromId, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(toId)) break;

            for (Connection c : adjacency.getOrDefault(current, Collections.emptyList())) {
                String neighborId = c.getOther(people.get(current)).getId();
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    prev.put(neighborId, current);
                    queue.add(neighborId);
                }
            }
        }

        // Reconstruct path
        if (!prev.containsKey(toId)) return null; // no path

        List<Person> path = new ArrayList<>();
        String step = toId;
        while (step != null) {
            path.add(0, people.get(step));
            step = prev.get(step);
        }
        return path;
    }

    // ─── BFS: All People Within N Degrees ─────────────────────────────────────

    public Map<Integer, List<Person>> getPeopleWithinDegrees(String id, int maxDegree) {
        Map<Integer, List<Person>> result  = new TreeMap<>();
        Queue<String>              queue   = new LinkedList<>();
        Map<String, Integer>       degrees = new HashMap<>();

        queue.add(id);
        degrees.put(id, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int    degree  = degrees.get(current);

            if (degree >= maxDegree) continue;

            for (Connection c : adjacency.getOrDefault(current, Collections.emptyList())) {
                String neighborId = c.getOther(people.get(current)).getId();
                if (!degrees.containsKey(neighborId)) {
                    int newDegree = degree + 1;
                    degrees.put(neighborId, newDegree);
                    result.computeIfAbsent(newDegree, k -> new ArrayList<>())
                            .add(people.get(neighborId));
                    queue.add(neighborId);
                }
            }
        }
        return result;
    }

    // ─── Mutual Connections ────────────────────────────────────────────────────

    public List<Person> getMutualConnections(String idA, String idB) {
        Set<String> setA = new HashSet<>();
        Person pA = people.get(idA);
        Person pB = people.get(idB);
        if (pA == null || pB == null) return Collections.emptyList();

        for (Connection c : adjacency.getOrDefault(idA, Collections.emptyList())) {
            setA.add(c.getOther(pA).getId());
        }

        List<Person> mutual = new ArrayList<>();
        for (Connection c : adjacency.getOrDefault(idB, Collections.emptyList())) {
            Person neighbor = c.getOther(pB);
            if (setA.contains(neighbor.getId())) {
                mutual.add(neighbor);
            }
        }
        return mutual;
    }

    // ─── Suggested Friends (Friends of Friends) ────────────────────────────────

    public List<Person> getSuggestions(String id) {
        Set<String>  directIds  = new HashSet<>();
        Person       person     = people.get(id);
        if (person == null) return Collections.emptyList();

        directIds.add(id);
        for (Connection c : adjacency.getOrDefault(id, Collections.emptyList())) {
            directIds.add(c.getOther(person).getId());
        }

        Map<String, Integer> scoreMap = new LinkedHashMap<>();
        for (String directId : directIds) {
            if (directId.equals(id)) continue;
            Person direct = people.get(directId);
            for (Connection c : adjacency.getOrDefault(directId, Collections.emptyList())) {
                String suggId = c.getOther(direct).getId();
                if (!directIds.contains(suggId)) {
                    scoreMap.merge(suggId, 1, Integer::sum);
                }
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(scoreMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        List<Person> suggestions = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted) {
            suggestions.add(people.get(entry.getKey()));
        }
        return suggestions;
    }

    // ─── Getters ───────────────────────────────────────────────────────────────

    public Collection<Person>     getAllPeople()      { return people.values(); }
    public List<Connection>       getAllConnections()  { return connections; }
    public Map<String, Person>    getPeopleMap()      { return people; }
    public int                    totalPeople()       { return people.size(); }
    public int                    totalConnections()  { return connections.size(); }
}