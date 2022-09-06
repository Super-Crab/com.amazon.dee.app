package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.dao.LocalItemDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideLocalItemDaoFactory implements Factory<LocalItemDao> {
    private final DataModule module;

    public DataModule_ProvideLocalItemDaoFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideLocalItemDaoFactory create(DataModule dataModule) {
        return new DataModule_ProvideLocalItemDaoFactory(dataModule);
    }

    public static LocalItemDao provideLocalItemDao(DataModule dataModule) {
        return (LocalItemDao) Preconditions.checkNotNull(dataModule.provideLocalItemDao(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocalItemDao mo10268get() {
        return provideLocalItemDao(this.module);
    }
}
