package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: ContextModule_ProvidesApplicationContextFactory.java */
/* loaded from: classes.dex */
public final class PXQ implements Factory<Context> {
    public final dyd zZm;

    public PXQ(dyd dydVar) {
        this.zZm = dydVar;
    }

    public static PXQ zZm(dyd dydVar) {
        return new PXQ(dydVar);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Context) Preconditions.checkNotNull(this.zZm.zZm.getApplicationContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
