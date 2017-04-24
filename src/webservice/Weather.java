/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    
    public Document forecast(){   
        Document doc = null;
        String uri="http://api.openweathermap.org/data/2.5/weather?q=" + citta + "&mode=xml&APPID=6bcbb00e4ebd8c2f0410b0537386b957";
    
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
                
                BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... ");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
                
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(conn.getInputStream());

		conn.disconnect();

	  } catch (Exception e) {

		System.out.println(e);

	  }
    
        return doc;
        
    }
    
}