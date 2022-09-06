package com.amazon.alexa.featureservice.api;
/* loaded from: classes7.dex */
public interface FeatureServiceV2 {

    /* loaded from: classes7.dex */
    public interface FeatureUpdateListener {
        void onFeatureUpdate(String str);
    }

    String getTreatmentAndRecordTrigger(String str, String str2);

    boolean hasAccess(String str, boolean z);

    void observeFeature(String str, FeatureUpdateListener featureUpdateListener);

    void unsubscribe(FeatureUpdateListener featureUpdateListener);
}
