package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.dao.LocalFolderDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideLocalFolderDaoFactory implements Factory<LocalFolderDao> {
    private final DataModule module;

    public DataModule_ProvideLocalFolderDaoFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideLocalFolderDaoFactory create(DataModule dataModule) {
        return new DataModule_ProvideLocalFolderDaoFactory(dataModule);
    }

    public static LocalFolderDao provideLocalFolderDao(DataModule dataModule) {
        return (LocalFolderDao) Preconditions.checkNotNull(dataModule.provideLocalFolderDao(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocalFolderDao mo10268get() {
        return provideLocalFolderDao(this.module);
    }
}
