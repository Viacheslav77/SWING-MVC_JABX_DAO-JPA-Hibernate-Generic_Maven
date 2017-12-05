package com.GUI;


import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.Jpa.Domain.Customer;

public class EditDialog extends JDialog implements ActionListener
{
    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;

    // Поле для ввода Имени
    private final JTextPane txtFirstName = new JTextPane();
    // Поле для ввода Фамилии
    private final JTextPane txtLastName = new JTextPane();
    // Поле для ввода Телефона
    private final JTextPane txtPhone = new JTextPane();
    // Поле для ввода должности
    private final JTextPane txtPosition = new JTextPane();
    
    private  List <JTextPane> entryField = new ArrayList<JTextPane>() ;
    
//    private String[] namesEntryField;

    // Поле для хранения ID контакта, если мы собираемся редактировать
    // Если это новый контакт - cjntactId == null
    private Long contactId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;

    public EditDialog() {
//        this(null);
    }
    


    public EditDialog(ViewTable viewTable, String[] namesEntryField) {
        
    	// Убираем layout - будем использовать абсолютные координаты
        setLayout(null); 

        // Выстраиваем метки и поля для ввода
        buildFields(namesEntryField);
        // Если нам передали контакт - заполняем поля формы
        initFields(viewTable);
        // выстариваем кнопки
        buildButtons();

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 450, 200);
        // Делаем форму видимой
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private void buildFields(String[] namesEntryField) {
        int i=0;
    	for(String nameField: namesEntryField){
    		
    		JTextPane jTextPane=new JTextPane();
    		entryField.add(jTextPane);
    		// Набор метки и поля для Имени
            JLabel lblFirstName = new JLabel(nameField);
            // Выравнивание текста с правой стороны
            lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
            // Выставляем координаты метки
            lblFirstName.setBounds(new Rectangle(PAD, i * H_B + PAD, W_L, H_B));
            // Кладем метку на форму
            add(lblFirstName);
            // Выставляем координаты поля
            jTextPane.setBounds(new Rectangle(W_L + 2 * PAD, i * H_B + PAD, W_T, H_B));
            // Делаем "бордюр" для поля
            jTextPane.setBorder(BorderFactory.createEtchedBorder());
            // Кладем поле на форму
            add(entryField.get(i++));
    		}     
    	}
    
    // Если нам пепередали контакт - заполняем поля из контакта
    private void initFields(ViewTable viewTable) {
        if (viewTable != null) {

        	for(int i=0 ; i< viewTable.getAllColumn().length; i++){
        		entryField.get(i).setText(viewTable.getColumn(i));
        	} 	
        	
        }
    }

    // Размещаем кнопки на форме
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    public List<JTextPane> getEntryField() {
		return entryField;
	}
    
    public String getValueEntryField(int index) {
  		return entryField.get(index).getText();
  	}

	public void setEntryField(List<JTextPane> entryField) {
		this.entryField = entryField;
	}


	@Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // Если нажали кнопку SAVE (сохранить изменения) - запоминаем этой
        save = SAVE.equals(action);
        // Закрываем форму
        setVisible(false);
    }

    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }

    // Создаем контакт из заполенных полей, который можно будет записать
    public ViewTable getContact() {
        ViewTable viewTable = new ViewTable(txtFirstName.getText(),
                txtLastName.getText(), txtPhone.getText(), txtPosition.getText());
        return viewTable;
    }



	public JTextPane getTxtFirstName() {
		return txtFirstName;
	}



	public JTextPane getTxtLastName() {
		return txtLastName;
	}



	public JTextPane getTxtPhone() {
		return txtPhone;
	}



	public JTextPane getTxtEmail() {
		return txtPosition;
	}
    
    
}
