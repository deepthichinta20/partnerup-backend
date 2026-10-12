
package com.studentproject.student_project_partner_finder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String skills;
    private String interests;
    private String availability;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSkills() {
        return skills;
    }

    public String getInterests() {
        return interests;
    }

    public String getAvailability() {
        return availability;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}

