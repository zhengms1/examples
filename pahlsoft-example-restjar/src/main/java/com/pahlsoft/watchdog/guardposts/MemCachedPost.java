package com.pahlsoft.watchdog.guardposts;

import com.pahlsoft.watchdog.utility.CommandLine;
import org.apache.log4j.Logger;

public class MemCachedPost  {

    public static Logger LOG = Logger.getLogger(GeneralJavaPost.class);

    private static final String statusScript = "ps -ef|grep memcached | grep -v grep | wc -l";

    public static String execute() {

        LOG.info("Executing MemCached Post");

        return CommandLine.execute(statusScript).get(0);
    }
}
