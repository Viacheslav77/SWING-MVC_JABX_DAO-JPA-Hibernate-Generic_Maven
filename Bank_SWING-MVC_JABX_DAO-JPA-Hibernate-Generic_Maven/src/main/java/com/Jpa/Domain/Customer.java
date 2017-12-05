package com.Jpa.Domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String position;
    
    @Column
    private String phone;

    @Column(nullable = false)
    private String name;
    
    @Column
    private String firstName;
    
    @OneToMany(mappedBy = "customer", cascade = {REFRESH, REMOVE})
    private List<Account> accounts = new ArrayList<Account>();


    @OneToMany(mappedBy = "customer", cascade = REFRESH)
    private List<Transaction> transactions = new ArrayList<Transaction>();


    public Customer() {}


    public Customer(String name) {
        this.name = Objects.requireNonNull(name);
    }
    
    public Customer(String name, String firstName, String phone, String position) {
		
    	this.name = name;
    	this.firstName=firstName;
		this.position = position;
		this.phone = phone;
		
	}


	public String getName() throws Exception {
        return name;
    }
	
	public void setName(String name) {
        this.name=name;
    }


    public List<Account> getAccounts() {
        return accounts;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", position=" + position + ", phone=" + phone + ", name=" + name + ", firstName="
				+ firstName + ", accounts=" + accounts + ", transactions=" + transactions + "]";
	}

}
