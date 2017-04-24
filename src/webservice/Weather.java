/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Weather {
    private String citta;
    private Proxy proxy;
    
    public Weather(String citta, Proxy proxy) {
        this.citta = citta;
        this.proxy = proxy;
    }
    
    public Element forecast(){   
        Element element = null;
        String uri="api.openweathermap.org/data/2.5/weather?q=" + citta;
    
        try {

		URL url = new URL(uri);
                HttpURLConnection conn;
                
                if(proxy!=null)
                    conn = (HttpURLConnection) url.openConnection(proxy);
                else
                    conn = (HttpURLConnection) url.openConnection();
                
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
                
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(conn.getInputStream());
                element = doc.getDocumentElement();

		conn.disconnect();

	  } catch (Exception e) {

		System.out.println(e);

	  }
    
        return element;
        
    }
    
}