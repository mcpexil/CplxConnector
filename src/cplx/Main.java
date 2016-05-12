/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cplx;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectorHandler ch = new ConnectorHandler();
        Connector c = new Connector(ch, "www.google.co.uk", 80);
        c.close();
        ch.end();
    }
}
