package com.pahlsoft.watchdog.gardposts;

import com.pahlsoft.watchdog.utility.CommandLine;

public class GeneralJavaPost {

    private static final String statusScript = "ps -ef| grep java | grep -v grep | wc -l";

    public static String getStatus() {
        return CommandLine.execute(statusScript).get(0);
    }
}
