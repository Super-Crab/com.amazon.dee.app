package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvidePreloadAttributionManagerFactory.java */
/* renamed from: com.amazon.alexa.lcD  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0216lcD implements Factory<PreloadAttributionManager> {
    public final Provider<Context> BIo;
    public final Provider<FeatureQuery> zQM;
    public final kbj zZm;

    public C0216lcD(kbj kbjVar, Provider<Context> provider, Provider<FeatureQuery> provider2) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PreloadAttributionManager) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
