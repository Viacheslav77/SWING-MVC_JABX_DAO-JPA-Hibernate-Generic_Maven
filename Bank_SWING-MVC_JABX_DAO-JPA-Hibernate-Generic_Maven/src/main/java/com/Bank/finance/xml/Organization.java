package com.Bank.finance.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

class Organization {
	private String id;
	private String oldid;
	private String org_type;
	
	private Title title;
	private City city;
	private Phone phone;
	private Address address;
	
	private Currencies currencies = new Currencies();

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getOldid() {
		return oldid;
	}

	@XmlAttribute
	public void setOldid(String oldid) {
		this.oldid = oldid;
	}

	public String getOrg_type() {
		return org_type;
	}

	@XmlAttribute
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}

	public Title getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(Title title) {
		this.title = title;
	}

	public City getCity() {
		return city;
	}

	@XmlElement
	public void setCity(City city) {
		this.city = city;
	}

	public Phone getPhone() {
		return phone;
	}

	@XmlElement
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(Address address) {
		this.address = address;
	}

	public Currencies getCurrencies() {
		return currencies;
	}

	@XmlElement
	public void setCurrencies(Currencies currencies) {
		this.currencies = currencies;
	}

	@Override
	public String toString() {
		return "Organization ["+ title.getValue() 
		 + ", phone=" + phone.getValue() 
		+ ", address=" + address.getValue() + ", currencies=" + currencies + "]";

		
	}
	

}

