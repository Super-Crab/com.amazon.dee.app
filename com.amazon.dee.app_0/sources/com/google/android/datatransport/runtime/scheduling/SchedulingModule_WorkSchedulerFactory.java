package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {
    private final Provider<Clock> clockProvider;
    private final Provider<SchedulerConfig> configProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(Provider<Context> provider, Provider<EventStore> provider2, Provider<SchedulerConfig> provider3, Provider<Clock> provider4) {
        this.contextProvider = provider;
        this.eventStoreProvider = provider2;
        this.configProvider = provider3;
        this.clockProvider = provider4;
    }

    public static SchedulingModule_WorkSchedulerFactory create(Provider<Context> provider, Provider<EventStore> provider2, Provider<SchedulerConfig> provider3, Provider<Clock> provider4) {
        return new SchedulingModule_WorkSchedulerFactory(provider, provider2, provider3, provider4);
    }

    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig schedulerConfig, Clock clock) {
        return (WorkScheduler) Preconditions.checkNotNull(SchedulingModule.workScheduler(context, eventStore, schedulerConfig, clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkScheduler mo10268get() {
        return workScheduler(this.contextProvider.mo10268get(), this.eventStoreProvider.mo10268get(), this.configProvider.mo10268get(), this.clockProvider.mo10268get());
    }
}
