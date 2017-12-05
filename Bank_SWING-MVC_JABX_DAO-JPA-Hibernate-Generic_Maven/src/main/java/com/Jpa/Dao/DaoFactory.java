package com.Jpa.Dao;


	/** Фабрика объектов для работы с базой данных */
	public interface DaoFactory {

	    /** Возвращает подключение к базе данных */
	    public void getPersistOfDatabase() throws DataAccessLayerException;
	    
	    /** Закрывает подключение к базе данных */
	    public void closePersistOfDatabase();
	    
	    /** Создаёт заново таблицы и возвращает подключение к базе данных */
	    public void getPersistOfDatabaseCreate() throws DataAccessLayerException;
	    
	    /** Удаляет и создаёт таблицы базы данных*/
	    public void createDataTables();

	    /** Возвращает объект для управления персистентным состоянием объекта */
	    public GenericDao getGenericDao();
	    
	    /** Возвращает объект для управления персистентным состоянием объекта */
	    public GenericDao getGenericDao(Object obj);
	    
	    

	}
	
//	public interface MenuDaoInterface<T, Id extends Serializable> {
//
//		public void persist(T entity);
//		
//		public void update(T entity);
//		
//		public T findById(Id id);
//		
//		public void delete(T entity);
//		
//		public List<T> findAll();
//		
//		public void deleteAll();

