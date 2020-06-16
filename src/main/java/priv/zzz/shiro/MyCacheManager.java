package priv.zzz.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class MyCacheManager implements CacheManager {
    @Override
    public Cache getCache(String s) throws CacheException {
        return new MyCache();
    }
}
