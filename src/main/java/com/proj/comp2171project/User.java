package com.proj.comp2171project;

public class User {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private int id;

//    public User(String fname, String lname, String email, String password){
//        this.fname = fname;
//        this.lname = lname;
//        this.email = email;
//        this.password = password;
//    }
    public User(int id,String fname, String lname, String email, String password){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFname(){
        return fname;
    }

    public String getLname(){
        return lname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setFname(String fname){
        this.fname = fname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
