package com.amazon.alexa;

import android.content.Context;
import android.view.WindowManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvideWindowManagerFactory.java */
/* loaded from: classes.dex */
public final class wsG implements Factory<WindowManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public wsG(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (WindowManager) Preconditions.checkNotNull(this.zZm.Qle(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
