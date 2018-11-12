package com.DTU;

import org.json.simple.JSONObject;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class PrintService extends UnicastRemoteObject implements Printerface {

    /* Global variables: */
    private boolean accessDenied = true;
    JSONObject userList = new JSONObject();
    private static final int PORT = 1245;

    /* psvm */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {


//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        Registry registry = LocateRegistry.createRegistry(6969);
        registry.bind("Printers", new PrintService());


//        try {
//            Registry registry = LocateRegistry.createRegistry(PORT, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
//
//            PrintService printServer = new PrintService();
//            registry.bind("Printers", printServer);
//        } catch (AlreadyBoundException e) {
//            e.printStackTrace();
//        }

    }




    PrintService() throws RemoteException {
        super(PORT);/*,new SslRMIClientSocketFactory(),new SslRMIServerSocketFactory());*/
        //setSSLSettings();

        userList.put("Gandalf","2f972eed9d08bca8020307da4d8d84fff052b6c15b49763e6351c84274ecb98f843a66d1ce41966899f3a5dc101cd60c804c203d94be2ab1ee4f89285e6867b5");
        userList.put("Hackerman101","f119caf16702d1bac8620e9becb42dcbb98170810ab1ee02edfe29b81cb2d34ec4713446cdce165dc1c2240e97f086dee80e34588f78084beccdab53230a41b7");
    }

//    private void setSSLSettings() {
//        String pass = "Gandalf";
//        System.setProperty("javax.net.ssl.debug","all");
//        System.setProperty("javax.net.ssl.keyStore","D:\\ssl\\ServerKeyStore.jks");
//        System.setProperty("javax.net.ssl.keyStorePassword",pass);
//        System.setProperty("javax.net.ssl.trustStore","D:\\ssl\\ServerTrustStore.jks");
//        System.setProperty("javax.net.ssl.trustStorePassword",pass);
//
//    }

    @Override
    public String echo(JSONObject ident, String input) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            return "from server " + input;
        }
        return ("Unregistered user");
    }

    @Override
    public void print(JSONObject ident, String filename, String printer) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Print requested. Filename: " + filename + " -- Printer: " + printer);
        }
    }

    @Override
    public String queue(JSONObject ident) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Get queued");
            return "0 jobs in queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String topQueue(JSONObject ident, int job) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Priorities changed!");
            return "Job "+job+" moved to top of queued";
        }
        return ("Unregistered user");
    }

    @Override
    public String start(JSONObject ident) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Server start");
            return "Starting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String stop(JSONObject ident) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Server stop");
            return "Stopping print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String restart(JSONObject ident) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Server restart");
            return "Restarting print server";
        }
        return ("Unregistered user");
    }

    @Override
    public String status(JSONObject ident) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Status request");
            return "'s all good.";
        }
        return ("Unregistered user");
    }

    @Override
    public String readConfig(JSONObject ident, String parameter) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Config get");
            return "Reading config "+parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String setConfig(JSONObject ident, String parameter, String value) {
        if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
            System.out.println("Config set");
            return "Setting config "+parameter;
        }
        return ("Unregistered user");
    }

    @Override
    public String login(JSONObject ident) {
        if(accessDenied) {
            if(userList.containsKey(ident.get(1)) && userList.get(ident.get(1)).equals(ident.get(2))) {
                System.out.println("User "+ident.get(1)+" logged in");
                accessDenied = false;
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
        } else{
            return "You are already logged in!";
        }
    }

}
