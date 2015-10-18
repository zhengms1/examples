package com.pahlsoft.watchdog.guardposts;

import com.pahlsoft.watchdog.utility.CommandLine;

import org.apache.log4j.Logger;
import org.json.JSONArray;

public class GeneralJavaPost {

    public static Logger LOG = Logger.getLogger(GeneralJavaPost.class);

    private static final String statusScript = "ps -ef| grep java | grep -v grep | wc -l";

    public static JSONArray execute() {

        LOG.info("Executing General Java Post");

        return CommandLine.execute(statusScript);
    }
}
