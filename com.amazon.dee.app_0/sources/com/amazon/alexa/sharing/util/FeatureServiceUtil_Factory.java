package com.amazon.alexa.sharing.util;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class FeatureServiceUtil_Factory implements Factory<FeatureServiceUtil> {
    private final Provider<FeatureServiceV2> featureServiceV2Provider;

    public FeatureServiceUtil_Factory(Provider<FeatureServiceV2> provider) {
        this.featureServiceV2Provider = provider;
    }

    public static FeatureServiceUtil_Factory create(Provider<FeatureServiceV2> provider) {
        return new FeatureServiceUtil_Factory(provider);
    }

    public static FeatureServiceUtil newFeatureServiceUtil(FeatureServiceV2 featureServiceV2) {
        return new FeatureServiceUtil(featureServiceV2);
    }

    public static FeatureServiceUtil provideInstance(Provider<FeatureServiceV2> provider) {
        return new FeatureServiceUtil(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureServiceUtil mo10268get() {
        return provideInstance(this.featureServiceV2Provider);
    }
}
