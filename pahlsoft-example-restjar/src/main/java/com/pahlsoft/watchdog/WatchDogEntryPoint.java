package com.pahlsoft.watchdog;

import com.google.gson.Gson;
import com.pahlsoft.watchdog.guardposts.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/watchdog")
public class WatchDogEntryPoint {

    @GET
    @Path("all")
    @Produces(MediaType.TEXT_PLAIN)
    public String all() {

        JSONObject jo = new JSONObject();
        Collection<JSONObject> items = new ArrayList<JSONObject>();

        JSONObject item1 = new JSONObject();
        item1.put("HostName", HostName.execute());
        items.add(item1);
        JSONObject item2 = new JSONObject();
        item2.put("Java Processes", JavaProcessesPost.execute());
        items.add(item2);
        JSONObject item3 = new JSONObject();
        item3.put("General Java:", GeneralJavaPost.execute());
        items.add(item3);
        jo.put("WatchDogReport", new JSONArray().put(items));

        return jo.toString();
    }

    @GET
    @Path("memcached")
    @Produces(MediaType.TEXT_PLAIN)
    public String memcached() {
        JSONObject jo = new JSONObject();
        jo.put("Memcached", MemCachedPost.execute());
        return jo.toString();
    }

    @GET
    @Path("java")
    @Produces(MediaType.TEXT_PLAIN)
    public String java() {
        JSONObject jo = new JSONObject();
        jo.put("General Java", GeneralJavaPost.execute());
        return jo.toString();
    }

    @GET
    @Path("memory")
    @Produces(MediaType.TEXT_PLAIN)
    public String memory() {
        JSONObject jo = new JSONObject();
        jo.put("Memory Usage", MemoryPost.execute());
        return jo.toString();
    }

    @GET
    @Path("jps")
    @Produces(MediaType.TEXT_PLAIN)
    public String jps() {
        JSONObject jo = new JSONObject();
        jo.put("Java Process List", JavaProcessesPost.execute());
        return jo.toString();

    }



}
