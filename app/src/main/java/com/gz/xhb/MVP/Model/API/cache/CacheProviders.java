package com.gz.xhb.MVP.Model.API.cache;

import com.gz.xhb.MyApplication.MyApplication;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by QingMei on 2017/7/10.
 * desc:
 */

public class CacheProviders {

    private static IXHBCacheProviders cacheProviders;

    public synchronized static IXHBCacheProviders getCache() {
        if (cacheProviders == null) {
            cacheProviders = new RxCache.Builder()
                    .persistence(MyApplication.getContext().getExternalCacheDir(), new GsonSpeaker())
                    .using(IXHBCacheProviders.class);
        }
        return cacheProviders;
    }
}
