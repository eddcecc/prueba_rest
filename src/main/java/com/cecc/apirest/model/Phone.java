package com.cecc.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "number", columnDefinition = "VARCHAR")
    private String number;
    @Column(name = "citycode", columnDefinition = "VARCHAR")
    private String citycode;
    @Column(name = "countrycode", columnDefinition = "VARCHAR")
    private String countrycode;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore 
    private User user;


    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return this.id;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getCitycode() {
        return citycode;
    }
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountrycode() {
        return countrycode;
    }
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

}
