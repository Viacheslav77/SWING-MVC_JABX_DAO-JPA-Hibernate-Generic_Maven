package com.Bank.finance.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "source")
public class Source {
	
	private String id;
	private String date;		
	
	private Organizations organizations = new Organizations();
	
	public String getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}
	
	@XmlAttribute
	public void setDate(String date) {
		this.date = date;
	}

	public Organizations getOrganizations() {
		return organizations;
	}

	@XmlElement
	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}


	
}
