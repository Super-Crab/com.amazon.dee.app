package com.amazon.alexa.handsfree.metrics.amok;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppMetricsInitializer_Factory implements Factory<VoiceAppMetricsInitializer> {
    private final Provider<FeatureServiceV2> featureServiceV2Provider;

    public VoiceAppMetricsInitializer_Factory(Provider<FeatureServiceV2> provider) {
        this.featureServiceV2Provider = provider;
    }

    public static VoiceAppMetricsInitializer_Factory create(Provider<FeatureServiceV2> provider) {
        return new VoiceAppMetricsInitializer_Factory(provider);
    }

    public static VoiceAppMetricsInitializer newVoiceAppMetricsInitializer(FeatureServiceV2 featureServiceV2) {
        return new VoiceAppMetricsInitializer(featureServiceV2);
    }

    public static VoiceAppMetricsInitializer provideInstance(Provider<FeatureServiceV2> provider) {
        return new VoiceAppMetricsInitializer(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceAppMetricsInitializer mo10268get() {
        return provideInstance(this.featureServiceV2Provider);
    }
}
