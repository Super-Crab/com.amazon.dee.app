package com.amazon.alexa;

import android.app.KeyguardManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvideKeyguardManagerFactory.java */
/* loaded from: classes.dex */
public final class adM implements Factory<KeyguardManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public adM(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    public static adM zZm(dyd dydVar, Provider<Context> provider) {
        return new adM(dydVar, provider);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (KeyguardManager) Preconditions.checkNotNull(this.zZm.BIo(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
