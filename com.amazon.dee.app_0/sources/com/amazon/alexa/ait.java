package com.amazon.alexa;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Properties;
import javax.inject.Provider;
/* compiled from: ConfigurationModule_ProvidesPropertiesFactory.java */
/* loaded from: classes.dex */
public final class ait implements Factory<Properties> {
    public final Provider<Context> BIo;
    public final VWb zZm;

    public ait(VWb vWb, Provider<Context> provider) {
        this.zZm = vWb;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Properties) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
