package com.pahlsoft.watchdog.gardposts;

import com.pahlsoft.watchdog.utility.CommandLine;

public class MemCachedPost  {

    private static final String statusScript = "ps -ef|grep memcached | grep -v grep | wc -l";

    public static String getStatus() {
        return CommandLine.execute(statusScript).get(0);
    }
}
