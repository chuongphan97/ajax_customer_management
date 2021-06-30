package com.codegym.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;
    private String country_name;

    @JsonIgnore
    @OneToMany(targetEntity = Customer.class)
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Country() {
    }

    public Country(Long country_id) {
        this.country_id = country_id;
    }

    public Country(Long country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    public Country(String country_name) {
        this.country_name = country_name;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
