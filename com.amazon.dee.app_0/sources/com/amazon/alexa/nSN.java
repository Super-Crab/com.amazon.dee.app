package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ExternalMediaPlayerModule_ProvidesPlaybackStateLoaderFactory.java */
/* loaded from: classes.dex */
public final class nSN implements Factory<uTP> {
    public final Provider<PersistentStorage> BIo;
    public final Provider<TimeProvider> zQM;
    public final ueX zZm;
    public final Provider<Gson> zyO;

    public nSN(ueX uex, Provider<PersistentStorage> provider, Provider<TimeProvider> provider2, Provider<Gson> provider3) {
        this.zZm = uex;
        this.BIo = provider;
        this.zQM = provider2;
        this.zyO = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (uTP) Preconditions.checkNotNull(this.zZm.zZm(DoubleCheck.lazy(this.BIo), this.zQM.mo10268get(), this.zyO.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
