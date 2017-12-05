package com.Jpa.Domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Exchange_Rates")
@NamedQueries({
        @NamedQuery(name = "ExchangeRate.findByCurrencies", query =
                "SELECT e FROM ExchangeRate e WHERE e.source = :source AND e.target = :target")
})
public class ExchangeRate {

//    static final BigDecimal DEFAULT_RATE = new BigDecimal("1.0000");
//    static final BigDecimal MIN_RATE = new BigDecimal("0.0001");

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Currency source;

    @OneToOne
    private Currency target;

    @Column(columnDefinition = "DECIMAL(19,4) NOT NULL")
    private BigDecimal rate; 
    @Column
    private String byBank;  
    @Column
    private String updateDate;

    public ExchangeRate() {
    	
    }

    public ExchangeRate(Currency source, Currency target, BigDecimal rate) {
        this.source = source ; //Objects.requireNonNull(source);
        this.target = target ; //Objects.requireNonNull(target);
        this.rate = rate; //checkRate(rate);
    }

    public ExchangeRate(Currency source, Currency target, BigDecimal rate, String byBank, String updateDate) {

		this.source = source;
		this.target = target;
		this.rate = rate;
		this.byBank = byBank;
		this.updateDate = updateDate;
	}


	public String getByBank() {
		return byBank;
	}


	public void setByBank(String byBank) {
		this.byBank = byBank;
	}


	public String getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	public BigDecimal getRate() {
        return rate;
    }


//    static BigDecimal checkRate(BigDecimal rate) {
//        if (rate.compareTo(MIN_RATE) < 0)
//            throw new IllegalArgumentException("Rate must be >= " + MIN_RATE);
//        if (rate.scale() > 4)
//            throw new IllegalArgumentException("Scale must <= 4");
//        return rate;
//    }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Currency getSource() {
		return source;
	}


	public void setSource(Currency source) {
		this.source = source;
	}


	public Currency getTarget() {
		return target;
	}


	public void setTarget(Currency target) {
		this.target = target;
	}


//	public static BigDecimal getDefaultRate() {
//		return DEFAULT_RATE;
//	}
//
//
//	public static BigDecimal getMinRate() {
//		return MIN_RATE;
//	}


	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	@Override
	public String toString() {
		return "ExchangeRate [id=" + id + ", source=" + source + ", target=" + target + ", rate=" + rate + "]";
	}
    
    

}
