package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.alexaservices.AlexaStateTracker;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetAlexaStateTrackerFactory implements Factory<AlexaStateTracker> {
    private final Provider<EnrollmentMetricsRecorder> metricsRecorderProvider;
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetAlexaStateTrackerFactory(EnrollmentLibraryModule enrollmentLibraryModule, Provider<EnrollmentMetricsRecorder> provider) {
        this.module = enrollmentLibraryModule;
        this.metricsRecorderProvider = provider;
    }

    public static EnrollmentLibraryModule_GetAlexaStateTrackerFactory create(EnrollmentLibraryModule enrollmentLibraryModule, Provider<EnrollmentMetricsRecorder> provider) {
        return new EnrollmentLibraryModule_GetAlexaStateTrackerFactory(enrollmentLibraryModule, provider);
    }

    public static AlexaStateTracker provideInstance(EnrollmentLibraryModule enrollmentLibraryModule, Provider<EnrollmentMetricsRecorder> provider) {
        return proxyGetAlexaStateTracker(enrollmentLibraryModule, provider.mo10268get());
    }

    public static AlexaStateTracker proxyGetAlexaStateTracker(EnrollmentLibraryModule enrollmentLibraryModule, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return (AlexaStateTracker) Preconditions.checkNotNull(enrollmentLibraryModule.getAlexaStateTracker(enrollmentMetricsRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaStateTracker mo10268get() {
        return provideInstance(this.module, this.metricsRecorderProvider);
    }
}
