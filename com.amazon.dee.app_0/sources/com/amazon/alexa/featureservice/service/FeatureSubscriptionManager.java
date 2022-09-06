package com.amazon.alexa.featureservice.service;

import android.util.Log;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.util.FeatureSubscriptionMap;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class FeatureSubscriptionManager {
    private static final String TAG = "FeatureSubscriptionManager";
    private FeatureSubscriptionMap featureSubscriptionMap;

    @Inject
    public FeatureSubscriptionManager(FeatureSubscriptionMap featureSubscriptionMap) {
        this.featureSubscriptionMap = featureSubscriptionMap;
    }

    public synchronized void addSubscriber(String str, FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureSubscriptionMap.add(str, featureUpdateListener);
    }

    public synchronized void notifySubscribers(String str) {
        Set<FeatureServiceV2.FeatureUpdateListener> subscribers = this.featureSubscriptionMap.getSubscribers(str);
        if (subscribers != null) {
            for (FeatureServiceV2.FeatureUpdateListener featureUpdateListener : subscribers) {
                if (featureUpdateListener != null) {
                    try {
                        featureUpdateListener.onFeatureUpdate(str);
                    } catch (Exception e) {
                        Log.e(TAG, "Exception while notifying subscriber", e);
                    }
                }
            }
        }
    }

    public synchronized void removeSubscriber(FeatureServiceV2.FeatureUpdateListener featureUpdateListener) {
        this.featureSubscriptionMap.remove(featureUpdateListener);
    }

    public synchronized void reset() {
        this.featureSubscriptionMap.clear();
    }
}
