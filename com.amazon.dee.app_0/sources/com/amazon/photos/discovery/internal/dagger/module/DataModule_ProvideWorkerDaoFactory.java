package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.photos.discovery.internal.dao.WorkerDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DataModule_ProvideWorkerDaoFactory implements Factory<WorkerDao> {
    private final DataModule module;

    public DataModule_ProvideWorkerDaoFactory(DataModule dataModule) {
        this.module = dataModule;
    }

    public static DataModule_ProvideWorkerDaoFactory create(DataModule dataModule) {
        return new DataModule_ProvideWorkerDaoFactory(dataModule);
    }

    public static WorkerDao provideWorkerDao(DataModule dataModule) {
        return (WorkerDao) Preconditions.checkNotNull(dataModule.provideWorkerDao(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkerDao mo10268get() {
        return provideWorkerDao(this.module);
    }
}
