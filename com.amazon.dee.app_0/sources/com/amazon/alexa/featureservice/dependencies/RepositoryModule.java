package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.featureservice.repo.mapper.FeatureCollectionMapper;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo;
import com.amazon.alexa.featureservice.repo.provider.FeatureDataRepoImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class RepositoryModule {
    @Provides
    @Singleton
    public FeatureDataRepo providesFeatureRepository(FeatureServiceClient featureServiceClient, RxFeatureDao rxFeatureDao, FeatureCollectionMapper featureCollectionMapper) {
        return new FeatureDataRepoImpl(featureServiceClient, rxFeatureDao, featureCollectionMapper);
    }
}
