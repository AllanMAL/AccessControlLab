package com.DTU;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintService extends UnicastRemoteObject implements Printerface {


    PrintService() throws RemoteException {
        super();
    }

    @Override
    public String echo(String input) {
        return "from server " + input;
    }

    @Override
    public void print(String filename, String printer) {
        System.out.println("Print requested. Filename: "+filename+" -- Printer: "+printer);

    }

    @Override
    public String queue() {
        System.out.println("Get queued");
        return "0 jobs in queued";
    }

    @Override
    public String topQueue(int job) {

        System.out.println("Priorities changed!");
        return "Job "+job+" moved to top of queued";
    }

    @Override
    public String start() {
        System.out.println("Server start");
        return "Starting print server";
    }

    @Override
    public String stop() {
        System.out.println("Server stop");
        return "Stopping print server";
    }

    @Override
    public String restart() {
        System.out.println("Server restart");
        return "Restarting print server";    }

    @Override
    public String status() {
        System.out.println("Status request");
        return "'s all good.";
    }

    @Override
    public String readConfig(String parameter) {
        System.out.println("Config get");
        return parameter;
    }

    @Override
    public String setConfig(String parameter, String value) {
        System.out.println("Config set");
        return parameter;
    }
}
