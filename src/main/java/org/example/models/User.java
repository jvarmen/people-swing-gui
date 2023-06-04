package org.example.models;

public class User {
    String name;
    String lastName;
    String phone;
    String address;

    public User(String name, String lastName, String phone, String address) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
