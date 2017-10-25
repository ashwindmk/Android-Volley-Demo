package com.example.ashwin.volleytest.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by ashwin on 2/6/17.
 */

@JsonObject
public class Employee {

    @JsonField(name = "id")
    private String id;

    @JsonField(name = "name")
    private String name;

    @JsonField(name = "email")
    private String email;

    @JsonField(name = "company")
    private String company;

    @JsonField(name = "age")
    private int age;

    @JsonField(name = "salary")
    private double salary;

    public Employee() {
        // Public default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
