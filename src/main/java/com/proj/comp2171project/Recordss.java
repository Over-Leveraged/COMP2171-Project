package com.proj.comp2171project;

import java.sql.Date;

public class Recordss {
    int id;
    String firstname,lastname,company,contact;
    Date med_exp,psra_exp,pol_exp;

    public Recordss(int id, String firstname, String lastname, String company, String contact) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.contact = contact;
    }



    public Recordss(int id, String firstname, String lastname, String company, String contact, Date med_exp,Date psra_exp,Date pol_exp) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.contact = contact;
        this.med_exp = med_exp;
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

    public Date getMed_exp() {
        return med_exp;
    }
    public Date getPsra_exp() {
        return psra_exp;
    }

    public Date getPol_exp() {
        return pol_exp;
    }

}
