package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: ChannelsModule_ProvideChannelStorageFactory.java */
/* loaded from: classes.dex */
public final class lCd implements Factory<PersistentStorage> {
    public final Provider<Context> BIo;
    public final dsY zZm;

    public lCd(dsY dsy, Provider<Context> provider) {
        this.zZm = dsy;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (PersistentStorage) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
