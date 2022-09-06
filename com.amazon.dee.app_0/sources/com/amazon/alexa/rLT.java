package com.amazon.alexa;

import android.content.Context;
import android.media.AudioManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ContextModule_ProvidesAudioManagerFactory.java */
/* loaded from: classes.dex */
public final class rLT implements Factory<AudioManager> {
    public final Provider<Context> BIo;
    public final dyd zZm;

    public rLT(dyd dydVar, Provider<Context> provider) {
        this.zZm = dydVar;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AudioManager) Preconditions.checkNotNull(this.zZm.LPk(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
