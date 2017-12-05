package com.Jpa.Dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import com.Jpa.Domain.Account;
import com.Jpa.Domain.Customer;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface GenericDao <T, PK extends Serializable> {

    /** Создает одну или несколько новых объектов или сохраняет их состояние  */
    public void persistGroup(T... objs);

    /** Создает новую запись, соответствующую объекту object */
    public T persist(T object)  throws SQLException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T getObjectByPK(long id);

    /** Сохраняет состояние объекта group в базе данных */
    public void update(T object) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(T obj);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll();

	public List<Object> getSelect(String were);

	void delete(Customer customer);

	void delete(long id);

	
}