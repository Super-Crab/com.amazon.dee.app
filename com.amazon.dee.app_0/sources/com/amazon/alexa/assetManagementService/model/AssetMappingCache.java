package com.amazon.alexa.assetManagementService.model;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes6.dex */
public class AssetMappingCache {
    private final Map<String, Map<String, AssetCache>> assetMappingCache = new ConcurrentHashMap();

    public Map<String, Map<String, AssetCache>> getAssetMappingCache() {
        return this.assetMappingCache;
    }

    public Map<String, AssetCache> getAssetMappingCacheBundle(String str) {
        if (this.assetMappingCache.containsKey(str)) {
            return this.assetMappingCache.get(str);
        }
        return null;
    }

    public String getAssetMappingCacheItem(@NonNull String str, @NonNull String str2) {
        if (this.assetMappingCache.containsKey(str)) {
            Map<String, AssetCache> assetMappingCacheBundle = getAssetMappingCacheBundle(str);
            AssetCache assetCache = assetMappingCacheBundle.containsKey(str2) ? assetMappingCacheBundle.get(str2) : null;
            if (assetCache == null) {
                return null;
            }
            return assetCache.getUrl();
        }
        return null;
    }

    public Iterator<String> getBundleIds() {
        return this.assetMappingCache.keySet().iterator();
    }

    public void setAssetMappingCache(String str, JsonObject jsonObject) {
        if (jsonObject != null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                concurrentHashMap.put(entry.getKey(), (AssetCache) new Gson().fromJson(((JsonObject) entry.getValue()).toString(), (Class<Object>) AssetCache.class));
            }
            this.assetMappingCache.put(str, concurrentHashMap);
        }
    }
}
