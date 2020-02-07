package com.viktoriia.mobiles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "personnumbers")
public class PersonNumber {
//    @Column(name = "firstnamep", nullable = false)
//    private String firstName;

    public void setId(long id) {
        this.id = id;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pn", nullable = false)
    private long id;

    @Column(name = "lastnamep", nullable = false)
    private String lastName;


    @Column(name = "firstnamep", nullable = false)
    private String firstName;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phonenumber_mobile", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mobile mobile;

    public PersonNumber() {

    }


    public PersonNumber(String lastName, String firstName, Mobile mobile) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public long getId() {
        return id;
    }

//    public long getNumber(){
//        return 34543543;
//    }

    @Override
    public String toString() {
        return "PersonNumber {" +
                "phoneNumberM='" + mobile.getPhoneNumberM() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
