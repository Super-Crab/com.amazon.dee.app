package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CareActorsStore_Factory implements Factory<CareActorsStore> {
    private final Provider<CareActorsStoreV1> careActorsStoreV1Provider;
    private final Provider<CareActorsStoreV2> careActorsStoreV2Provider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;

    public CareActorsStore_Factory(Provider<CareActorsStoreV1> provider, Provider<CareActorsStoreV2> provider2, Provider<FeatureServiceV2> provider3) {
        this.careActorsStoreV1Provider = provider;
        this.careActorsStoreV2Provider = provider2;
        this.featureServiceV2Provider = provider3;
    }

    public static CareActorsStore_Factory create(Provider<CareActorsStoreV1> provider, Provider<CareActorsStoreV2> provider2, Provider<FeatureServiceV2> provider3) {
        return new CareActorsStore_Factory(provider, provider2, provider3);
    }

    public static CareActorsStore newCareActorsStore(Lazy<CareActorsStoreV1> lazy, Lazy<CareActorsStoreV2> lazy2, FeatureServiceV2 featureServiceV2) {
        return new CareActorsStore(lazy, lazy2, featureServiceV2);
    }

    public static CareActorsStore provideInstance(Provider<CareActorsStoreV1> provider, Provider<CareActorsStoreV2> provider2, Provider<FeatureServiceV2> provider3) {
        return new CareActorsStore(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CareActorsStore mo10268get() {
        return provideInstance(this.careActorsStoreV1Provider, this.careActorsStoreV2Provider, this.featureServiceV2Provider);
    }
}
