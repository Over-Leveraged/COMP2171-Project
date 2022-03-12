package com.proj.comp2171project;

public class ModelTabel {
    int id;
    String firstname,lastname,company,contact;

    public ModelTabel(int id, String firstname, String lastname, String company, String contact) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getContact() {
        return contact;
    }
}
