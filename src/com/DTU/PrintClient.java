package com.DTU;

import org.json.simple.JSONObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PrintClient implements Serializable {
    private static final int PORT = 12083;

    private static Printerface printer;

    public static void main(String[] args) throws RemoteException, NotBoundException, UnknownHostException {
        new PrintClient();
        login("Hackerman101","GandalfTheWhite1");


        //printer = (Printerface) Naming.lookup("rmi://localhost:6969/Printers");

        //System.out.println("-- " + printer.echo("Hey Server"));


    }

    public PrintClient() throws UnknownHostException, RemoteException, NotBoundException {
        //setSSLSettings();
        Registry registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostName(), PORT,new SslRMIClientSocketFactory());
        printer = (Printerface) registry.lookup("Printers");
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
    private static void login(String username, String password) throws RemoteException{
        JSONObject hashedPass = new JSONObject();
        hashedPass.put(1,username);
        hashedPass.put(2,hashAndSaltPass(password));
        System.out.println(printer.login(hashedPass));
        System.out.println();
    }

    private static String hashAndSaltPass(String password){
        String salt = "123123123123123123123123123123123123123123123123";
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
        // System.out.println(pass);
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
