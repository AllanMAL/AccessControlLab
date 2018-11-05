package com.DTU;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Printerface extends Remote {
    String echo(String input) throws RemoteException;
    void print(String filename, String printer)throws RemoteException;   // prints file filename on the specified printer
    String queue()throws RemoteException;   // lists the printer queued on the user's display in lines of the form <job number>   <file name>
    String topQueue(int job)throws RemoteException;   // moves job to the top of the queued
    String start()throws RemoteException;   // starts the printer server
    String stop()throws RemoteException;   // stops the printer server
    String restart()throws RemoteException;   // stops the printer server, clears the printer queued and starts the printer server again
    String status()throws RemoteException;  // prints status of printer on the user's display
    String readConfig(String parameter)throws RemoteException;   // prints the value of the parameter on the user's display
    String setConfig(String parameter, String value)throws RemoteException;   // sets the parameter to value
}
