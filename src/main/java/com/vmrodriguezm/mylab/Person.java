package com.vmrodriguezm.mylab;

import java.time.LocalDate;

public class Person {
    private String DNI;
    private String name;
    private Integer age;
    private Address adress;
    private String gender;
    private LocalDate registerDate;
    private String email;

    public Person(String DNI, String name, Integer age, Address adress, String gender,
            LocalDate registerDate, String email) {
        super();
        this.name = name;
        this.age = age;
        this.adress = adress;
        this.gender = gender;
        this.registerDate = registerDate;
        this.email = email;
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
