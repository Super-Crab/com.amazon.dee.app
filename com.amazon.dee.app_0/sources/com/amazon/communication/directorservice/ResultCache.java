package com.amazon.communication.directorservice;

import com.amazon.communication.time.AndroidTimeSource;
import com.amazon.communication.time.TimeSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
public class ResultCache {
    private Map<ResultCacheKey, ResultCacheValue> mCache;
    private TimeSource mTimeSource;

    private ResultCache(ConcurrentHashMap<ResultCacheKey, ResultCacheValue> concurrentHashMap, TimeSource timeSource) {
        this.mCache = concurrentHashMap;
        this.mTimeSource = timeSource;
    }

    private boolean isExpired(ResultCacheValue resultCacheValue, int i) {
        return resultCacheValue.getLastWriteMillis() < this.mTimeSource.currentTimeMillis() - ((long) (i * 1000));
    }

    public static ResultCache newCache() {
        return new ResultCache(new ConcurrentHashMap(), AndroidTimeSource.INSTANCE);
    }

    public GetEndpointResponse get(String str, String str2, String str3, int i) {
        ResultCacheValue resultCacheValue = this.mCache.get(ResultCacheKey.newKey(str, str2, str3));
        if (resultCacheValue != null && !isExpired(resultCacheValue, i)) {
            return resultCacheValue.getResponse();
        }
        return null;
    }

    public void put(String str, String str2, String str3, GetEndpointResponse getEndpointResponse) {
        long currentTimeMillis = this.mTimeSource.currentTimeMillis();
        this.mCache.put(ResultCacheKey.newKey(str, str2, str3), ResultCacheValue.newValue(getEndpointResponse, currentTimeMillis));
    }

    public static ResultCache newCache(TimeSource timeSource) {
        return new ResultCache(new ConcurrentHashMap(), timeSource);
    }
}
