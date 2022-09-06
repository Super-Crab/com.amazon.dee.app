package com.amazon.alexa.fitness.service.afits;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.repository.SessionSummaryCache;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class WorkoutSessionDeleteHandler_Factory implements Factory<WorkoutSessionDeleteHandler> {
    private final Provider<ILog> logProvider;
    private final Provider<SampleStore> sampleStoreProvider;
    private final Provider<SessionSummaryCache> sessionSummaryCacheProvider;

    public WorkoutSessionDeleteHandler_Factory(Provider<SessionSummaryCache> provider, Provider<SampleStore> provider2, Provider<ILog> provider3) {
        this.sessionSummaryCacheProvider = provider;
        this.sampleStoreProvider = provider2;
        this.logProvider = provider3;
    }

    public static WorkoutSessionDeleteHandler_Factory create(Provider<SessionSummaryCache> provider, Provider<SampleStore> provider2, Provider<ILog> provider3) {
        return new WorkoutSessionDeleteHandler_Factory(provider, provider2, provider3);
    }

    public static WorkoutSessionDeleteHandler newWorkoutSessionDeleteHandler(SessionSummaryCache sessionSummaryCache, SampleStore sampleStore, ILog iLog) {
        return new WorkoutSessionDeleteHandler(sessionSummaryCache, sampleStore, iLog);
    }

    public static WorkoutSessionDeleteHandler provideInstance(Provider<SessionSummaryCache> provider, Provider<SampleStore> provider2, Provider<ILog> provider3) {
        return new WorkoutSessionDeleteHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkoutSessionDeleteHandler mo10268get() {
        return provideInstance(this.sessionSummaryCacheProvider, this.sampleStoreProvider, this.logProvider);
    }
}
