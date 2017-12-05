package com.Bank.finance.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class Address {
	private String value;

	public String getValue() {
		return value;
	}
	@XmlAttribute
	public void setValue(String value) {
		this.value = value;
	}
}
