package com.thousandeyes.api.cache;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roberto on 31/07/2015.
 */
public class MemcacheManager {
    private InetAddress address;
    private int port;
    /* time to live of key-value in cache, in seconds */
    private int timeToLive;

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public MemcachedClient getClient(){
        InetSocketAddress socket = new InetSocketAddress(address, port);
        MemcachedClient client = null;
        try {
            client = new MemcachedClient(socket);
        } catch (IOException e) {
            //Do nothing
        }
        return client;
    }

    public boolean checkToken(MemcachedClient client, String token) {
        Object value;
        Future<Object> f = client.asyncGet(token);
        try {
            // throws expecting InterruptedException, ExecutionException or TimeoutException
            value = f.get(500, TimeUnit.MILLISECONDS);
            /* value can be NULL if it's not in cache */
            if (value == null)
                return false;
            return true;
        } catch (Exception e) {
            f.cancel(true);
            return false;
        }
    }

    public boolean addTokenInCache(MemcachedClient client, String token) {
        if (client == null)
            return false;
        // key = value
        client.set(token, timeToLive, token);
        return true;
    }
}
