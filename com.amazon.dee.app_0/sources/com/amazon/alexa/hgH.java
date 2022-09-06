package com.amazon.alexa;

import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: UnlockActivityModule_ProvideTimeProviderFactory.java */
/* loaded from: classes.dex */
public final class hgH implements Factory<TimeProvider> {
    public final Mit zZm;

    public hgH(Mit mit) {
        this.zZm = mit;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (TimeProvider) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
