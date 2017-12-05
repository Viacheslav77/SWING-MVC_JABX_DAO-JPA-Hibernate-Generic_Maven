package com.Jpa.Domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Accounts")
public class Account {

    static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    static final int MAX_AMOUNT_SCALE = 2;

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal amount;


    public Account() {}


    public Account(Customer customer, Currency currency, BigDecimal startAmount) {
        this.customer = Objects.requireNonNull(customer);
        this.currency = Objects.requireNonNull(currency);
        this.amount = checkAmount(startAmount);
    }


    public long getId() {
        return id;
    }


    public Customer getCustomer() {
        return customer;
    }


    public Currency getCurrency() {
        return currency;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void add(BigDecimal value) {
        amount = amount.add(checkAmount(value));
    }


    public void subtract(BigDecimal value) {
        amount = amount.subtract(checkAmount(value));
    }


    static BigDecimal checkAmount(BigDecimal value) {
        if (value.compareTo(MIN_AMOUNT) <= 0)
            throw new IllegalArgumentException("Amount must be >= " + MIN_AMOUNT);
        if (value.scale() > MAX_AMOUNT_SCALE)
            throw new IllegalArgumentException("Scale must <= " + MAX_AMOUNT_SCALE);
        return value;
    }
    
    @Override
    public String toString() {
        return   id + "  " 
        		 +	customer + "  "
        		 +  currency + "  "
        		 +  amount; 
        		
    }
}
