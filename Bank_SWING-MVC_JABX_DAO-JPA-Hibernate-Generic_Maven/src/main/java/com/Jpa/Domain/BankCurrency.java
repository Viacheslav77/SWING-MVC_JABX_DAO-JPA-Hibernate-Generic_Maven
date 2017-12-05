package com.Jpa.Domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bankcurrencies")
public class BankCurrency {
	
	@Id
    @GeneratedValue
	private long id;
	
	@ManyToOne
	private BankOrganization bankorganization;
	
	@Column
	private String currencyCode;
	@Column
	private BigDecimal purchase;
	@Column
	private BigDecimal sale;
	@Column
	private String updateDate;
	
	public BankCurrency(){
		
	}

	public BankCurrency(String updateDate, BankOrganization bankorganization, String currencyCode, BigDecimal purchase, 
			BigDecimal sale) {

		this.bankorganization = bankorganization;
		this.currencyCode = currencyCode;
		this.purchase = purchase;
		this.sale = sale;
		this.updateDate = updateDate;
	}

	public BankCurrency(BankOrganization bankOrganization, String currencyCode, BigDecimal purchase, BigDecimal sale) {
		this.bankorganization=bankOrganization;
		this.currencyCode = currencyCode;
		this.purchase = purchase;
		this.sale = sale;
	}



	
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BankOrganization getBankorganization() {
		return bankorganization;
	}

	public void setBankorganization(BankOrganization bankorganization) {
		this.bankorganization = bankorganization;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getPurchase() {
		return purchase;
	}

	public void setPurchase(BigDecimal purchase) {
		this.purchase = purchase;
	}

	public BigDecimal getSale() {
		return sale;
	}

	public void setSale(BigDecimal sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "BankCurrency [id=" + id + ", bankorganization=" + bankorganization + ", currencyCode=" + currencyCode
				+ ", purchase=" + purchase + ", sale=" + sale + "]";
	}
	
}
