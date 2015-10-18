package com.pahlsoft.watchdog.utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {
    public static JSONArray execute(String command) {

        JSONArray jsonArray = new JSONArray();
        String line = "";
        BufferedReader input = null;

        String [] execCmd = { "/bin/sh","-c", command };

        try {
            Process process = Runtime.getRuntime().exec(execCmd);
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                jsonArray.put(line);
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
        return jsonArray;
    }
}
