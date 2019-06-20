package de.tub.ise.anwsys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

    String firstName;

    String lastName;

    String studies;

    @Id
    @GeneratedValue
    long id;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
