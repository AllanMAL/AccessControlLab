package com.DTU;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintClient{
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Printerface print = (Printerface) Naming.lookup("rmi://localhost:6969/print");
        System.out.println("-- " + print.Echo("Hey Server"));
    }

    public void print(String filename, String printer) throws RemoteException{

    }

    public void queue() throws RemoteException{

    }

    public void topQueue(int job) throws RemoteException{

    }

    public void start() throws RemoteException{

    }

    public void stop() throws RemoteException{

    }

    public void restart() throws RemoteException{

    }

    public void status() throws RemoteException{

    }

    public void readConfig(String parameter) throws RemoteException{

    }

    public void setConfig(String parameter, String value) throws RemoteException{

    }
}
