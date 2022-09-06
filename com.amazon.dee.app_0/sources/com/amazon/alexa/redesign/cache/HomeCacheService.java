package com.amazon.alexa.redesign.cache;

import androidx.annotation.NonNull;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.CacheException;
import com.dee.app.cachemanager.CacheMetadata;
import com.google.common.base.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.exceptions.Exceptions;
/* loaded from: classes10.dex */
public class HomeCacheService {
    public static final int HOMECACHE_VERSION_NUMBER = 1;
    private static final String HOME_CARDS_KEY = "HOME_CARDS";
    private static final String MODULE_NAME = "HomeDataCache";
    private static final String TAG = "HomeCacheService";
    private Cache<AppDataCacheEntry> cache;
    private CacheMetadata cacheMetadata = new CacheMetadata(MODULE_NAME);

    public HomeCacheService(Cache<AppDataCacheEntry> cache) {
        this.cache = cache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ JSONObject lambda$getRawCards$0(Optional optional) {
        if (optional.isPresent()) {
            try {
                return new JSONObject(((AppDataCacheEntry) optional.get()).getData());
            } catch (ClassCastException e) {
                throw Exceptions.propagate(e);
            } catch (JSONException e2) {
                throw Exceptions.propagate(e2);
            }
        }
        throw Exceptions.propagate(new CacheException("No Cache"));
    }

    public synchronized Observable<Void> clearCacheCards() {
        return this.cache.clear(this.cacheMetadata);
    }

    public synchronized Observable<JSONObject> getRawCards() {
        return this.cache.get(HOME_CARDS_KEY, this.cacheMetadata).map($$Lambda$HomeCacheService$ZPt5wVqfoZ0fswMSjUVP8v7wRE.INSTANCE);
    }

    public synchronized Observable<Void> saveRawCards(@NonNull JSONObject jSONObject) {
        return this.cache.put(HOME_CARDS_KEY, new AppDataCacheEntry(jSONObject.toString(), 0, true), this.cacheMetadata);
    }
}
