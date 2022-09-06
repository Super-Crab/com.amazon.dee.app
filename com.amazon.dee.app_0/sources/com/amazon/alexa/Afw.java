package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* compiled from: FeatureFlagConfigurationAuthority_Factory.java */
/* loaded from: classes.dex */
public final class Afw implements Factory<gSO> {
    public final Provider<FeatureQuery> BIo;
    public final Provider<ScheduledExecutorService> jiA;
    public final Provider<TimeProvider> zQM;
    public final Provider<FeatureFlagConsumer> zZm;
    public final Provider<AlexaClientEventBus> zyO;

    public Afw(Provider<FeatureFlagConsumer> provider, Provider<FeatureQuery> provider2, Provider<TimeProvider> provider3, Provider<AlexaClientEventBus> provider4, Provider<ScheduledExecutorService> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    public static Afw zZm(Provider<FeatureFlagConsumer> provider, Provider<FeatureQuery> provider2, Provider<TimeProvider> provider3, Provider<AlexaClientEventBus> provider4, Provider<ScheduledExecutorService> provider5) {
        return new Afw(provider, provider2, provider3, provider4, provider5);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        Provider<FeatureFlagConsumer> provider = this.zZm;
        Provider<FeatureQuery> provider2 = this.BIo;
        Provider<TimeProvider> provider3 = this.zQM;
        return new gSO(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), this.zyO, this.jiA.mo10268get());
    }
}
