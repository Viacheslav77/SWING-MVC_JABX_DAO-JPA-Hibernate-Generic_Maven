 package com.Bank.finance.xml;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.Jpa.Domain.Account;
import com.Jpa.Domain.BankCurrency;
import com.Jpa.Domain.BankOrganization;
import com.Jpa.Domain.Currency;
import com.Jpa.Domain.Customer;
import com.Jpa.Domain.ExchangeRate;

public class FinanceUkranian {
    
    public static List<Object> getInformation() {
    	List <Object> objectList = new ArrayList <Object> ();
    	Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("   hh:mm    dd.MM.yy ");     
        String updateDate = format.format(d); //source.getDate();
    	String request = "http://resources.finance.ua/ru/public/currency-cash.xml";
        String path = "D:\\1\\finance.xml";     
        try {
			BuildSource.performRequest(request, path);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        Source source = BuildSource.getParseRequestJABX(path);  
    	
        objectList.addAll(getbanksFromFinanceUa (updateDate, source));
        
    	Currency uah = new Currency("UAH", "Ukrainian");
        Currency usd = new Currency("USD", "USA");
        Currency eur = new Currency("EUR", "Euro");				 
        objectList.add(uah);
        objectList.add(usd);
        objectList.add(eur);
        
        objectList.addAll(getCurrencyFromFinanceUa (updateDate, source,uah,usd,eur));          
        
        List <Customer> listCustomer = new ArrayList<Customer>();
        for (int i=0; i < getCustomers().length;){
        	Customer customer = new Customer(getCustomers()[i++], 
											 getCustomers()[i++],
											 getCustomers()[i++],
											 getCustomers()[i++]);
        	listCustomer.add(customer);
        	objectList.add(customer);
        }
      
      Random ranrom = new Random();
      Currency [] usd_uah_eur = {usd,uah,eur};
      for(int i=0; i<listCustomer.size()*2;i++){
    	  objectList.add(new Account(listCustomer.get(ranrom.nextInt(listCustomer.size()-1)), 
    			  						usd_uah_eur[ranrom.nextInt(3)], 
    	    							new BigDecimal(ranrom.nextInt(1000))));
      }
        
		return objectList;
    }
    private static Collection<? extends Object> getCurrencyFromFinanceUa(String updateDate, Source source, Currency uah, Currency usd, Currency eur) {
    	List <Object> objectList = new ArrayList <Object> ();
   
        Organization bankOrganization = source.getOrganizations().getOrganization().get(0);
        String byBank = bankOrganization.getTitle().getValue();      
        
        CurrencyFinance rateUsd = bankOrganization.getCurrencies().getCurrency().get(1);
        CurrencyFinance rateEur = bankOrganization.getCurrencies().getCurrency().get(0);
       
        objectList.add(new ExchangeRate(uah, usd, rateUsd.getBr(),byBank,updateDate));
        objectList.add(new ExchangeRate(usd, uah, rateUsd.getAr(),byBank,updateDate));
        objectList.add(new ExchangeRate(uah, eur, rateEur.getBr(),byBank,updateDate));
        objectList.add(new ExchangeRate(eur, uah, rateEur.getAr(),byBank,updateDate));
        objectList.add(new ExchangeRate(eur, usd,rateUsd.getBr().divide(rateEur.getBr(), BigDecimal.ROUND_HALF_EVEN),byBank,updateDate));
        objectList.add(new ExchangeRate(usd, eur,rateEur.getAr().divide(rateUsd.getAr(), BigDecimal.ROUND_HALF_EVEN),byBank,updateDate));
		
        return objectList;
	}
	private static Collection<? extends Object> getbanksFromFinanceUa(String updateDate, Source source) {
    	List <Object> objectList = new ArrayList <Object> ();
    	
        for (Organization organization: source.getOrganizations().getOrganization()){     	    	
        	BankOrganization bankOrganization = 
		    			new BankOrganization(organization.getTitle().getValue(), 
									         organization.getPhone().getValue(), 
									         organization.getAddress().getValue());
        	objectList.add(bankOrganization);
     
        	for (CurrencyFinance currencyFinance: organization.getCurrencies().getCurrency()){
        		objectList.add(	
        			new BankCurrency (updateDate, bankOrganization, currencyFinance.getId(), currencyFinance.getAr(), currencyFinance.getBr()));
        	 	}
        	} 
		return objectList;
	}
	public static String[] getCustomers(){
    	String [] customersDate = 
    		{"Ковалэнко","Семен" , "+38044-537-90-63","Начальник отдела", 
    		  "Бондарэнко ","Оксана","+38044-526-72-75", "Заместитель начальника отдела" ,
    		  "Евэнко ","Игорь", "+38044-537-91-09", "Заместитель начальника отдела",  		  
    		  "Злэнко"," Сергей","+38044-567-32-94","Государственный инспектор",
    		  "Лучэнко"," Надежда","+38044-526-54-77","Старший научный сотрудник",
    		  "Ткачэнко"," Петро","+38044-526-89-74","Полицейский",
    		  "Петрэнко"," Василий","+38044-526-82-55","Старший государственный инспектор",
    		  "Павлэнко ","Лена","+38044-526-72-75","Старший куда пошлют",
    		  "Майстрэнко "," Павел","+38044-537-91-09","Инспектор полиции"};
		return customersDate;

    }
    
	

}