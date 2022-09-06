package com.amazon.alexa.featureservice.api;

import java.util.Map;
/* loaded from: classes7.dex */
public interface PlatformFeatureServiceV2 extends FeatureServiceV2 {
    Map<String, String> getAllFeatures();

    String getSerializedFeatureCache();

    void prefetchFeatures(String[] strArr);
}
