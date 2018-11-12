package com.DTU;

import org.json.simple.JSONObject;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Printerface extends Remote,Serializable {
    String echo(JSONObject hashedPass, String input) throws RemoteException;
    void print(JSONObject hashedPass, String filename, String printer)throws RemoteException;   // prints file filename on the specified printer
    String queue(JSONObject hashedPass)throws RemoteException;   // lists the printer queued on the user's display in lines of the form <job number>   <file name>
    String topQueue(JSONObject hashedPass, int job)throws RemoteException;   // moves job to the top of the queued
    String start(JSONObject hashedPass)throws RemoteException;   // starts the printer server
    String stop(JSONObject hashedPass)throws RemoteException;   // stops the printer server
    String restart(JSONObject hashedPass)throws RemoteException;   // stops the printer server, clears the printer queued and starts the printer server again
    String status(JSONObject hashedPass)throws RemoteException;  // prints status of printer on the user's display
    String readConfig(JSONObject hashedPass, String parameter)throws RemoteException;   // prints the value of the parameter on the user's display
    String setConfig(JSONObject hashedPass, String parameter, String value)throws RemoteException;   // sets the parameter to value
    String verifyUser(JSONObject ident) throws RemoteException;
    boolean verify(JSONObject token) throws RemoteException;

}
