package com.Bank.finance.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

class BuildSource {
	protected static void performRequest(String urlStr, String path) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))){
            
            String s;
            while((s=br.readLine())!=null)
            	sb.append(s);
        }
        
        System.out.println("Полученный тектовый ответ   в виде :\n" + "---------------------------------------------\n" + sb+"\n");

		File file = new File(path);
        try(PrintWriter fwr = new PrintWriter(file)){
            fwr.write(sb.toString());   
        	}
    
    }
	
	public static Source getParseRequestJABX(String path){	        
		
		File file = new File(path);
		Source queryTMP = null;		
		try {           
            JAXBContext jaxbContext = JAXBContext.newInstance(Source.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // читабельное форматирование
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

             // читаем из файла
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            queryTMP = (Source) unmarshaller.unmarshal(file);
            
//            System.out.println("Проверяем запарсенный XML :\n" + "---------------------------------------------");
        
//	        marshaller.marshal(queryTMP, System.out);
	         
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
		
//		System.out.println("\nРаботаем с объектами :\n" + "---------------------------------------------");
		
		return queryTMP;
		
	}
}
