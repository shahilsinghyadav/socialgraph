package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String city;

    private String occupation;

    private int age;

    private List<String> interests;

    public Person() {
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        this.interests = new ArrayList<>();
    }

    // ─── Getters ───────────────────────────────────────────────────────────────

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAge() {
        return age;
    }

    public List<String> getInterests() {
        return interests;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // ─── Setters ───────────────────────────────────────────────────────────────

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addInterest(String interest) {
        if (!interest.isBlank()) {
            this.interests.add(interest.trim());
        }
    }

    // ─── Display ───────────────────────────────────────────────────────────────

    @Override

    public String toString() {
        return String.format(

        id,getFullName(),
                age,
                email,
                phone,
                city,
                occupation,
                String.join(", ", interests)
    );
    }

    public String toShortString() {
        return String.format(
                "[%s] %s (%s, %s)", id, getFullName(), city, occupation);
    }
}
