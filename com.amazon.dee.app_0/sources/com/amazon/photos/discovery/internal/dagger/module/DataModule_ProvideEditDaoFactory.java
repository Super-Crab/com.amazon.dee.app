package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.dao.EditDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideEditDaoFactory implements Factory<EditDao> {
    private final DataModule module;

    public DataModule_ProvideEditDaoFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideEditDaoFactory create(DataModule dataModule) {
        return new DataModule_ProvideEditDaoFactory(dataModule);
    }

    public static EditDao provideEditDao(DataModule dataModule) {
        return (EditDao) Preconditions.checkNotNull(dataModule.provideEditDao(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EditDao mo10268get() {
        return provideEditDao(this.module);
    }
}
