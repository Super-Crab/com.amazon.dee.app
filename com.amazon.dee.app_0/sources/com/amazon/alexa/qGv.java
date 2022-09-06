package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideArtifactManagerFactory.java */
/* loaded from: classes.dex */
public final class qGv implements Factory<ArtifactManager> {
    public final Provider<Context> BIo;
    public final iPU zZm;

    public qGv(iPU ipu, Provider<Context> provider) {
        this.zZm = ipu;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (ArtifactManager) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
