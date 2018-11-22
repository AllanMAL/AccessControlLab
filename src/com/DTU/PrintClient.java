package com.DTU;

import org.json.simple.JSONObject;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import static java.rmi.Naming.lookup;


public class PrintClient {
    static JSONObject userpass;

    private static Printerface printer;

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        new PrintClient();

    }


    public PrintClient() throws NotBoundException, MalformedURLException {

        try {
            printer = (Printerface) lookup("rmi://localhost:1245/Printers");
            createToken("Henry","Terminator");

        /*
        Current usernames and passwords:
         Username: Alice    Password: Cooper
         Username: Bob      Password: Terminator
         Username: Cecilia  Password: Simon & Garfunkel
         Username: David    Password: Goliath
         Username: Erica    Password: AirWrecka
         Username: Fred     Password: Gandalf
         Username: George   Password: Weasley
         Username: Henry    Password: Terminator
         Username: Ida      Password: God
        */

            System.out.println(printer.echo(userpass,"IT's ALIVE!"));
            print();
            status();
            start();
            stop();
            restart();
            queue();
            topQueue(2);
            setConfig("Fix hats", "false");
        } catch (RemoteException e) {
            System.out.println("Server not available... "+ "\nTurn it on!");
            //e.printStackTrace();
        }


    }


    static void verifyUser() throws RemoteException {
        System.out.println(printer.verifyUser(userpass));
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

    private static void createToken(String username, String password) throws RemoteException {
        userpass = new JSONObject();
        //System.out.println("Username: "+username+" Password: "+password);
        userpass.put(1,username);
        userpass.put(2,(password));
        System.out.println(printer.verifyUser(userpass));

    }



}
