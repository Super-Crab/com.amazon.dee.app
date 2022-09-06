package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.dao.UnifiedItemDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideUnifiedItemDaoFactory implements Factory<UnifiedItemDao> {
    private final DataModule module;

    public DataModule_ProvideUnifiedItemDaoFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideUnifiedItemDaoFactory create(DataModule dataModule) {
        return new DataModule_ProvideUnifiedItemDaoFactory(dataModule);
    }

    public static UnifiedItemDao provideUnifiedItemDao(DataModule dataModule) {
        return (UnifiedItemDao) Preconditions.checkNotNull(dataModule.provideUnifiedItemDao(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UnifiedItemDao mo10268get() {
        return provideUnifiedItemDao(this.module);
    }
}
