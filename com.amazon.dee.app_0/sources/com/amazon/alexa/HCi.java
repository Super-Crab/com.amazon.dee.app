package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ExternalMediaPlayerModule_ProvidesPlayerInFocusStoreFactory.java */
/* loaded from: classes.dex */
public final class HCi implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final ueX zZm;

    public HCi(ueX uex, Provider<Context> provider) {
        this.zZm = uex;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.BIo(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
