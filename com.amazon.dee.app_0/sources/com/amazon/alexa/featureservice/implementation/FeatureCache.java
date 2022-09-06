package com.amazon.alexa.featureservice.implementation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.repo.model.Feature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class FeatureCache {
    private Map<String, Feature> featureCache = new HashMap();
    private Map<String, Feature> featureOverrideCache = new HashMap();
    private Gson gson;

    @Inject
    public FeatureCache(Gson gson) {
        this.gson = gson;
    }

    private synchronized Map<String, Feature> deepCopy(Map<String, Feature> map) {
        return (Map) this.gson.fromJson(this.gson.toJson(map), new TypeToken<Map<String, Feature>>() { // from class: com.amazon.alexa.featureservice.implementation.FeatureCache.1
        }.getType());
    }

    @Nullable
    public synchronized void cacheFeature(@NonNull Feature feature) {
        if (feature != null) {
            this.featureCache.put(feature.getFeatureName(), feature);
        }
    }

    public synchronized void clear() {
        this.featureCache.clear();
        this.featureOverrideCache.clear();
    }

    public synchronized Set<Map.Entry<String, Feature>> entrySet() {
        return getAllAsMap().entrySet();
    }

    public synchronized Map<String, Feature> getAllAsMap() {
        return deepCopy(this.featureCache);
    }

    @Nullable
    public synchronized Feature getFeature(@NonNull String str) {
        if (str != null) {
            return this.featureCache.get(str);
        }
        return null;
    }

    @Nullable
    public synchronized Feature getFeatureOverride(@NonNull String str) {
        if (str != null) {
            return this.featureOverrideCache.get(str);
        }
        return null;
    }

    public synchronized void overrideFeature(@NonNull Feature feature) {
        if (feature != null) {
            this.featureOverrideCache.put(feature.getFeatureName(), feature);
        }
    }

    public synchronized void removeOverride(@NonNull String str) {
        if (str != null) {
            this.featureOverrideCache.remove(str);
        }
    }
}
