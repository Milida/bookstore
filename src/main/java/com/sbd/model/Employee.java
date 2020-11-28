package com.sbd.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "employees")
@SuppressWarnings("serial")
public class Employee implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Employee() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
