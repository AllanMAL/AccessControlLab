package com.DTU;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PrintClient{
    static Printerface printer;

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        printer = (Printerface) Naming.lookup("rmi://localhost:6969/printers");
        //System.out.println("-- " + printer.echo("Hey Server"));
        print();
    }

    static void print() throws RemoteException{
        printer.print("text.pdf","lab-001");
    }

    public void queue() throws RemoteException{
        System.out.println(printer.queue());
    }

    public void topQueue(int job) throws RemoteException{
        System.out.println(printer.topQueue(job));
    }

    public void start() throws RemoteException{
        System.out.println(printer.start());

    }

    public void stop() throws RemoteException{
        System.out.println(printer.stop());

    }

    public void restart() throws RemoteException{
        System.out.println(printer.restart());

    }

    public void status() throws RemoteException{
        System.out.println(printer.status());

    }

    public void readConfig(String parameter) throws RemoteException{
        System.out.println(printer.readConfig(parameter));

    }

    public void setConfig(String parameter, String value) throws RemoteException{
        System.out.println(printer.setConfig(parameter,value));

    }
}
