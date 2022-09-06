package com.amazon.alexa.featureservice.repo.provider;

import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.featureservice.repo.mapper.FeatureCollectionMapper;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class FeatureDataRepoImpl_Factory implements Factory<FeatureDataRepoImpl> {
    private final Provider<FeatureCollectionMapper> featureCollectionMapperProvider;
    private final Provider<RxFeatureDao> featureDaoProvider;
    private final Provider<FeatureServiceClient> featureServiceClientProvider;

    public FeatureDataRepoImpl_Factory(Provider<FeatureServiceClient> provider, Provider<RxFeatureDao> provider2, Provider<FeatureCollectionMapper> provider3) {
        this.featureServiceClientProvider = provider;
        this.featureDaoProvider = provider2;
        this.featureCollectionMapperProvider = provider3;
    }

    public static FeatureDataRepoImpl_Factory create(Provider<FeatureServiceClient> provider, Provider<RxFeatureDao> provider2, Provider<FeatureCollectionMapper> provider3) {
        return new FeatureDataRepoImpl_Factory(provider, provider2, provider3);
    }

    public static FeatureDataRepoImpl newFeatureDataRepoImpl(FeatureServiceClient featureServiceClient, RxFeatureDao rxFeatureDao, FeatureCollectionMapper featureCollectionMapper) {
        return new FeatureDataRepoImpl(featureServiceClient, rxFeatureDao, featureCollectionMapper);
    }

    public static FeatureDataRepoImpl provideInstance(Provider<FeatureServiceClient> provider, Provider<RxFeatureDao> provider2, Provider<FeatureCollectionMapper> provider3) {
        return new FeatureDataRepoImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureDataRepoImpl mo10268get() {
        return provideInstance(this.featureServiceClientProvider, this.featureDaoProvider, this.featureCollectionMapperProvider);
    }
}
