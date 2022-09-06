package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: WakeWordModule_ProvideWakeWordArtifactDownloadFactory.java */
/* loaded from: classes.dex */
public final class PPo implements Factory<RfA> {
    public final iPU zZm;

    public PPo(iPU ipu) {
        this.zZm = ipu;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (RfA) Preconditions.checkNotNull(this.zZm.jiA(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
