package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: PermissionsModule_ProvidesPermissionCheckerFactory.java */
/* loaded from: classes.dex */
public final class Vtr implements Factory<Dtt> {
    public final Provider<ClientConfiguration> BIo;
    public final kVR zZm;

    public Vtr(kVR kvr, Provider<ClientConfiguration> provider) {
        this.zZm = kvr;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Dtt) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
