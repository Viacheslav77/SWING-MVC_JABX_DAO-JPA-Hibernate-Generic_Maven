package com.Bank;

import com.Jpa.Domain.Bank;
import com.GUI.MainFrame;;

/*
 * * Создать базу данных «Банк» с таблицами «Пользователи»,«Транзакции», «Счета» и «Курсы валют».
 *  Счет бывает 3-х видов:USD, EUR, UAH. Написать запросы для пополнения счета в нужной валюте, 
 *  перевода средств с одного счета на другой, конвертации валюты по курсу в рамках счетов одного
 *  пользователя. Написать запрос для получения суммарных средств на счету одного пользователя 
 *  в UAH (расчет по курсу).
 */

public class Main {

	public static void main(String[] args) {
		
		Bank bank = new Bank();
		
		MainFrame cf = new MainFrame(bank);
	    cf.setVisible(true);
	
	}

}
