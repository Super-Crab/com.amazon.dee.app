package com.amazon.alexa;

import com.amazon.alexa.wakeword.pryon.AudioPlaybackConfigurationHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: AudioPlayerModule_ProvidesAudioPlaybackConfigurationHelperFactory.java */
/* loaded from: classes.dex */
public final class mRo implements Factory<AudioPlaybackConfigurationHelper> {
    public final uuv zZm;

    public mRo(uuv uuvVar) {
        this.zZm = uuvVar;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AudioPlaybackConfigurationHelper) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
