package com.pahlsoft.watchdog.guardposts;

import com.pahlsoft.watchdog.utility.CommandLine;
import org.apache.log4j.Logger;
import org.json.JSONArray;

public class JavaProcessesPost {

    public static Logger LOG = Logger.getLogger(JavaProcessesPost.class);

    private static final String statusScript = "jps -l";

    public static JSONArray execute() {

        LOG.info("Executing Java Process Listing Post");

        return CommandLine.execute(statusScript);
    }
}
