package com.GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.Jpa.Domain.*;


public class MainFrame extends JFrame implements ActionListener {
	
	private static final String BANKS = "BANKS";
	private static final String BANKCURRENCIES = "BANKCURRENCY";
	private static final String CUSTOMERS = "CUSTOMERS";
	private static final String ACCOUNTS = "ACCOUNTS";
	private static final String TRANSACTIONS = "TRANSACTIONS";
	private static final String NEW_TRANSACTION = "NEW_TRANSACTION";
	private static final String EXCHANGERATE = "EXCHANGERATE";
	private static final String LOAD = "LOAD";
	private static final String CLEAR = "CLEAR";	
	private static final String ADDCUSTOMER = "ADDCUSTOMER";
    private static final String EDITCUSTOMER = "EDITCUSTOMER";	
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static Bank bank;
    private final JTable contactTable = new JTable();

    // В конструкторе мы создаем нужные элементы
    public MainFrame(Bank bank) {
         super(" <<<  MyTestBank >>> Received  information from  http://resources.finance.ua/ru/public/currency-cash.xml");
         this.bank=bank;
       
    	// Выставляем у таблицы свойство, которое позволяет выделить  ТОЛЬКО одну строку в таблице
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        // Используем layout GridBagLayout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(2, 5, 3, 5);
        

        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gridbag);
        
        JPanel btnPanel1 = new JPanel();
        btnPanel1.setLayout(gridbag);
        
        JPanel btnPanel2 = new JPanel();
        btnPanel2.setLayout(gridbag);
        // Создаем кнопки и укзаываем их загловок и ActionCommand
      
        
        btnPanel.add(createButton(gridbag, gbc, "Курсы валют в <<<MyTestBank>>> ", EXCHANGERATE));     
        btnPanel.add(createButton(gridbag, gbc, "Счета <<< MyTestBank>>> ", ACCOUNTS));    
        btnPanel.add(createButton(gridbag, gbc, "Транзакции <<<MyTestBank>>> ", TRANSACTIONS));
        btnPanel.add(createButton(gridbag, gbc, "Новая Транзакция by Random", NEW_TRANSACTION));
        
        btnPanel1.add(createButton(gridbag, gbc, "Клиенты <<<MyTestBank>>> ", CUSTOMERS));
        btnPanel1.add(createButton(gridbag, gbc, "Добавить клиента банка", ADDCUSTOMER));
        btnPanel1.add(createButton(gridbag, gbc, "Редактировать данные клиента ....", EDITCUSTOMER));
        btnPanel1.add(createButton(gridbag, gbc, "Удалить данные клиента", DELETE));     
        
        btnPanel2.add(createButton(gridbag, gbc, "Очистить <<<MyTestBank>>>", CLEAR));
        btnPanel2.add(createButton(gridbag, gbc, "<<<Обновить курсы и таблицы>>>", LOAD));
        btnPanel2.add(createButton(gridbag, gbc, "Справочник по банкам", BANKS));
        btnPanel2.add(createButton(gridbag, gbc, "Курсы валют в банках", BANKCURRENCIES));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        left.add(btnPanel1, BorderLayout.WEST );
        left.add(btnPanel2, BorderLayout.SOUTH);
       
//        left.setBorder(new MatteBorder(5,5,50,5, Color.white));
        left.setBorder(new TitledBorder((new BevelBorder(BevelBorder.LOWERED)),"Menu <<< MyTestBank>>>",5,5));
        // Кладем панель для левой колонки на форму слева - WEST
        add(new JScrollPane(left), BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(contactTable), BorderLayout.CENTER);
        
        // выставляем координаты формы
        setBounds(100, 100, 1100, 550);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
     
//        loadSetup();
        loadInformatiom();
//        loadCustomers();
        
    }
    private void loadSetup(){ 
        JOptionPane.showMessageDialog(this, 
        		"        Уничтожаем данные в таблицах банка (чтобы убедиться нажимаем на кнопки таблиц).\n"
        		+ "Для загрузки тестовых данных и актуальных курсов и данных украинских банков с finance.ua\n"
        		+ "                                      нажмите кнопку \"Обновить курсы валют\"...");
        bank.loadSetup();
        ViewTabletModel cm = new ViewTabletModel(new ArrayList <ViewTable> ());
        cm.setColumnName("Таблицы-пустые. Для загрузки данных и актуальных курсов с finance.ua нажмите кнопку \"Обновить курсы валют\"...");
        contactTable.setModel(cm);
    }
    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую 
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
     // Банковские курсы валют
        case BANKCURRENCIES:
        	loadBankCurrency();
            break;
            // Укр. Банки
        case BANKS:
        	loadBanks();
            break;
            // "Счета"
        case ACCOUNTS:
        	loadAccounts();
            break;
            // Клиенты
        case CUSTOMERS:
        	loadCustomers();
            break; 
            // loadTransactions() 
        case TRANSACTIONS:
        	loadTransactions();
            break;
        case NEW_TRANSACTION:
        	gerationNewTransaction();
            break;
        case EXCHANGERATE:
        	loadExchangeRate();
            break;   
            
        // Перегрузка данных
            case LOAD:
            	loadInformatiom();
                break; 
            case CLEAR:
            	loadSetup();
                break;              
            // Добавление записи
            case ADDCUSTOMER:
                addCustomer(null);
                break;
            // Исправление записи
            case EDITCUSTOMER:
                editCustomer();
                break;
            // Удаление записи
            case DELETE:
                deleteCustomer();
                break;
        }
    }
    
    
    private void gerationNewTransaction() {
    	bank.gerationNewTransaction();
    	loadTransactions();
	}
	// Добавление клиента
    private void addCustomer(ViewTable viewTable) {  	
    	// Создаем диалог для ввода данных        
    	String [] fieldTitle ={"Фамилия","Имя","Телефон","Должность"}; 	
    	EditDialog newCustomerEditDialog = new EditDialog(viewTable,fieldTitle);
        
    	if(newCustomerEditDialog.isSave()){
	        // Обрабатываем закрытие диалога
	       bank.addCustomer(newCustomerEditDialog);
        loadCustomers();
        }
    }
    // Редактирование данных клиента
    private void editCustomer() {     
        ViewTable viewTable = null;
        // если строка выделена - можно ее редактировать
        if (contactTable.getSelectedRow() != -1){   	
        	
        	bank.updateCustomer(contactTable.getSelectedRow());	
        	loadCustomers();

        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить в таблице клиентов для редактирования");
            loadCustomers();
        }
    }
    // Удаление данных клиента
    private void deleteCustomer() { 
        // если строка выделена - можно ее редактировать
        if (contactTable.getSelectedRow() != -1) {        	
        	bank.deleteCustomer(contactTable.getSelectedRow());    	
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить в таблице клиентов для редактирования");          
        }
        loadCustomers();
    }	
    private void loadInformatiom(){
    	bank.loadInformatiom();
    	loadBankCurrency();		
    }
    private void loadExchangeRate() {
        // Создаем модель, которой передаем полученный список
        ViewTabletModel cm = new ViewTabletModel(bank.loadExchangeRate());
        cm.setColumnName("Дата обновления", "Валюта продажи", "Валюта покупки", "Курс обмена", "by Bank");
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }
    private void loadTransactions() {
        // Создаем модель, которой передаем полученный список от банка
        ViewTabletModel cm = new ViewTabletModel(bank.loadTransactions());
        cm.setColumnName("Дата","Плательщик", "Валюта", "Остаток", "Получатель", "Валюта", "Остаток", "Курс","Сумма","Статус");
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }
    private void loadCustomers() {
        // Создаем модель, которой передаем полученный список
        ViewTabletModel cm = new ViewTabletModel(bank.loadCustomers());
        cm.setColumnName("Фамилия","Имя",  "Телефон", "Должность");
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }
	private void loadAccounts() {
        // Создаем модель, которой передаем полученный список
        ViewTabletModel cm = new ViewTabletModel(bank.loadAccounts());
        cm.setColumnName("Фамилия", "Имя", "Код валюты", "Остаток");
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }

// Загрузить список курсов валют
    private void loadBankCurrency() {
        // Создаем модель, которой передаем полученный список
        ViewTabletModel cm = new ViewTabletModel(bank.loadBankCurrency());
        cm.setColumnName("Дата обновления","Название банка", "Код валюты", "Покупка гривны", "Продажа гривны");
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }
 // Загрухить список банков
    private void loadBanks() {
        // Создаем модель, которой передаем полученный список
        ViewTabletModel cm = new ViewTabletModel(bank.loadBanks());
        cm.setColumnName("Название Банка", "Телефон", "Адрес");
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }
}
