package com.pahlsoft.watchdog;

import com.pahlsoft.watchdog.gardposts.GeneralJavaPost;
import com.pahlsoft.watchdog.gardposts.MemCachedPost;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/watchdog")
public class WatchDogEntryPoint {

    @GET
    @Path("all")
    @Produces(MediaType.TEXT_PLAIN)
    public String all() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("MemCached Instances", MemCachedPost.getStatus());
        map.put("General Java Process Count", GeneralJavaPost.getStatus());
        return toJson(map);
    }

    @GET
    @Path("memcached")
    @Produces(MediaType.TEXT_PLAIN)
    public String memcached() {
        return toJson("MemCached Instances", MemCachedPost.getStatus());
    }

    @GET
    @Path("java")
    @Produces(MediaType.TEXT_PLAIN)
    public String java() {
        return toJson("General Java Process Count", GeneralJavaPost.getStatus());
    }

    private String toJson(String key, String value) {
        JSONObject obj = new JSONObject();
        obj.put(key, value);
        return obj.toString();
    }

    private String toJson(Map map) {
        JSONObject obj = new JSONObject();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry entry = (Map.Entry) entries.next();
            obj.put((String)entry.getKey(),entry.getValue());
        }
        return obj.toString();
    }

}
