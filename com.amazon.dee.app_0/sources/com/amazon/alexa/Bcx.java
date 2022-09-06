package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* compiled from: NetworkingModule_ProvidesNetworkAuthorityFactory.java */
/* loaded from: classes.dex */
public final class Bcx implements Factory<dAN> {
    public final Provider<DVu> BIo;
    public final Provider<aew> jiA;
    public final Provider<OkHttpClient> zQM;
    public final Provider<AlexaClientEventBus> zZm;
    public final Provider<FqH> zyO;

    public Bcx(Provider<AlexaClientEventBus> provider, Provider<DVu> provider2, Provider<OkHttpClient> provider3, Provider<FqH> provider4, Provider<aew> provider5) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        Provider<AlexaClientEventBus> provider = this.zZm;
        Provider<DVu> provider2 = this.BIo;
        Provider<OkHttpClient> provider3 = this.zQM;
        Provider<FqH> provider4 = this.zyO;
        return (dAN) Preconditions.checkNotNull(new dAN(provider.mo10268get(), provider2, provider3, provider4.mo10268get(), this.jiA), "Cannot return null from a non-@Nullable @Provides method");
    }
}
