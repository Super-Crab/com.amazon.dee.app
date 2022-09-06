package com.amazon.alexa.featureservice.util;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes7.dex */
public class FeatureSubscriptionMap {
    private Map<String, Map<FeatureServiceV2.FeatureUpdateListener, Boolean>> featureToCallbackMap = new HashMap();
    private Map<FeatureServiceV2.FeatureUpdateListener, List<String>> callbackToFeatureMap = new HashMap();

    public synchronized void add(String str, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        if (str == null || featureUpdateListener == null) {
            return;
        }
        if (!this.featureToCallbackMap.containsKey(str)) {
            this.featureToCallbackMap.put(str, new HashMap());
        }
        Map<FeatureServiceV2.FeatureUpdateListener, Boolean> map = this.featureToCallbackMap.get(str);
        if (!map.containsKey(featureUpdateListener)) {
            map.put(featureUpdateListener, true);
        }
        if (!this.callbackToFeatureMap.containsKey(featureUpdateListener)) {
            this.callbackToFeatureMap.put(featureUpdateListener, new ArrayList());
        }
        List<String> list = this.callbackToFeatureMap.get(featureUpdateListener);
        if (!list.contains(str)) {
            list.add(str);
        }
    }

    public synchronized void clear() {
        this.featureToCallbackMap.clear();
        this.callbackToFeatureMap.clear();
    }

    public synchronized Set<FeatureServiceV2.FeatureUpdateListener> getSubscribers(String str) {
        HashSet hashSet = new HashSet();
        if (str != null && this.featureToCallbackMap.get(str) != null) {
            hashSet.addAll(this.featureToCallbackMap.get(str).keySet());
            return hashSet;
        }
        return hashSet;
    }

    public synchronized void remove(FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        if (featureUpdateListener == null) {
            return;
        }
        List<String> list = this.callbackToFeatureMap.get(featureUpdateListener);
        if (list != null) {
            for (String str : list) {
                this.featureToCallbackMap.get(str).remove(featureUpdateListener);
            }
            if (list.size() == 1) {
                this.callbackToFeatureMap.remove(featureUpdateListener);
            }
        }
    }
}
