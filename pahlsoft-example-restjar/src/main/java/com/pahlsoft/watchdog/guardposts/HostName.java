package com.pahlsoft.watchdog.guardposts;

import com.pahlsoft.watchdog.utility.CommandLine;
import org.apache.log4j.Logger;
import org.json.JSONArray;


public class HostName {

    public static Logger LOG = Logger.getLogger(HostName.class);

    private static final String statusScript = "hostname";

    public static JSONArray execute() {

        LOG.info("Executing HostName Post");

        return CommandLine.execute(statusScript);
    }
}
