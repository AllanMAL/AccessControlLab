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
        printer.login("Dia","Beetus");
        print();
        queue();
        topQueue(4);
        start();
        stop();
        restart();


    }

    static void print() throws RemoteException{
        printer.print("text.pdf","lab-001");
    }

    static void queue() throws RemoteException{
        System.out.println(printer.queue());
    }

    static void topQueue(int job) throws RemoteException{
        System.out.println(printer.topQueue(job));
    }

    static void start() throws RemoteException{
        System.out.println(printer.start());

    }

    static void stop() throws RemoteException{
        System.out.println(printer.stop());

    }

    static void restart() throws RemoteException{
        System.out.println(printer.restart());

    }

    static void status() throws RemoteException{
        System.out.println(printer.status());

    }

    static void readConfig(String parameter) throws RemoteException{
        System.out.println(printer.readConfig(parameter));

    }

    static void setConfig(String parameter, String value) throws RemoteException{
        System.out.println(printer.setConfig(parameter,value));

    }
}
