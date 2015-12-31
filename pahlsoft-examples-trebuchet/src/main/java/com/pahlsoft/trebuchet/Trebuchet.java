package com.pahlsoft.trebuchet;

public class Trebuchet {

    private static int lowerRange;
    private static int upperRange;
    private static String serverName;

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack" , "true");

        try {

            if (args[0].contains("-S")) {
                System.out.println("Starting Server on Port: " + args[0].split("-S")[1]);
                TrebuchetServer trebuchetServer = new TrebuchetServer(Integer.parseInt(args[0].split("-S")[1]));
            } else if (args[0].contains("-C")) {
                System.out.println("Client Connecting to Server: " + args[0].split("-C")[1].split(":")[0]);
                serverName = args[0].split("-C")[1].split(":")[0];
                TrebuchetClient trebuchetClient = new TrebuchetClient(args[0].split("-C")[1].split(":")[0],
                        Integer.parseInt(args[0].split("-C")[1].split(":")[1]));


                trebuchetClient.send(Dialogue.SALUTATION);


                if (args[1].contains("-p")) {
                    System.out.println("Lower Port: " + args[1].split("-p")[1]);
                    lowerRange = Integer.parseInt(args[1].split("-p")[1]);
                }

                if (args[2].contains("-P")) {
                    System.out.println("Upper Port: " + args[2].split("-P")[1]);
                    upperRange = Integer.parseInt(args[2].split("-P")[1]);
                }

                for ( int counter = lowerRange; counter <= upperRange; counter++ ) {
                    trebuchetClient.send(Dialogue.START_TEST);
                    trebuchetClient.send(counter);
                    TrebuchetClient tcSlave = new TrebuchetClient(serverName, counter);
                    tcSlave.send(Dialogue.SALUTATION);
                    Thread.sleep(1000);
                }
                trebuchetClient.send(Dialogue.TERMINATE);

            } else if ((args[0].contains("--help"))) {
                printUsage();
            }

        } catch (ArrayIndexOutOfBoundsException aeob) {
            argumentError();
        } catch (InterruptedException ie) {
            System.out.println("Interrupted");
        }

    }

    private static void argumentError() {
        System.out.println("Error: Incorrect Number of Arguments");
        System.out.println("Usage: Trebuchet.jar [ -S<portnum> | -C<ip:port> -p<portnum> -P<portnum>]");
        printUsage();
        System.exit(1);
    }

    private static void printUsage() {
        System.out.println("Server Example: java -jar Trebuchet.jar -S9080");
        System.out.println("Client Example: java -jar Trebuchet.jar -Clocalhost:9080 -p1238 -P55023");
        System.exit(0);
    }

}
