package com.Jpa.Hibernate;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import com.Bank.finance.xml.FinanceUkranian;
import com.GUI.MainFrame;
import com.Jpa.Dao.DaoFactory;
import com.Jpa.Dao.DataAccessLayerException;
import com.Jpa.Dao.GenericDao;
import com.Jpa.Domain.Account;
import com.Jpa.Domain.Currency;
import com.Jpa.Domain.Customer;
import com.Jpa.Domain.ExchangeRate;




public class HibernateDaoFactory implements DaoFactory {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	
	public HibernateDaoFactory (){

	}
	
	@Override
	public GenericDao getGenericDao() {
		return new HibernateGenericDao(entityManagerFactory, entityManager);
	}
	
	public GenericDao getGenericDao(Object obj) {
		return new HibernateGenericDao(entityManagerFactory, entityManager, obj);
	}
	
	@Override
	public void getPersistOfDatabase() throws DataAccessLayerException {
		entityManagerFactory = Persistence.createEntityManagerFactory("BankUpdate");
		entityManager = entityManagerFactory.createEntityManager();
		
	}
	
	@Override
	public void getPersistOfDatabaseCreate() throws DataAccessLayerException{
		entityManagerFactory = Persistence.createEntityManagerFactory("BankCreate");
		entityManager = entityManagerFactory.createEntityManager();
		
	}
	
	public void closePersistOfDatabase()  {
         
         if (entityManager != null)
             entityManager.close();
         if (entityManagerFactory!= null)
             entityManagerFactory.close();
		
	}

	@Override
	public void createDataTables() {
		for(Object object : FinanceUkranian.getInformation())
			 getGenericDao().persistGroup(object);

	}

}
