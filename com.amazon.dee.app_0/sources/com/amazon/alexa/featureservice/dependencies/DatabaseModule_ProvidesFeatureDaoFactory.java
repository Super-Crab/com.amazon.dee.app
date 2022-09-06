package com.amazon.alexa.featureservice.dependencies;

import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao;
import com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class DatabaseModule_ProvidesFeatureDaoFactory implements Factory<FeatureFlagDao> {
    private final Provider<FeatureAppDatabase> dbProvider;
    private final DatabaseModule module;

    public DatabaseModule_ProvidesFeatureDaoFactory(DatabaseModule databaseModule, Provider<FeatureAppDatabase> provider) {
        this.module = databaseModule;
        this.dbProvider = provider;
    }

    public static DatabaseModule_ProvidesFeatureDaoFactory create(DatabaseModule databaseModule, Provider<FeatureAppDatabase> provider) {
        return new DatabaseModule_ProvidesFeatureDaoFactory(databaseModule, provider);
    }

    public static FeatureFlagDao provideInstance(DatabaseModule databaseModule, Provider<FeatureAppDatabase> provider) {
        return proxyProvidesFeatureDao(databaseModule, provider.mo10268get());
    }

    public static FeatureFlagDao proxyProvidesFeatureDao(DatabaseModule databaseModule, FeatureAppDatabase featureAppDatabase) {
        return (FeatureFlagDao) Preconditions.checkNotNull(databaseModule.providesFeatureDao(featureAppDatabase), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FeatureFlagDao mo10268get() {
        return provideInstance(this.module, this.dbProvider);
    }
}
