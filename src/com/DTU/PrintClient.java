package com.DTU;

import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static java.rmi.Naming.lookup;


public class PrintClient {
    private static final int PORT = 1245;
    static JSONObject userpass;

    private static Printerface printer;

    public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        new PrintClient();
        createToken("Alice","Cooper");
        //System.out.println(printer.echo(userpass,"IT's ALIVE!"));
        print();
        // System.out.println(userpass);
        status();

        // Username: Alice Password: Cooper
        // Username: Bob Password: Terminator
        // Username: Cecilia Password: Simon & Garfunkel
        // Username: David Password: Goliath
        // Username: Erica Password: AirWrecka
        // Username: Fred Password: Gandalf
        // Username: George Password: Weasley
        //
        //        stop();
        //        print();
        //        start();
        //        restart();
        //        setConfig("Out of Magenta","false");
        //        readConfig("Any Magenta left?");





        // System.out.println("-- " + printer.echo("Hey Server"));


    }

    public static void verifyUser() throws RemoteException {
        System.out.println(printer.verifyUser(userpass));
    }


    public PrintClient() throws RemoteException, NotBoundException, MalformedURLException {

        printer = (Printerface) lookup("rmi://localhost:1245/Printers");

        //Registry registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostName(), PORT);
        //printer = (Printerface) registry.lookup("Printers");
    }

    static void print() throws RemoteException{
        System.out.println(printer.print(userpass,"text.pdf","lab-001"));
    }

    static void queue() throws RemoteException{
        System.out.println(printer.queue(userpass));
    }

    static void topQueue(int job) throws RemoteException{
        System.out.println(printer.topQueue(userpass,job));
    }

    static void start() throws RemoteException{
        System.out.println(printer.start(userpass));
    }

    static void stop() throws RemoteException{
        System.out.println(printer.stop(userpass));
    }

    static void restart() throws RemoteException{
        System.out.println(printer.restart(userpass));
    }

    static void status() throws RemoteException{
        System.out.println(printer.status(userpass));
    }

    static void readConfig(String parameter) throws RemoteException{
        System.out.println(printer.readConfig(userpass,parameter));
    }

    static void setConfig(String parameter, String value) throws RemoteException{
        System.out.println(printer.setConfig(userpass,parameter,value));
    }

    static void echo(String parameter) throws RemoteException{
        System.out.println(printer.echo(userpass,parameter));
    }

    private static void createToken(String username, String password) throws RemoteException{
        userpass = new JSONObject();
        //System.out.println("Username: "+username+" Password: "+password);
        userpass.put(1,username);
        userpass.put(2,(password));
        //System.out.println(printer.verifyUser(userpass));

    }



}
