package com.GUI;

import java.util.ArrayList;
import java.util.List;

import com.Jpa.Dao.DaoFactory;
import com.Jpa.Domain.ExchangeRate;

/**
 *   Класс для хранения выводимых данных
 */
public class ViewTable {
//    private Long Id;

    private String[] strings;
    
    public ViewTable() {
    } 
	public ViewTable(String ...strings ) {
		this.strings=strings;

	}
	public String getColumn(int index) {
		return strings[index];
	}
	
	public String [] getAllColumn() {
		return strings;
	}

}
