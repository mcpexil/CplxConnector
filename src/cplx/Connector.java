/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cplx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Connector {
    private final Socket service;
    private final InputStream in;
    private final OutputStream out;
    private final ArrayList<ConnectorListener> listeners = new ArrayList<>();
    public Connector(ConnectorHandler invoker, String addr, int port) throws UnknownHostException, IOException, ConnectorException {
        service = new Socket(addr, port);
        if(service == null) throw new ConnectorException(); // catch-all
        this.in = service.getInputStream();
        this.out = service.getOutputStream();
        
        if(invoker != null) {
            listeners.add(invoker);
            invoker.executeConnector(this); // parent thread is a manageable ConnectorHandler
        } else {
            new Thread(new ConnectorPacketWorker(this)).start(); // parent thread is Connector invoker, possibly unmanaged
        }
        
        listeners.stream().forEach((l) -> {
            l.onConnected(this);
        });
    }
    
    public String getPeer() { return service.getInetAddress().getHostAddress(); }
    
    public boolean write(String message) {
        message = message.replace("\0", "");
        message += "\0";
        boolean result = false;
        try {
            out.write(message.getBytes());
            result = true;
        } catch (IOException ex) {
            // TODO handle
        }
        return result;
    }
    
    public void close() throws IOException {
        if(!service.isClosed()) {
            out.close();
            in.close();
            service.close();
            listeners.stream().forEach((l) -> {
                l.onClosed(this);
            });
        }
    }
}
