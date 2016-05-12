/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cplx;

public interface ConnectorListener {
    public void onConnected(Connector invoker);
    public void onReceivedPacket(Connector invoker, Packet packet);
    public void onClosed(Connector invoker);
}
