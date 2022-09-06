package com.amazon.alexa;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: GooglePlayModule_ProvidesGoogleApiClientFactory.java */
/* loaded from: classes.dex */
public final class NQZ implements Factory<GoogleApiClient> {
    public final Provider<Context> BIo;
    public final BIC zZm;

    public NQZ(BIC bic, Provider<Context> provider) {
        this.zZm = bic;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (GoogleApiClient) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
