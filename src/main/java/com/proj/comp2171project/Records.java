package com.proj.comp2171project;

public class Records {

        private int id;
        private String fname;
        private String lname;
        private String company;

        public Records(int id, String fname, String lname, String company) {
            this.id = id;
            this.fname = fname;
            this.lname = lname;
            this.company = company;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }

