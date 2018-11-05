package com.DTU;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Printerface extends Remote {
    String echo(String input) throws RemoteException;
    void print(String filename, String printer);   // prints file filename on the specified printer
    String queue();   // lists the printer queue on the user's display in lines of the form <job number>   <file name>
    String topQueue(int job);   // moves job to the top of the queue
    boolean start();   // starts the printer server
    boolean stop();   // stops the printer server
    boolean restart();   // stops the printer server, clears the printer queue and starts the printer server again
    String status();  // prints status of printer on the user's display
    String readConfig(String parameter);   // prints the value of the parameter on the user's display
    String setConfig(String parameter, String value);   // sets the parameter to value
}
