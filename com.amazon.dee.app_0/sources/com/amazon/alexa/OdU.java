package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;
/* compiled from: NetworkingModule_ProvidesBaseOkHttpClientFactory.java */
/* loaded from: classes.dex */
public final class OdU implements Factory<OkHttpClient> {
    public static final OdU zZm = new OdU();

    public static OdU zZm() {
        return zZm;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (OkHttpClient) Preconditions.checkNotNull(QuV.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
