package com.Bank.finance.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="currencies")
class Currencies {
	private List<CurrencyFinance> currencyFinance = new ArrayList<CurrencyFinance>();

	public List<CurrencyFinance> getCurrency() {
		return currencyFinance;
	}
	
	@XmlElement (name="c")
	public void setCurrency(List<CurrencyFinance> currencyFinance) {
		this.currencyFinance = currencyFinance;
	}

	@Override
	public String toString() {
		return "Currencies [currency=" + currencyFinance + "]";
	}
	
}
