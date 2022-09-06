package com.amazon.alexa;

import android.content.Context;
import android.media.SoundPool;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: AudioPlayerModule_ProvidesScoSoundWrapperFactory.java */
/* loaded from: classes.dex */
public final class pbK implements Factory<dcs> {
    public final Provider<SoundPool> BIo;
    public final Provider<Context> zQM;
    public final uuv zZm;

    public pbK(uuv uuvVar, Provider<SoundPool> provider, Provider<Context> provider2) {
        this.zZm = uuvVar;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (dcs) Preconditions.checkNotNull(this.zZm.zZm(DoubleCheck.lazy(this.BIo), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
