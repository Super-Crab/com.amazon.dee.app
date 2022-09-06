package com.amazon.alexa.enrollment.alexaservices;

import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AlexaStateTracker_Factory implements Factory<AlexaStateTracker> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<AlexaState> initialAlexaStateProvider;
    private final Provider<EnrollmentMetricsRecorder> metricsRecorderProvider;

    public AlexaStateTracker_Factory(Provider<EventBus> provider, Provider<EnrollmentMetricsRecorder> provider2, Provider<AlexaState> provider3) {
        this.eventBusProvider = provider;
        this.metricsRecorderProvider = provider2;
        this.initialAlexaStateProvider = provider3;
    }

    public static AlexaStateTracker_Factory create(Provider<EventBus> provider, Provider<EnrollmentMetricsRecorder> provider2, Provider<AlexaState> provider3) {
        return new AlexaStateTracker_Factory(provider, provider2, provider3);
    }

    public static AlexaStateTracker newAlexaStateTracker(EventBus eventBus, EnrollmentMetricsRecorder enrollmentMetricsRecorder, AlexaState alexaState) {
        return new AlexaStateTracker(eventBus, enrollmentMetricsRecorder, alexaState);
    }

    public static AlexaStateTracker provideInstance(Provider<EventBus> provider, Provider<EnrollmentMetricsRecorder> provider2, Provider<AlexaState> provider3) {
        return new AlexaStateTracker(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaStateTracker mo10268get() {
        return provideInstance(this.eventBusProvider, this.metricsRecorderProvider, this.initialAlexaStateProvider);
    }
}
