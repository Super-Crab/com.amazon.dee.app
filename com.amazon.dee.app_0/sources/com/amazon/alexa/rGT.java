package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;
/* compiled from: OfflinePromptsModule_ProvideOfflinePathDirFactory.java */
/* loaded from: classes.dex */
public final class rGT implements Factory<File> {
    public final Provider<Context> BIo;
    public final QYP zZm;

    public rGT(QYP qyp, Provider<Context> provider) {
        this.zZm = qyp;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (File) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
