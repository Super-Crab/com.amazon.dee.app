package com.amazon.alexa;

import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.preload.attribution.FeatureQueryBridge;
/* compiled from: MetricsModule.java */
/* loaded from: classes.dex */
public class Zrn implements FeatureQueryBridge {
    public final /* synthetic */ FeatureQuery zZm;

    public Zrn(kbj kbjVar, FeatureQuery featureQuery) {
        this.zZm = featureQuery;
    }

    @Override // com.amazon.alexa.preload.attribution.FeatureQueryBridge
    public boolean isActive(String str) {
        return this.zZm.isActive(str);
    }
}
