package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao;
import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DatabaseModule_ProvidesRxFeatureDaoFactory implements Factory<RxFeatureDao> {
    private final Provider<FeatureFlagDao> featureDaoProvider;
    private final DatabaseModule module;

    public DatabaseModule_ProvidesRxFeatureDaoFactory(DatabaseModule databaseModule, Provider<FeatureFlagDao> provider) {
        this.module = databaseModule;
        this.featureDaoProvider = provider;
    }

    public static DatabaseModule_ProvidesRxFeatureDaoFactory create(DatabaseModule databaseModule, Provider<FeatureFlagDao> provider) {
        return new DatabaseModule_ProvidesRxFeatureDaoFactory(databaseModule, provider);
    }

    public static RxFeatureDao provideInstance(DatabaseModule databaseModule, Provider<FeatureFlagDao> provider) {
        return proxyProvidesRxFeatureDao(databaseModule, provider.mo10268get());
    }

    public static RxFeatureDao proxyProvidesRxFeatureDao(DatabaseModule databaseModule, FeatureFlagDao featureFlagDao) {
        return (RxFeatureDao) Preconditions.checkNotNull(databaseModule.providesRxFeatureDao(featureFlagDao), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RxFeatureDao mo10268get() {
        return provideInstance(this.module, this.featureDaoProvider);
    }
}
