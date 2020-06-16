package priv.zzz.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyCache implements Cache<String, Object>{

    @Resource
    JedisPool jedisPool;

    @Override
    public Object get(String s) throws CacheException {
        return jedisPool.getResource().get(getKey(s));
    }

    @Override
    public Object put(String s, Object o) throws CacheException {
        return jedisPool.getResource().set(getKey(s), (String) o);
    }

    @Override
    public Object remove(String s) throws CacheException {
        return jedisPool.getResource().del(getKey(s));
    }

    @Override
    public void clear() throws CacheException {
        jedisPool.getResource().flushDB();
    }

    @Override
    public int size() {
        return jedisPool.getResource().dbSize().intValue();
    }

    @Override
    public Set<String> keys() {
        return jedisPool.getResource().keys("shiro:*");
    }

    @Override
    public Collection<Object> values() {
        Set<String> keys = this.keys();
        List<Object> values = new ArrayList<>();
        for(String key : keys){
            values.add(this.get(key));
        }
        return values;
    }

    private String getKey(String s){
        return "shiro:" + s;
    }
}
