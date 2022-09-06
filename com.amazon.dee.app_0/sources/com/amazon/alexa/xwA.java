package com.amazon.alexa;

import android.media.MediaPlayer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: AudioPlayerModule_ProvidesMediaPlayerFactory.java */
/* loaded from: classes.dex */
public final class xwA implements Factory<MediaPlayer> {
    public final uuv zZm;

    public xwA(uuv uuvVar) {
        this.zZm = uuvVar;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (MediaPlayer) Preconditions.checkNotNull(this.zZm.BIo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
