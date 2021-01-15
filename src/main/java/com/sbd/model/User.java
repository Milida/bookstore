package com.sbd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
    
    @JsonProperty(access = Access.WRITE_ONLY)
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

    @JsonIgnoreProperties({"user", "hibernateLazyInitializer"})
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(nullable = false)
    private Boolean isActive = true;

    @JsonIgnoreProperties({"users", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

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

    public User(String firstname, String lastname, String email, String password, String phone, String address,
            String postalCode, String city, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.isActive = true;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
