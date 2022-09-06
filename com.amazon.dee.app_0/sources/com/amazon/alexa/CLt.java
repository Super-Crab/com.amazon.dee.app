package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* compiled from: PlaylistParser_Factory.java */
/* loaded from: classes.dex */
public final class CLt implements Factory<onD> {
    public final Provider<OkHttpClient> zZm;

    public CLt(Provider<OkHttpClient> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new onD(this.zZm.mo10268get());
    }
}
