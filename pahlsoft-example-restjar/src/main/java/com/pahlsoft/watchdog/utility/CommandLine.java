package com.pahlsoft.watchdog.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandLine {
    public static ArrayList<String> execute(String cmd) {
        ArrayList<String> response = new ArrayList<String>();
        String line = "";
        BufferedReader input = null;

        String [] execCmd = { "/bin/sh","-c", cmd };

        try {
            Process process = Runtime.getRuntime().exec(execCmd);
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                response.add(line);
             }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}
