package com.DTU;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class PrintService extends UnicastRemoteObject implements Printerface {

    /* Global variables: */
    private static JSONObject userList;
    private static JSONObject policy;
    private static final int PORT = 1245;

    /* psvm */
    public static void main(String[] args) throws IOException, AlreadyBoundException, ParseException {


        Registry registry = LocateRegistry.createRegistry(6969);
        registry.bind("Printers", new PrintService());

    }

    private PrintService() throws IOException, ParseException {
        super(PORT);

        userList = new JSONObject();
        userList.putAll(readFile("C:\\Users\\Steve\\Dropbox\\DTU 2017-19\\02239 - Data Security\\AccessControlLab\\src\\com\\DTU\\Userlist_pt1.json"));
        //System.out.println(userList);
        policy = new JSONObject();
        policy.putAll(readFile("C:\\Users\\Steve\\Dropbox\\DTU 2017-19\\02239 - Data Security\\AccessControlLab\\src\\com\\DTU\\Policy_pt1.json"));
        System.out.println(policy.get("Alice"));

        //TODO: Login system with "cookie" or other temporary server-supplied token.
        //userList.put("Gandalf","2f972eed9d08bca8020307da4d8d84fff052b6c15b49763e6351c84274ecb98f843a66d1ce41966899f3a5dc101cd60c804c203d94be2ab1ee4f89285e6867b5");
        //userList.put("Hackerman101","f119caf16702d1bac8620e9becb42dcbb98170810ab1ee02edfe29b81cb2d34ec4713446cdce165dc1c2240e97f086dee80e34588f78084beccdab53230a41b7");
    }

    private JSONObject readFile(String filename) throws IOException, ParseException {

        InputStream input = getClass().getResourceAsStream(filename);
        JSONParser parser = new JSONParser();
        JSONObject obj;
        obj = (JSONObject) parser.parse(new FileReader(filename));
        return obj;
        //TODO: Relative path
    }

    @Override
    public String echo(JSONObject ident, String input) throws RemoteException {
        if(verify(ident,"echo")){
            return "You made me say " + input;
        }
        return ("Unregistered user");
    }

    @Override
    public void print(JSONObject ident, String filename, String printer) throws RemoteException {
        if(verify(ident,"print")){
            System.out.println("Print requested. Filename: " + filename + " -- Printer: " + printer);
        }
    }

    @Override
    public String queue(JSONObject ident) throws RemoteException {
        if(verify(ident,"queue")){
            System.out.println("Get queued");
            return "0 jobs in queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String topQueue(JSONObject ident, int job) throws RemoteException {
        if(verify(ident,"topQueue")){
            System.out.println("Priorities changed!");
            return "Job "+job+" moved to top of queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String start(JSONObject ident) throws RemoteException {
        if(verify(ident,"start")){
            System.out.println("Server start");
            return "Starting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String stop(JSONObject ident) throws RemoteException {
        if(verify(ident,"stop")){
            System.out.println("Server stop");
            return "Stopping print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String restart(JSONObject ident) throws RemoteException {
        if(verify(ident,"restart")){
            System.out.println("Server restart");
            return "Restarting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String status(JSONObject ident) throws RemoteException {
        if(verify(ident,"status")){
            System.out.println("Status request");
            return "It's all good.";
        }
        return ("Unregistered user");
    }

    @Override
    public String readConfig(JSONObject ident, String parameter) throws RemoteException {
        if(verify(ident,"readConfig")){
            System.out.println("Config get");
            return "Reading config "+parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String setConfig(JSONObject ident, String parameter, String value) throws RemoteException {
        if(verify(ident,"setConfig")){
            System.out.println("Config set");
            return "Setting config "+parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String verifyUser(JSONObject ident) throws RemoteException {
         if(verify(ident,"verifyUser")){
            System.out.println("User "+ident.get(1)+" logged in");
            return "Welcome to the printing system. Available commands: \n"+
                    policy.get(ident.get(1));
        }
        return "Username or password incorrect";

    }

    @Override
    public boolean verify(JSONObject token, String task) throws RemoteException {
        boolean state = false;
        Object name = token.get(1);
        if(userList.containsKey(name) && userList.get(name).equals(token.get(2))){
            JSONArray commands = (JSONArray) policy.get(name);
            for (Object command : commands) {
                System.out.println("Ping");
                if (command.toString().equals(task)) {
                    System.out.println("Success!");
                    state = true;
                    return state;

                }
            }
        }
        return state;
    }

}
