package com.sbd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 63, nullable = false)
    private String firstname;

    @Column(length = 63, nullable = false)
    private String lastname;

    @Column(length = 63, nullable = false)
    private String email;

    @Column(length = 511, nullable = false)
    private String password;

    @Column(length = 15, nullable = false)
    private String phone;

    @Column(length = 127, nullable = false)
    private String address;

    @Column(length = 7, nullable = false)
    private String postalCode;

    @Column(length = 63, nullable = false)
    private String city;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Column(nullable = false)
    private Boolean isActive;

    public User() {
    }

    public User(String firstname, String lastname, String email, String password, String phone, String address,
            String postalCode, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        return id != null && id.equals(((User) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
