package com.DTU;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PrintClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Printerface print = (Printerface) Naming.lookup("rmi://localhost:6969/print");
        System.out.println("-- " + print.Echo("Hey Server"));
    }

    public void print() throws RemoteException{

    }
    
}
