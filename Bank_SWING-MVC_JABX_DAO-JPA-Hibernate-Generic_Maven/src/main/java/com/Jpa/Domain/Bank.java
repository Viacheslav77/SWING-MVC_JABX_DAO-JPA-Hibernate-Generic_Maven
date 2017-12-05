package com.Jpa.Domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.GUI.EditDialog;
import com.GUI.ViewTable;
import com.GUI.ViewTabletModel;
import com.Jpa.Dao.DaoFactory;
import com.Jpa.Hibernate.HibernateDaoFactory;

public class Bank {
	DaoFactory bankService;
	
	public Bank(){
		bankService  = new HibernateDaoFactory();
	}
	
	 public void loadSetup(){
	    	try{
	    		bankService.getPersistOfDatabaseCreate ();
	    		
	    		} finally {
	    			bankService.closePersistOfDatabase ();
	            }
	 }
	
    public void loadInformatiom(){
    	try{
		bankService.getPersistOfDatabaseCreate ();
		bankService.createDataTables();
		
		} finally {
			bankService.closePersistOfDatabase ();
        }	
    	for(int i=0;i<10;i++)
    			gerationNewTransaction();
    }
	// Добавление клиента
    public void addCustomer(EditDialog newCustomerEditDialog) {  	

	        try{	
		        bankService.getPersistOfDatabase ();
			    bankService.getGenericDao().persistGroup
			    	(new Customer(
					    		newCustomerEditDialog.getValueEntryField(0), 
					    		newCustomerEditDialog.getValueEntryField(1), 
					    		newCustomerEditDialog.getValueEntryField(2), 
					    		newCustomerEditDialog.getValueEntryField(3)));
		        
			} finally {
				bankService.closePersistOfDatabase (); 
	        
			}
    	}
    
    public void gerationNewTransaction() {
        try{	
	        bankService.getPersistOfDatabase ();
	        List <Account> accounts =  bankService.getGenericDao(new Account()).getAll();
	        Random random = new Random();
	        int indexSource = random.nextInt(accounts.size());
	        int indexTarget;       
	        while(true){
	        	indexTarget=random.nextInt(accounts.size());    	
	        	if(indexTarget!=indexSource)
	        		break;
	        	}     
	        Account source = accounts.get(indexSource);
	        Account target = accounts.get(indexTarget);      	        
	        BigDecimal sourceAmount = new BigDecimal (random.nextInt(200));       
	        
		    bankService.getGenericDao().persistGroup(new Transaction (source, target, sourceAmount, bankService));
	        
		} finally {
			bankService.closePersistOfDatabase ();
		}      
//        loadTransactions();
	}
    
    public List<ViewTable> loadTransactions() {
    	// Обращаемся к классу для загрузки таблицы
        List<ViewTable> viewTables = new ArrayList <ViewTable>  ();
        try{	
	        bankService.getPersistOfDatabase ();
	        bankService.getGenericDao(new Transaction()).getAll().forEach(
	        		transaction -> {
						try {
							viewTables.add(new ViewTable(
												((Transaction) transaction).getDate(),
												((Transaction) transaction).getSource().getCustomer().getName(),
												((Transaction) transaction).getSource().getCurrency().toString(),
												((Transaction) transaction).getSource().getAmount().toString(),
												((Transaction) transaction).getTarget().getCustomer().getName(),
												((Transaction) transaction).getTarget().getCurrency().toString(),
												((Transaction) transaction).getTarget().getAmount().toString(),
												((Transaction) transaction).getRate().stripTrailingZeros().toString(),
												((Transaction) transaction).getAmount().toString(),
												((Transaction) transaction).getInfo()
												));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		} finally {
			bankService.closePersistOfDatabase ();
		}		
        return viewTables;
    }
    
    public List<ViewTable> loadCustomers() {
    	// Обращаемся к классу для загрузки таблицы
        List<ViewTable> viewTables = new ArrayList <ViewTable>  ();
        try{	
	        bankService.getPersistOfDatabase ();
	        bankService.getGenericDao(new Customer()).getAll().forEach(
	        		customer -> {
						try {
							viewTables.add(new ViewTable(								
												((Customer) customer).getName(),
												((Customer) customer).getFirstName(),
												((Customer) customer).getPhone(),
												((Customer) customer).getPosition()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		} finally {
			bankService.closePersistOfDatabase ();
		}
		return viewTables;	
    }
    public List<ViewTable> loadAccounts() {
        List<ViewTable> viewTable = new ArrayList <ViewTable>  ();
        try{	
	        bankService.getPersistOfDatabase ();
	        bankService.getGenericDao(new Account()).getAll().forEach(
	        		account -> {
						try {
							viewTable.add(new ViewTable(   									
												((Account) account).getCustomer().getName(),
												((Account) account).getCustomer().getFirstName(),
												((Account) account).getCurrency().getCode(),
												((Account) account).getAmount().toString()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		} finally {
			bankService.closePersistOfDatabase ();
		}
		return viewTable;		
    }
    public  List<ViewTable> loadBankCurrency() { 
        List<ViewTable> viewTable = new ArrayList <ViewTable>  ();
        try{	
	        bankService.getPersistOfDatabase ();
	        bankService.getGenericDao(new BankCurrency()).getAll().forEach(
	        		bankCurrency -> viewTable.add(new ViewTable(
	        							((BankCurrency) bankCurrency).getUpdateDate(),
	        							((BankCurrency) bankCurrency).getBankorganization().getTitle(),
    									((BankCurrency) bankCurrency).getCurrencyCode(),
    									((BankCurrency) bankCurrency).getSale().stripTrailingZeros().toString(),
    									((BankCurrency) bankCurrency).getPurchase().stripTrailingZeros().toString())));
		} finally {
			bankService.closePersistOfDatabase ();
		}
		return viewTable;		
    }
    public  List<ViewTable> loadBanks() {
        // Обращаемся к классу для загрузки списка контактов
        List<ViewTable> viewTable = new ArrayList <ViewTable>  ();
        try{	
	        bankService.getPersistOfDatabase ();
	        bankService.getGenericDao(new BankOrganization()).getAll().forEach(
	        		bankOrganization -> viewTable.add(new ViewTable(
    									((BankOrganization) bankOrganization).getTitle(),									
    									((BankOrganization) bankOrganization).getPhone(),
    									((BankOrganization) bankOrganization).getAddress()
    									)));
		} finally {
			bankService.closePersistOfDatabase ();
		}
		return viewTable;		
    }
    public List<ViewTable> loadExchangeRate() {
    	// Обращаемся к классу для загрузки таблицы
        List<ViewTable> viewTables = new ArrayList <ViewTable>  ();
        try{	
	        bankService.getPersistOfDatabase ();
	        bankService.getGenericDao(new ExchangeRate()).getAll().forEach(
	        		exchangeRate -> viewTables.add(
	        				new ViewTable(					
										((ExchangeRate) exchangeRate).getUpdateDate(),
										((ExchangeRate) exchangeRate).getSource().getCode(),
										((ExchangeRate) exchangeRate).getTarget().getCode(),
										((ExchangeRate) exchangeRate).getRate().stripTrailingZeros().toString(),
	        							((ExchangeRate) exchangeRate).getByBank()
	        		)));
		} finally {
			bankService.closePersistOfDatabase ();
		}
		return viewTables;		
    }
    public void deleteCustomer(int index) {
        Customer customer = new Customer();        	
    	try{	
	        bankService.getPersistOfDatabase ();	
	        bankService.getGenericDao(new Customer()).delete(index);	 
    	} finally {
			bankService.closePersistOfDatabase ();        
		}
    }
    public Customer getCustomerId(int index){
    	Customer customer = new Customer();
    	try{	
        bankService.getPersistOfDatabase ();	    
        customer = (Customer) bankService.getGenericDao(customer).getObjectByPK(index); 
    	} finally {
			bankService.closePersistOfDatabase ();        
		}
		return customer;
        
    }
    public void updateCustomer(Customer customer){	
    	try{	
            bankService.getPersistOfDatabase ();	    
            bankService.getGenericDao(customer).persistGroup(customer);	
        	} finally {
    			bankService.closePersistOfDatabase ();        
    		}
    }
}
