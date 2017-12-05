package com.Bank.finance.xml;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="c")
 class CurrencyFinance {
	private String id;
	private BigDecimal br;
	private BigDecimal ar;
	
	
	public String getId() {
		return id;
	}
	
	@XmlAttribute(name = "id")
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getBr() {
		return br;
	}
	
	@XmlAttribute(name = "br")
	public void setBr(BigDecimal br) {
		this.br = br;
	}
	public BigDecimal getAr() {
		return ar;
	}
	
	@XmlAttribute(name = "ar")
	public void setAr(BigDecimal ar) {
		this.ar = ar;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", br=" + br + ", ar=" + ar + "]";
	}
	
	
}
