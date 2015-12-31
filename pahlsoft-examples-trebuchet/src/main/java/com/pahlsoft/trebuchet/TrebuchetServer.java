package com.pahlsoft.trebuchet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TrebuchetServer implements Runnable {
    private static ServerSocket server;
    private static int port;
    private static Socket socket;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static Dialogue message;
    private static long initTime;
    private static boolean keepRunning = true;
    private static long TIMEOUTMAX = 30000;

    public  TrebuchetServer(int port) {
        TrebuchetServer.port = port;
        if (isServerListening("localhost",port)) {
            System.out.println("Server: Already Running");
        } else {
            Thread t = new Thread(this);
            t.start();
        }
    }

    public void run() {
        try {
            initTime = System.currentTimeMillis();

            server = new ServerSocket(port);
            socket = server.accept();

            System.out.println("Server: Waiting for client message...");

            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            while (underMaxTime() && keepRunning) {

                message = (Dialogue) ois.readObject();
                switch (message) {
                    case SALUTATION:
                        System.out.println("Server: Received Salutation : " + port);
                        oos.writeObject(Dialogue.SALUTATION);
                        break;
                    case TERMINATE:
                        System.out.println("Server: Received Terminate Request");
                        oos.writeObject(Dialogue.ACK_TERMINATE);
                        keepRunning = false;
                        break;
                    case START_TEST:
                        oos.writeObject(Dialogue.ACK_START_TEST);
                        int targetPort = (int) ois.readObject();
                        TrebuchetServer ts = new TrebuchetServer(targetPort);
                        oos.writeObject(Dialogue.ACK_TARGET_PORT);
                        System.out.println("Server: Starting Test on Port: " + targetPort);
                        break;
                    default:
                        System.out.println("Server: Unrecognized Command");
                        oos.writeObject(Dialogue.BAD_COMMAND);
                        break;

                }
            }
        } catch (SocketException se) {
            System.out.println("Server: Sockets Closed");
        } catch (EOFException eofe) {
            //System.out.println("Server: No More Input Data on Port: " + port);
        } catch (IOException e) {
            System.out.println("Server: IO Exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Server: No Class Def Found");
        }
    }

    private boolean underMaxTime() {
        if ( System.currentTimeMillis() < (initTime + TIMEOUTMAX )) {
            return true;
        }
        System.out.println("Server: Max Time Reached");
        keepRunning=false;
        return false;
    }

    public void stop() {
        System.out.println("Server: Stopping Server Socket");
        try {
            System.out.println("Server: Closing Server and Sockets");
            oos.close();
            ois.close();
            server.close();
            socket.close();
        } catch (NullPointerException npe) {
            System.out.println("Server: Already Stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isListening() {
        try {
            if (ois.readObject() == null) {
                return false;
            }
        } catch (EOFException eofe ) {
            return false;
        } catch (NullPointerException npe) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isServerListening(String host, int port)
    {
        Socket s = null;
        try
        {
            s = new Socket(host, port);
            s.sendUrgentData(1);
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if(s != null) {
                try {s.close();}
                catch(Exception e){}
            }
        }
        return true;
    }

}
