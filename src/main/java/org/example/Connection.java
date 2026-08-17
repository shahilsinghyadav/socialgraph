package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Connection {

    public enum RelationType {
        FRIEND,
        COLLEAGUE,
        FAMILY,
        ACQUAINTANCE,
        FOLLOWED
    }

    private Person personA;
    private Person personB;
    private RelationType type;
    private String connectedAt;

    public Connection(Person personA, Person personB, RelationType type) {
        this.personA      = personA;
        this.personB      = personB;
        this.type         = type;
        this.connectedAt  = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Person getPersonA()     { return personA; }
    public Person getPersonB()     { return personB; }
    public RelationType getType()  { return type; }
    public String getConnectedAt() { return connectedAt; }

    public boolean involves(Person p) {
        return personA.getId().equals(p.getId())
                || personB.getId().equals(p.getId());
    }

    public Person getOther(Person p) {
        return personA.getId().equals(p.getId()) ? personB : personA;
    }

    @Override
    public String toString() {
        return String.format("  %s  ←[%s]→  %s  (since %s)",
                personA.getFullName(),
                type,
                personB.getFullName(),
                connectedAt
        );
    }
}