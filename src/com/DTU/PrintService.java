package com.DTU;

import org.json.simple.JSONObject;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class PrintService extends UnicastRemoteObject implements Printerface {

    /* Global variables: */
    private JSONObject userList;
    private static final int PORT = 1245;

    /* psvm */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {


        Registry registry = LocateRegistry.createRegistry(6969);
        registry.bind("Printers", new PrintService());

    }


    private PrintService() throws RemoteException {
        super(PORT);

        userList = new JSONObject();
        userList.put("Gandalf","2f972eed9d08bca8020307da4d8d84fff052b6c15b49763e6351c84274ecb98f843a66d1ce41966899f3a5dc101cd60c804c203d94be2ab1ee4f89285e6867b5");
        userList.put("Hackerman101","f119caf16702d1bac8620e9becb42dcbb98170810ab1ee02edfe29b81cb2d34ec4713446cdce165dc1c2240e97f086dee80e34588f78084beccdab53230a41b7");
    }


    @Override
    public String echo(JSONObject ident, String input) {
        if(verify(ident)){
            return "from server " + input;
        }
        return ("Unregistered user");
    }

    @Override
    public void print(JSONObject ident, String filename, String printer) {
        if(verify(ident)){
            System.out.println("Print requested. Filename: " + filename + " -- Printer: " + printer);
        }
    }

    @Override
    public String queue(JSONObject ident) {
        if(verify(ident)){
            System.out.println("Get queued");
            return "0 jobs in queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String topQueue(JSONObject ident, int job) {
        if(verify(ident)){
            System.out.println("Priorities changed!");
            return "Job "+job+" moved to top of queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String start(JSONObject ident) {
        if(verify(ident)){
            System.out.println("Server start");
            return "Starting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String stop(JSONObject ident) {
        if(verify(ident)){
            System.out.println("Server stop");
            return "Stopping print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String restart(JSONObject ident) {
        if(verify(ident)){
            System.out.println("Server restart");
            return "Restarting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String status(JSONObject ident) {
        if(verify(ident)){
            System.out.println("Status request");
            return "'s all good.";
        }
        return ("Unregistered user");
    }

    @Override
    public String readConfig(JSONObject ident, String parameter) {
        if(verify(ident)){
            System.out.println("Config get");
            return "Reading config "+parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String setConfig(JSONObject ident, String parameter, String value) {
        if(verify(ident)){
            System.out.println("Config set");
            return "Setting config "+parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String verifyUser(JSONObject ident) {
         if(verify(ident)){
            System.out.println("User "+ident.get(1)+" logged in");
            return "Login succesful. \n"+
                    "The following options are now available: \n" +
                    "print(String filename, String printer)\\n\" +\n" +
                    "queue()\\n\" +\n" +
                    "topQueue(int job)\\n\" +\n" +
                    "start()\\n\" +\n" +
                    "stop()\\n\" +\n" +
                    "restart()\\n\" +\n" +
                    "status()\\n\" +\n" +
                    "readConfig(String parameter)\\n\" +\n" +
                    "setConfig(String parameter, String value)\"";
        }
        return "Username or password incorrect";
    }

    @Override
    public boolean verify(JSONObject token){
        if(userList.containsKey(token.get(1)) && userList.get(token.get(1)).equals(token.get(2))){
            return true;
        } else {
            return false;
        }

    }

}
