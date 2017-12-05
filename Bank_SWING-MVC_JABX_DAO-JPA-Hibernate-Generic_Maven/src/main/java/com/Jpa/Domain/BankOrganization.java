package com.Jpa.Domain;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "Bankorganizations")
public class BankOrganization {

	static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    static final int MAX_AMOUNT_SCALE = 2;
    
    @Id
    @GeneratedValue
	private long id;
    
    @Column
    private String address;
    
    @Column
    private String phone;
    
    @Column (nullable = false)
	private String title;
	
    @OneToMany   (mappedBy = "bankorganization", cascade = {REFRESH, REMOVE})
    private List<BankCurrency> bankcurrencies = new ArrayList<BankCurrency>();
    
    
	public  BankOrganization(){};
    
    public BankOrganization(String title, String phone, String address) {
		this.title = Objects.requireNonNull(title);
		this.phone = phone;
		this.address = address;
//		this.bankCurrencies = bankCurrencies;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<BankCurrency> getBankCurrencies() {
		return bankcurrencies;
	}

	public void setBankCurrencies(List<BankCurrency> bankCurrencies) {
		this.bankcurrencies = bankCurrencies;
	}

	public static BigDecimal getMinAmount() {
		return MIN_AMOUNT;
	}

	public static int getMaxAmountScale() {
		return MAX_AMOUNT_SCALE;
	}

	@Override
	public String toString() {
		return "BankOrganization [id=" + id + ", address=" + address + ", phone=" + phone + ", title=" + title
				+ ", bankcurrencies=" + bankcurrencies + "]";
	}



}
