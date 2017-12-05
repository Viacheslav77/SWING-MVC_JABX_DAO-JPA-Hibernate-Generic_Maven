package com.Jpa.Domain;


import javax.persistence.*;

import com.Jpa.Dao.DaoFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Transactions")
@NamedQueries({
        @NamedQuery(name = "Transaction.updateTargetAccount",
                query = "UPDATE Transaction t SET t.target = :newTarget WHERE t.target = :oldTarget")
})
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne 
    private Account source;

    @ManyToOne
    private Account target;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(columnDefinition = "DECIMAL(19,4) NOT NULL")
    private BigDecimal rate;
    @Column
    private String updateDate;
    @Column
    private String info;

    public Transaction (){
    	
    }
    
    public Transaction (Account source, Account target, BigDecimal sourceAmount , BigDecimal sourceRate) {
    	this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
        this.customer = source.getCustomer();
        this.amount = Account.checkAmount(sourceAmount);
	    this.updateDate= new SimpleDateFormat("   hh:mm    dd.MM.yy ").format( new Date(System.currentTimeMillis()));  
	    this.rate= sourceRate;
	    
	    source.subtract(amount);
    	target.add(amount.multiply(rate).setScale(Account.MAX_AMOUNT_SCALE, BigDecimal.ROUND_HALF_EVEN));
    }
    
    public Transaction (Account source, Account target, BigDecimal sourceAmount , DaoFactory bankService) {
    	this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
        this.customer = source.getCustomer();
        this.amount = Account.checkAmount(sourceAmount);
	    this.updateDate= new SimpleDateFormat("   hh:mm    dd.MM.yy ").format( new Date(System.currentTimeMillis())); 
        
        doTransaction(bankService);
 
    }
    
    private void doTransaction (DaoFactory bankService){
    	
    	List<ExchangeRate> listExchangeRate =  bankService.getGenericDao(new ExchangeRate()).getAll();
	    for(ExchangeRate exchangeRate: listExchangeRate){  		
	    	if (source.getCurrency().equals(target.getCurrency())){
	    		rate=new BigDecimal(1);      		
	    		}             	
	    	if (exchangeRate.getSource().equals(source.getCurrency())
	    									&& exchangeRate.getTarget().equals(target.getCurrency())){
	    		if (exchangeRate.getSource().getCode().equals("UAH")){
	    			rate = exchangeRate.getRate().divide(new BigDecimal(1000), BigDecimal.ROUND_HALF_EVEN); 
	    		}
	    		else{
	    			rate = exchangeRate.getRate(); 
	    		}
	    	}  
	    }
	    	 if(source.getAmount().compareTo(amount)==-1){
	    		 info="Нет средств.";
	     		}
	         else{
	         	 info="Выполнено.";
	         	 source.subtract(amount);
	         	 System.out.println(source.getCurrency().getCode()+rate + "----------------------------------");
	         	 target.add(amount.multiply(rate).setScale(Account.MAX_AMOUNT_SCALE, BigDecimal.ROUND_HALF_EVEN));
	         	}  
	    
    }

    public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public BigDecimal getAmount() {
        return amount;
    }


    public BigDecimal getRate() {
        return rate;
    }


    public Account getSource() {
        return source;
    }


    public Account getTarget() {
        return target;
    }


    public void setSource(Account source) {
        this.source = source;
    }


    public void setUser(Customer customer) {
        this.customer = customer;
    }

}
