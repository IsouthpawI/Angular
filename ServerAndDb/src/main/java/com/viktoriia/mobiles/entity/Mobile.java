package com.viktoriia.mobiles.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


//Entity представляет данные одной записи (record) таблицы
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mobiles")
public class Mobile {

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Id
    @Column(name = "phonenumberm", nullable = false)
    private long phoneNumberM;

    @Column(name = "phonenumberh", nullable = false)
    private long phoneNumberH;

    @Temporal(TemporalType.DATE)
    @Column(name = "added", nullable = false)
    private Date added;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mobile")
//    private Set<PersonNumber> personNumbers;

    public Mobile() {

    }

    public Mobile(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH, Date added) {
        this.phoneNumberM = phoneNumberM;
        this.phoneNumberH = phoneNumberH;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.added = added;
    }

//    public Set<PersonNumber> getPersonNumbers() {
//        return personNumbers;
//    }
//
//    public void setPersonNumbers(Set<PersonNumber> personNumbers) {
//        this.personNumbers = personNumbers;
//    }

    public void setPhoneNumberM(long phoneNumberM) {
        this.phoneNumberM = phoneNumberM;
    }

    public void setPhoneNumberH(long phoneNumberH) {
        this.phoneNumberH = phoneNumberH;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdded(Date added) {
        this.added = added;
    }


    public long getPhoneNumberM() {
        return phoneNumberM;
    }

    public long getPhoneNumberH() {
        return phoneNumberH;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Date getAdded() {
        return added;
    }

    @Override
    public String toString() {
        return "Mobile {" +
                "phoneNumberM='" + phoneNumberM + '\'' +
                ", phoneNumberH='" + phoneNumberH + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", added=" + added +
                '}';
    }

}
