/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cplx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectorHandler implements ConnectorListener {
    private final ExecutorService es = Executors.newCachedThreadPool();
    void executeConnector(Connector c) {
        es.execute(new ConnectorPacketWorker(c));
    }
    
    public void end() {
        es.shutdown();
    };
    
    @Override
    public void onConnected(Connector invoker) {
        System.out.println(invoker.toString() + " connected to " + invoker.getPeer());
    }

    @Override
    public void onReceivedPacket(Connector invoker, Packet p) {
        
    }

    @Override
    public void onClosed(Connector invoker) {
        
    }
}
