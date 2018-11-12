package com.DTU;

import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.rmi.Naming.lookup;


public class PrintClient {
    private static final int PORT = 1245;
    static JSONObject hashedPass;

    private static Printerface printer;

    public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
        new PrintClient();
        createToken("Hackerman101","GandalfTheWhite1");
        System.out.println(printer.echo(hashedPass,"Loopback test"));

        stop();
        print();
        start();
        restart();
        setConfig("Out of Magenta","false");
        readConfig("Any Magenta left?");





        //System.out.println("-- " + printer.echo("Hey Server"));


    }


    public PrintClient() throws RemoteException, NotBoundException, MalformedURLException {

        printer = (Printerface) lookup("rmi://localhost:6969/Printers");

        //Registry registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostName(), PORT);
        //printer = (Printerface) registry.lookup("Printers");
    }

    static void print() throws RemoteException{
        printer.print(hashedPass,"text.pdf","lab-001");
    }

    static void queue() throws RemoteException{
        System.out.println(printer.queue(hashedPass));
    }

    static void topQueue(int job) throws RemoteException{
        System.out.println(printer.topQueue(hashedPass,job));
    }

    static void start() throws RemoteException{
        System.out.println(printer.start(hashedPass));

    }

    static void stop() throws RemoteException{
        System.out.println(printer.stop(hashedPass));

    }

    static void restart() throws RemoteException{
        System.out.println(printer.restart(hashedPass));

    }

    static void status() throws RemoteException{
        System.out.println(printer.status(hashedPass));

    }

    static void readConfig(String parameter) throws RemoteException{
        System.out.println(printer.readConfig(hashedPass,parameter));

    }

    static void setConfig(String parameter, String value) throws RemoteException{
        System.out.println(printer.setConfig(hashedPass,parameter,value));

    }
    private static void createToken(String username, String password) throws RemoteException{
        hashedPass = new JSONObject();
        System.out.println("Username: "+username+" Password: "+password);
        hashedPass.put(1,username);
        hashedPass.put(2,hashAndSaltPass(password));
        //System.out.println(printer.verifyUser(hashedPass));

    }

    private static String hashAndSaltPass(String password){
        String salt = "123123123123123123123123123123123123123123123123"; // Shortcut, should have been a variable salt
        String pass = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            pass = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        System.out.println("Hashed and salted password: "+pass);
        return pass;
    }
    private static void setSSLSettings() {
        String pass = "Gandalf";
        System.setProperty("javax.net.ssl.debug", "all");
        System.setProperty("javax.net.ssl.keyStore", "D:\\ssl\\ClientKeyStore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", pass);
        System.setProperty("javax.net.ssl.trustStore", "D:\\ssl\\ClientTrustStore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", pass);
    }

}
