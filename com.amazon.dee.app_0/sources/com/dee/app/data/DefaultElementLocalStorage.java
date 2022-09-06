package com.dee.app.data;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.CacheMetadata;
import com.dee.app.data.api.ElementLocalStorage;
import com.dee.app.data.api.EvictionTier;
import com.google.common.base.Optional;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
/* loaded from: classes10.dex */
public class DefaultElementLocalStorage implements ElementLocalStorage {
    private static final String TAG = "com.dee.app.data.DefaultElementLocalStorage";
    private Cache<AppDataCacheEntry> cache;

    public DefaultElementLocalStorage(Cache<AppDataCacheEntry> cache) {
        this.cache = cache;
    }

    @Override // com.dee.app.data.api.ElementLocalStorage
    public Observable<Void> clear(String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            return this.cache.clear(str);
        }
        throw new IllegalArgumentException("Cannot clear with empty namespace.");
    }

    @VisibleForTesting
    String createElementsCacheItemJson(String str, String str2) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("key", str);
                jSONObject.put("value", str2);
                jSONObject.put("timestamp", System.currentTimeMillis());
                jSONObject.put("ttl", 60000);
                return jSONObject.toString();
            }
            throw new IllegalArgumentException("Cannot put data with empty value argument.");
        }
        throw new IllegalArgumentException("Cannot put value with empty key argument.");
    }

    @VisibleForTesting
    String extractValueFromCacheItemJson(String str) throws JSONException {
        return new JSONObject(str).getString("value");
    }

    @Override // com.dee.app.data.api.ElementLocalStorage
    public Observable<AppDataCacheEntry> get(String str, @Nullable Bundle bundle) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            final boolean z = (bundle == null || !bundle.containsKey("extractValueFromJson")) ? true : bundle.getBoolean("extractValueFromJson");
            return this.cache.get(str, CacheMetadata.EMPTY).map(new Func1() { // from class: com.dee.app.data.-$$Lambda$DefaultElementLocalStorage$YGFAcV7jGZctfsQuBF3Sv7BZ9LI
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return DefaultElementLocalStorage.this.lambda$get$0$DefaultElementLocalStorage(z, (Optional) obj);
                }
            });
        }
        throw new IllegalArgumentException("Cannot get value with empty key argument.");
    }

    public Cache<AppDataCacheEntry> getNativeCache() {
        return this.cache;
    }

    public /* synthetic */ AppDataCacheEntry lambda$get$0$DefaultElementLocalStorage(boolean z, Optional optional) {
        if (!optional.isPresent()) {
            return null;
        }
        AppDataCacheEntry appDataCacheEntry = (AppDataCacheEntry) optional.get();
        if (z) {
            try {
                appDataCacheEntry.setData(extractValueFromCacheItemJson(appDataCacheEntry.getData()));
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse Elements Cache Json, returning original value", e);
            }
        }
        return appDataCacheEntry;
    }

    public /* synthetic */ void lambda$put$1$DefaultElementLocalStorage(Bundle bundle, String str, String str2, Subscriber subscriber) {
        boolean z;
        try {
            try {
                EvictionTier evictionTier = EvictionTier.EVICTION_TIER_1;
                int i = 1;
                if (bundle != null) {
                    z = bundle.containsKey("encrypt") ? bundle.getBoolean("encrypt") : true;
                    evictionTier = EvictionTier.fromValue(bundle.getString("evictionTier"));
                } else {
                    z = true;
                }
                if (evictionTier != EvictionTier.EVICTION_TIER_1) {
                    i = 0;
                }
                AppDataCacheEntry appDataCacheEntry = new AppDataCacheEntry(str, evictionTier.getAppDataCacheEvictionTier(), z);
                this.cache.put(str2, appDataCacheEntry, new CacheMetadata(i)).toBlocking().subscribe();
                subscriber.onNext(appDataCacheEntry);
            } catch (Exception e) {
                subscriber.onError(e);
            }
        } finally {
            subscriber.onCompleted();
        }
    }

    @Override // com.dee.app.data.api.ElementLocalStorage
    public Observable<AppDataCacheEntry> put(final String str, final String str2, @Nullable final Bundle bundle) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                boolean z = true;
                if (bundle != null && bundle.containsKey("jsonify")) {
                    z = bundle.getBoolean("jsonify");
                }
                if (z) {
                    try {
                        str2 = createElementsCacheItemJson(str, str2);
                    } catch (JSONException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.data.-$$Lambda$DefaultElementLocalStorage$tWoISUS1ZfsSUARZJRaR4GMbq1s
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        DefaultElementLocalStorage.this.lambda$put$1$DefaultElementLocalStorage(bundle, str2, str, (Subscriber) obj);
                    }
                });
            }
            throw new IllegalArgumentException("Cannot put data with empty value argument.");
        }
        throw new IllegalArgumentException("Cannot put value with empty key argument.");
    }

    @Override // com.dee.app.data.api.ElementLocalStorage
    public Observable<Void> remove(String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            return this.cache.remove(str, CacheMetadata.EMPTY);
        }
        throw new IllegalArgumentException("Cannot remove value with empty key argument.");
    }
}
