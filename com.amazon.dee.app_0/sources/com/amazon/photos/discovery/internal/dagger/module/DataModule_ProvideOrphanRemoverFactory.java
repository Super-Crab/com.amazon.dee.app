package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DataModule_ProvideOrphanRemoverFactory implements Factory<OrphanRemover> {
    private final Provider<Metrics> metricsProvider;
    private final DataModule module;
    private final Provider<WorkerDao> workerDaoProvider;

    public DataModule_ProvideOrphanRemoverFactory(DataModule dataModule, Provider<WorkerDao> provider, Provider<Metrics> provider2) {
        this.module = dataModule;
        this.workerDaoProvider = provider;
        this.metricsProvider = provider2;
    }

    public static DataModule_ProvideOrphanRemoverFactory create(DataModule dataModule, Provider<WorkerDao> provider, Provider<Metrics> provider2) {
        return new DataModule_ProvideOrphanRemoverFactory(dataModule, provider, provider2);
    }

    public static OrphanRemover provideOrphanRemover(DataModule dataModule, WorkerDao workerDao, Metrics metrics) {
        return (OrphanRemover) Preconditions.checkNotNull(dataModule.provideOrphanRemover(workerDao, metrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OrphanRemover mo10268get() {
        return provideOrphanRemover(this.module, this.workerDaoProvider.mo10268get(), this.metricsProvider.mo10268get());
    }
}
