package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.featureservice.repo.mapper.FeatureCollectionMapper;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvidesFeatureRepositoryFactory implements Factory<FeatureDataRepo> {
    private final Provider<RxFeatureDao> featureDaoProvider;
    private final Provider<FeatureServiceClient> featureServiceClientProvider;
    private final Provider<FeatureCollectionMapper> mapperProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvidesFeatureRepositoryFactory(RepositoryModule repositoryModule, Provider<FeatureServiceClient> provider, Provider<RxFeatureDao> provider2, Provider<FeatureCollectionMapper> provider3) {
        this.module = repositoryModule;
        this.featureServiceClientProvider = provider;
        this.featureDaoProvider = provider2;
        this.mapperProvider = provider3;
    }

    public static RepositoryModule_ProvidesFeatureRepositoryFactory create(RepositoryModule repositoryModule, Provider<FeatureServiceClient> provider, Provider<RxFeatureDao> provider2, Provider<FeatureCollectionMapper> provider3) {
        return new RepositoryModule_ProvidesFeatureRepositoryFactory(repositoryModule, provider, provider2, provider3);
    }

    public static FeatureDataRepo provideInstance(RepositoryModule repositoryModule, Provider<FeatureServiceClient> provider, Provider<RxFeatureDao> provider2, Provider<FeatureCollectionMapper> provider3) {
        return proxyProvidesFeatureRepository(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static FeatureDataRepo proxyProvidesFeatureRepository(RepositoryModule repositoryModule, FeatureServiceClient featureServiceClient, RxFeatureDao rxFeatureDao, FeatureCollectionMapper featureCollectionMapper) {
        return (FeatureDataRepo) Preconditions.checkNotNull(repositoryModule.providesFeatureRepository(featureServiceClient, rxFeatureDao, featureCollectionMapper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureDataRepo mo10268get() {
        return provideInstance(this.module, this.featureServiceClientProvider, this.featureDaoProvider, this.mapperProvider);
    }
}
