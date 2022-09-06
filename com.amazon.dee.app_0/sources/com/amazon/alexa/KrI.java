package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: FeatureFlagModule_ProvidesFeatureFlagConsumerFactory.java */
/* loaded from: classes.dex */
public final class KrI implements Factory<FeatureFlagConsumer> {
    public final Provider<Context> BIo;
    public final ENl zZm;

    public KrI(ENl eNl, Provider<Context> provider) {
        this.zZm = eNl;
        this.BIo = provider;
    }

    public static KrI zZm(ENl eNl, Provider<Context> provider) {
        return new KrI(eNl, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (FeatureFlagConsumer) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
