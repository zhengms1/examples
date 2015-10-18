package com.pahlsoft.watchdog.guardposts;

import com.pahlsoft.watchdog.utility.CommandLine;
import org.apache.log4j.Logger;
import org.json.JSONArray;

public class MemoryPost {

    public static Logger LOG = Logger.getLogger(GeneralJavaPost.class);

    private static final String statusScript = "free -m  |grep Mem | awk '{print \"Assigned:\"$2,\",Used:\"$3, \",Free:\" $4}'";

    public static JSONArray execute() {

        LOG.info("Executing Memory Used Post");

        return CommandLine.execute(statusScript);
    }
}
