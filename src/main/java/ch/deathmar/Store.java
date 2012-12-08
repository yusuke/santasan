package ch.deathmar;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Store {
    static Cache<String , Object> cache;
    static Cache<String , Object> tempCache;
    static{
        try {
            cache = new DefaultCacheManager(Store.class.getResourceAsStream("/infinispan.xml")).getCache("xml-configured-cache");
            tempCache = new DefaultCacheManager(Store.class.getResourceAsStream("/infinispan.xml")).getCache("temporary-cache");
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
//        cache = new HashMap<String,Object>();
//        tempCache = new HashMap<String,Object>();
    }
    public static void put(String str, Object obj){
        cache.put(str,obj);
    }
    public static Object get(String str){
        return cache.get(str);
    }

    public static void putTemporal(String key, Object obj){
        tempCache.put(key,obj);
    }
    public static Object getTemporal(String key){
        return tempCache.get(key);
    }
}
