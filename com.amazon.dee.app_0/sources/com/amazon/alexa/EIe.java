package com.amazon.alexa;

import android.media.SoundPool;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: AudioPlayerModule_ProvidesSoundPoolFactory.java */
/* loaded from: classes.dex */
public final class EIe implements Factory<SoundPool> {
    public final uuv zZm;

    public EIe(uuv uuvVar) {
        this.zZm = uuvVar;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (SoundPool) Preconditions.checkNotNull(this.zZm.zyO(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
