/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

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
        
        
    }
    
}
