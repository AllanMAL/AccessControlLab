package com.DTU;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PrintService extends UnicastRemoteObject implements Printerface {

    /* Global variables: */
    private static JSONObject userList;
    private static JSONObject policy;
    private static final int PORT = 1245;

    /* psvm */
    public static void main(String[] args) throws IOException, AlreadyBoundException, ParseException {


        Registry registry = LocateRegistry.createRegistry(PORT);
        registry.bind("Printers", new PrintService());

    }

    private PrintService() throws IOException, ParseException {
        super(PORT);

        //TODO: Login system with "cookie" or other temporary server-supplied token.
        //userList.put("Hackerman101","f119caf16702d1bac8620e9becb42dcbb98170810ab1ee02edfe29b81cb2d34ec4713446cdce165dc1c2240e97f086dee80e34588f78084beccdab53230a41b7");
    }

    private JSONObject readFile(String filename) throws IOException, ParseException {

        InputStream input = getClass().getResourceAsStream(filename);
        JSONParser parser = new JSONParser();
        JSONObject obj;
        obj = (JSONObject) parser.parse(new FileReader(filename));
        return obj;
    }

    @Override
    public String echo(JSONObject ident, String input) throws RemoteException {
        if(verify(ident,"echo")){
            return "You made me say " + input;
        }
        return ("echo: "+"Action not allowed");
    }

    @Override
    public String print(JSONObject ident, String filename, String printer) throws RemoteException {
        if(verify(ident,"print")){
            System.out.println("Print requested. Filename: " + filename + " -- Printer: " + printer);
            return "Printing " + filename;
        }
        return ("print: "+"Action not allowed");
    }

    @Override
    public String queue(JSONObject ident) throws RemoteException {
        if(verify(ident,"queue")){
            System.out.println("Get queue");
            return "Queue: 999 jobs in queue";
        }
        return ("queue: "+"Action not allowed");
    }

    @Override
    public String topQueue(JSONObject ident, int job) throws RemoteException {
        if(verify(ident,"topQueue")){
            System.out.println("Priorities changed!");
            return "Job "+job+" moved to top of queue";
        }
        return ("topQueue: "+"Action not allowed");
    }

    @Override
    public String start(JSONObject ident) throws RemoteException {
        if(verify(ident,"start")){
            System.out.println("Server start");
            return "Starting print server";
        }
        return ("start: "+"Action not allowed");
    }

    @Override
    public String stop(JSONObject ident) throws RemoteException {
        if(verify(ident,"stop")){
            System.out.println("Server stop");
            return "Stopping print server";
        }
        return ("stop: "+"Action not allowed");
    }

    @Override
    public String restart(JSONObject ident) throws RemoteException {
        if(verify(ident,"restart")){
            System.out.println("restart");
            return "Restarting print server";
        }
        return ("restart: "+"Action not allowed");
    }

    @Override
    public String status(JSONObject ident) throws RemoteException {
        if(verify(ident,"status")){
            System.out.println("Status request");
            return "Status: It's all good.";
        }
        return ("Status: "+"Action not allowed");
    }

    @Override
    public String readConfig(JSONObject ident, String parameter) throws RemoteException {
        if(verify(ident,"readConfig")){
            System.out.println("Config get");
            return "Reading config "+parameter;
        }
        return ("readConfig: "+"Action not allowed");
    }

    @Override
    public String setConfig(JSONObject ident, String parameter, String value) throws RemoteException {
        if(verify(ident,"setConfig")){
            System.out.println("Config set");
            return "Setting config "+parameter;
        }
        return ("setConfig: "+"Action not allowed");
    }

    @Override
    public String verifyUser(JSONObject ident) throws RemoteException {
         if(verify(ident,"verifyUser")){
            System.out.println("User "+ident.get(1)+" logged in");
             JSONObject userData = (JSONObject) userList.get(ident.get(1));
            return "Welcome to the printing system "+ident.get(1)+". \nAvailable commands: "+
                    policy.get(userData.get("Role"));
        }
        return "verifyUser failed. "+"Username or password incorrect";

    }

    @Override
    public boolean verify(JSONObject token, String task) {
        boolean state = false;
        Object name = token.get(1);

        policy = new JSONObject();
        userList = new JSONObject();

        try {
            userList.putAll(readFile(".\\src\\com\\DTU\\Userlist_pt3.json"));
            policy.putAll(readFile(".\\src\\com\\DTU\\Policy_pt2.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String hashedPass = hashAndSaltPass(token);
        if (userList.containsKey(name)) {
            JSONObject userData = (JSONObject) userList.get(name);
            if (userData.get("Password").equals(hashedPass)) {
                JSONArray commands = (JSONArray) policy.get(userData.get("Role"));
                for (Object command : commands) {
                    if (command.toString().equals(task)) {
                        state = true;
                        return state;

                    }
                }
            }
        }
        return state;

    }

    private static String hashAndSaltPass(JSONObject token){
        JSONObject userData = (JSONObject) userList.get(token.get(1));
        //System.out.println(userData.get("Salt"));
        String salt = userData.get("Salt").toString();
        String pass = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(token.get(2).toString().getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            pass = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        System.out.println("Hashed and salted password: "+pass);
        return pass;
    }
}
