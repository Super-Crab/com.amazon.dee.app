package com.amazon.alexa;

import android.view.WindowManager;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: LegacyFlagsModule_ProvidesDefaultLegacyFlagsFactory.java */
/* renamed from: com.amazon.alexa.MTi  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0174MTi implements Factory<Cta> {
    public final Provider<WindowManager> BIo;
    public final Provider<ClientConfiguration> zQM;
    public final ZVy zZm;

    public C0174MTi(ZVy zVy, Provider<WindowManager> provider, Provider<ClientConfiguration> provider2) {
        this.zZm = zVy;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Cta) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
