package com.amazon.alexa;

import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvidesDirectedIDProviderFactory.java */
/* loaded from: classes.dex */
public final class Xml implements Factory<DirectedIDProvider> {
    public final Provider<tol> BIo;
    public final kbj zZm;

    public Xml(kbj kbjVar, Provider<tol> provider) {
        this.zZm = kbjVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (DirectedIDProvider) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
