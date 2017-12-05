package com.GUI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ViewTabletModel extends AbstractTableModel
{
    // Список загловков для колонок в таблице
    private List <String> headers = new ArrayList();
    
    // Здесь мы храним список , который будем отображать в таблице
    private final List<ViewTable> viewTableList;
    
    private  String[] columnNames;

    public ViewTabletModel(List<ViewTable> viewTableList) {
        this.viewTableList = viewTableList;
    }
    
    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return viewTableList.size();
    }

    @Override
    // Получить количество столбцов 
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return headers.get(col);
    }
    
    // Установить загловок колонки
    public  void setColumnName(String ...columnNames1 ) {	    	
    	columnNames=columnNames1;
    	
    	for(String s: columnNames)
    		headers.add(s);
    }   
    @Override
    // Получить объект для отображения в кокнретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    public Object getValueAt(int row, int col) {
        ViewTable viewTable = viewTableList.get(row);
        // В зависимости от номера колонки возвращаем то или иное поле контакта
        
        return viewTable.getColumn(col);
    }
}
