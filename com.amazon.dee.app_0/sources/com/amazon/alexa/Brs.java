package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.DataDirectoryProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvidesDataFolderProviderFactory.java */
/* loaded from: classes.dex */
public final class Brs implements Factory<DataDirectoryProvider> {
    public final Provider<Context> BIo;
    public final iPU zZm;

    public Brs(iPU ipu, Provider<Context> provider) {
        this.zZm = ipu;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (DataDirectoryProvider) Preconditions.checkNotNull(this.zZm.BIo(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
