package com.uok.servelet_application;

public class Student {
    private String id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String year;
    private String address;
    private String email;
    private String phoneNumber;
    private String grade;

    public Student(String id, String name, String gender, String dateOfBirth, String year, String address, String email, String phoneNumber, String grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.year = year;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.grade = grade;
    }

    // Getters for each field
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getYear() {
        return year;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGrade() {
        return grade;
    }
}
