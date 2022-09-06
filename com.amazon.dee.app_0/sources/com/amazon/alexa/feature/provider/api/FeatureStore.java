package com.amazon.alexa.feature.provider.api;
/* loaded from: classes7.dex */
public interface FeatureStore {
    void delete();

    void initialize();

    void store(Iterable<String> iterable);
}
