package com.pahlsoft.trebuchet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class TrebuchetClient {

    private static String server;
    private static int port;
    private static Socket socket;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;

    public TrebuchetClient(String server, int port) {
        this.server = server;
        this.port = port;
        connect();
    }

    public void connect() {
        try {
            InetAddress host = InetAddress.getByName(this.server);
            socket = new Socket(host, this.port);

            if (socket.isConnected()) {
                System.out.println("Client: Connected to Server: " + this.server + " on Port: " + this.port);
            }
        } catch (ConnectException ce) {
            System.out.println("Client: Failed to Connect to Server " + this.server + " on Port: " + this.port);
        } catch (UnknownHostException e) {
            System.out.println("Client: unable to resolve host name or IP: " + this.server);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Enum send(int portNumber) {
        Enum response = Dialogue.NULL;

        try {
            initStreams();
            oos.writeObject(portNumber);
            response = (Enum) ois.readObject();
        } catch (UnknownHostException e) {
            System.out.println("Client: Unable to determine Host name " + server);
        } catch (SocketException se ) {
            System.out.println("Client: Unable to connect to host " + server);
        } catch (IOException e) {
            System.out.println("Client: Unable to Send to host" + server);
        } catch (ClassNotFoundException e) {
            System.out.println("Client: Bad Message");
        }
        return response;
    }

    public Enum send(Dialogue message) {
        Enum response = Dialogue.NULL;

        try {
            initStreams();
            oos.writeObject(message);
            response = (Enum) ois.readObject();
        } catch (UnknownHostException e) {
            System.out.println("Client: Unknown Host Exception");
        } catch (IOException e) {
            System.out.println("Client: Unable to connect to server on port: " + port);
        } catch (ClassNotFoundException e) {
            System.out.println("Client: Class Not Found");
        }

        return response;
    }

    public void disconnect()  {
        if (socket.isConnected()) {
                try {

                    if (ois != null) ois.close();
                    if (oos !=null) oos.close();

                        socket.shutdownOutput();
                        socket.shutdownInput();
                        socket.close();
                } catch (EOFException eof) {
                    System.out.println("Client: Socket is already closed");
                } catch (Exception e) {
                    System.out.println("Client: Client is already disconnected");
                }
        } else {
            System.out.println("Client: Client is disconnected");
        }
    }

    private void initStreams() {
        try {
            if (oos == null)
                oos = new ObjectOutputStream(socket.getOutputStream());
            if (ois == null) {
                ois = new ObjectInputStream(socket.getInputStream());
            }
        } catch (IOException ioe) {
            System.out.println("Client: Unable to Initialize Streams");
        }

    }

    public boolean isConnected() {
        try {
            if (ois != null) {
                if (ois.read() == -1) {
                    return false;

                } else {
                    return true;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
