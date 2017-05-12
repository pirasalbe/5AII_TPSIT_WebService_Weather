/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import org.w3c.dom.Document;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author pirasalbe
 */
public class WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(
			(System.in)));
        
        String citta = "London";
        
        try {
            System.out.print("Write a city: ");
            citta = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //proxy
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.18.0.1", 3128));
        
        Authenticator authenticator = new Authenticator() {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication("esterno4",
                    "Rossiesterno4".toCharArray()));
            }
        };
        Authenticator.setDefault(authenticator);
        //end of proxy
        
        Weather w = new Weather(citta,proxy);
        
        Document d = w.forecast();
        
        doSomething(d.getFirstChild());
    }
    
    public static void doSomething(Node node) {
    // do something with the current node instead of System.out
    System.out.println(node.getNodeName() + ": ");
    
    if(node.getNodeValue() != null) 
        System.out.println(node.getNodeValue() + ", ");
    
    Node currentNode;
    int i;
    for (i = 0; i < node.getAttributes().getLength(); i++) {
        currentNode = node.getAttributes().item(i);
        System.out.println("\t" + currentNode.getNodeName() + ": " + currentNode.getNodeValue());
    }

    NodeList nodeList = node.getChildNodes();
    for (i = 0; i < nodeList.getLength(); i++) {
        currentNode = nodeList.item(i);
        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
            //calls this method for all the children which is Element
            doSomething(currentNode);
        }
    }
}
}
