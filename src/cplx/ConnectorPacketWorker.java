/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cplx;

public class ConnectorPacketWorker implements Runnable {
    private final Connector invoker;
    public ConnectorPacketWorker(Connector invoker) {
        this.invoker = invoker;
    }
    @Override
    public void run() {
        
    }
}
