package com.DTU;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintService extends UnicastRemoteObject implements Printerface {
    private boolean accessDenied = true;
    private String name = "Dia",pass = "Beetus";



    PrintService() throws RemoteException {
        super();
    }

    @Override
    public String echo(String input) {
        if(!accessDenied) {
            return "from server " + input;
        }
        return ("Unregistered user");
    }

    @Override
    public void print(String filename, String printer) {
        if(!accessDenied) {
            System.out.println("Print requested. Filename: " + filename + " -- Printer: " + printer);
        }
    }

    @Override
    public String queue() {
        if(!accessDenied) {
            System.out.println("Get queued");
            return "0 jobs in queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String topQueue(int job) {
        if(!accessDenied) {
            System.out.println("Priorities changed!");
            return "Job "+job+" moved to top of queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String start() {
        if(!accessDenied) {
            System.out.println("Server start");
            return "Starting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String stop() {
        if(!accessDenied) {
            System.out.println("Server stop");
            return "Stopping print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String restart() {
        if(!accessDenied) {
            System.out.println("Server restart");
            return "Restarting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String status() {
        if(!accessDenied) {
            System.out.println("Status request");
            return "'s all good.";
        }
        return ("Unregistered user");
    }

    @Override
    public String readConfig(String parameter) {
        if(!accessDenied) {
            System.out.println("Config get");
            return parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String setConfig(String parameter, String value) {
        if(!accessDenied) {
            System.out.println("Config set");
            return parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String login(String username, String password) {
        if(accessDenied) {
            if(username.equals(name) && password.equals(pass)) {
                System.out.println("User "+username+" logged in");
                accessDenied = false;
                return "true";
            }
            return "Username or password incorrect";
        } else{
            return "You are already logged in!";
        }
    }
}
