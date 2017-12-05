package com.Jpa.Hibernate;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.Jpa.Dao.GenericDao;
import com.Jpa.Domain.Customer;

public class HibernateGenericDao <T> implements  GenericDao {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Object obj;
	
	public HibernateGenericDao (EntityManagerFactory entityManagerFactory, EntityManager entityManager){
		this.entityManagerFactory=entityManagerFactory;
		this.entityManager=entityManager;
	}
	
	public HibernateGenericDao (EntityManagerFactory entityManagerFactory, EntityManager entityManager, Object obj){
		this.entityManagerFactory=entityManagerFactory;
		this.entityManager=entityManager;
		this.obj=obj;
	}
	
	@Override
	public void persistGroup (Object... objs) {
	        performDatabaseTransaction(() -> {
	            for (Object o : objs)
	                entityManager.persist(o);
	        });
	    }

     private  void performDatabaseTransaction(Runnable action) {
        entityManager.getTransaction().begin();
        try {
            action.run();
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
	 }
	
    @Override
	public T getObjectByPK(long key){
		return (T) entityManager.find(obj.getClass(), key+1);
	}
     
    @Override
	public void update(Object object) throws SQLException {
		
    	Customer c = (Customer)object;
    	c.setName("sdasfas");
    	
    	entityManager.merge(c);
	}
	
	@Override
	public void delete(long id) {
		
		Customer customer = (Customer) getObjectByPK(id);  
		   
		performDatabaseTransaction(() -> {
             customer.getTransactions().forEach(transaction -> {
                 transaction.setSource(null);
                 transaction.setUser(null);
             });
             Query targetUpdater = entityManager.createNamedQuery("Transaction.updateTargetAccount");
             customer.getAccounts().forEach(account -> {
                 targetUpdater.setParameter("newTarget", null);
                 targetUpdater.setParameter("oldTarget", account);
                 targetUpdater.executeUpdate();
             });

             entityManager.remove(customer);
         });
     }
		
	@Override
	public List<T> getAll() { 
         Query query = entityManager.createQuery(
        		 "SELECT a FROM "+ obj.getClass().getName()+" a", obj.getClass()); 

     return (List<T>) query.getResultList();	
	}
	
	@Override
	public List<T> getSelect(String where) { 
         Query query = entityManager.createQuery(
        		 "SELECT a FROM "+ obj.getClass().getName()+" a  where customer_id="+ where, obj.getClass());
 
     return  (List<T>) query.getResultList();		
	}

	@Override
	public Object persist(Object object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		
	}

}
