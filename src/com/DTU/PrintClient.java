package com.DTU;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PrintClient{
    static Printerface printer;

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        printer = (Printerface) Naming.lookup("rmi://localhost:6969/printer");
        System.out.println("-- " + printer.echo("Hey Server"));
        print();
    }

    public static void print() throws RemoteException{
        printer.print("text.pdf","lab-001");
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
