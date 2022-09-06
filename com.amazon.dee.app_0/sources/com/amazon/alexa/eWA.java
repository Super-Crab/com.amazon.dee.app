package com.amazon.alexa;

import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: FeatureFlagModule_ProvidesFeatureQueryFactory.java */
/* loaded from: classes.dex */
public final class eWA implements Factory<FeatureQuery> {
    public final Provider<FeatureFlagConsumer> BIo;
    public final ENl zZm;

    public eWA(ENl eNl, Provider<FeatureFlagConsumer> provider) {
        this.zZm = eNl;
        this.BIo = provider;
    }

    public static eWA zZm(ENl eNl, Provider<FeatureFlagConsumer> provider) {
        return new eWA(eNl, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (FeatureQuery) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
