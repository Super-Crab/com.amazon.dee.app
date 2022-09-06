package com.amazon.alexa.feature.consumer.api;

import java.util.Set;
/* loaded from: classes7.dex */
public interface FeatureFlagConsumer {
    void enqueueFeatureFlagListener(String str, FeatureFlagListener featureFlagListener);

    FeatureQuery getFeatureQuery();

    void initialize();

    void load(Set<String> set);

    void loadAll();

    void teardown();
}
