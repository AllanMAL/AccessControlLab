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

        return "0 jobs in queue";
    }

    @Override
    public String topQueue(int job) {

        return null;
    }

    @Override
    public boolean start() {

        return false;
    }

    @Override
    public boolean stop() {

        return false;
    }

    @Override
    public boolean restart() {

        return false;
    }

    @Override
    public String status() {

        return null;
    }

    @Override
    public String readConfig(String parameter) {

        return parameter;
    }

    @Override
    public String setConfig(String parameter, String value) {

        return parameter;
    }
}
