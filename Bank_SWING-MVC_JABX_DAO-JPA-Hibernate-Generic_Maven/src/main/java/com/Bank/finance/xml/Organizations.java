package com.Bank.finance.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "organizations")
class Organizations {
		private List<Organization> organization = new ArrayList<Organization>();

		public List<Organization> getOrganization() {
			return organization;
		}

		@XmlElement
		public void setOrganization(List<Organization> organization) {
			this.organization = organization;
		}
	

	
}
