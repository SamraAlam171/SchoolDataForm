package com.example.schooldataform;

public class Person {
    private String name;
    private String fatherName;
    private String dob;
    private String cnic;
    private String city;
    private String gender;

    // Constructor
    public Person(String name, String fatherName, String dob, String cnic, String city, String gender) {
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.cnic = cnic;
        this.city = city;
        this.gender = gender;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getDob() {
        return dob;
    }

    public String getCnic() {
        return cnic;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return String.format("Name: %s \nFatherName: %s \nDateOfBirth: %s \nCNIC: %s \nCity: %s \nGender: %s", name,fatherName,dob,cnic,city,gender);
    }
}
