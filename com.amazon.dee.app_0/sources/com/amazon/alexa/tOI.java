package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: OfflinePromptsModule_ProvideOfflinePromptsArtifactManagerFactory.java */
/* loaded from: classes.dex */
public final class tOI implements Factory<kHl> {
    public final QYP zZm;

    public tOI(QYP qyp) {
        this.zZm = qyp;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (kHl) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
