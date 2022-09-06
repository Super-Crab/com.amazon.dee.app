package com.amazon.alexa;

import android.media.SoundPool;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: AudioPlayerModule_ProvidesScoSoundPoolFactory.java */
/* loaded from: classes.dex */
public final class MXD implements Factory<SoundPool> {
    public final uuv zZm;

    public MXD(uuv uuvVar) {
        this.zZm = uuvVar;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (SoundPool) Preconditions.checkNotNull(this.zZm.zQM(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
