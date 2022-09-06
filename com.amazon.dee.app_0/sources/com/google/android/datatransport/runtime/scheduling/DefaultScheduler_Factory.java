package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class DefaultScheduler_Factory implements Factory<DefaultScheduler> {
    private final Provider<BackendRegistry> backendRegistryProvider;
    private final Provider<EventStore> eventStoreProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> workSchedulerProvider;

    public DefaultScheduler_Factory(Provider<Executor> provider, Provider<BackendRegistry> provider2, Provider<WorkScheduler> provider3, Provider<EventStore> provider4, Provider<SynchronizationGuard> provider5) {
        this.executorProvider = provider;
        this.backendRegistryProvider = provider2;
        this.workSchedulerProvider = provider3;
        this.eventStoreProvider = provider4;
        this.guardProvider = provider5;
    }

    public static DefaultScheduler_Factory create(Provider<Executor> provider, Provider<BackendRegistry> provider2, Provider<WorkScheduler> provider3, Provider<EventStore> provider4, Provider<SynchronizationGuard> provider5) {
        return new DefaultScheduler_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static DefaultScheduler newInstance(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard synchronizationGuard) {
        return new DefaultScheduler(executor, backendRegistry, workScheduler, eventStore, synchronizationGuard);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultScheduler mo10268get() {
        return new DefaultScheduler(this.executorProvider.mo10268get(), this.backendRegistryProvider.mo10268get(), this.workSchedulerProvider.mo10268get(), this.eventStoreProvider.mo10268get(), this.guardProvider.mo10268get());
    }
}
