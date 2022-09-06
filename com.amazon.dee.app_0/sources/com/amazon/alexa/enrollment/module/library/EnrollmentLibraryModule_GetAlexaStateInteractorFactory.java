package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.alexaservices.AlexaStateInteractor;
import com.amazon.alexa.enrollment.alexaservices.AlexaStateTracker;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetAlexaStateInteractorFactory implements Factory<AlexaStateInteractor> {
    private final Provider<AlexaStateTracker> alexaStateTrackerProvider;
    private final Provider<EnrollmentMetricsRecorder> metricsRecorderProvider;
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetAlexaStateInteractorFactory(EnrollmentLibraryModule enrollmentLibraryModule, Provider<AlexaStateTracker> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        this.module = enrollmentLibraryModule;
        this.alexaStateTrackerProvider = provider;
        this.metricsRecorderProvider = provider2;
    }

    public static EnrollmentLibraryModule_GetAlexaStateInteractorFactory create(EnrollmentLibraryModule enrollmentLibraryModule, Provider<AlexaStateTracker> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        return new EnrollmentLibraryModule_GetAlexaStateInteractorFactory(enrollmentLibraryModule, provider, provider2);
    }

    public static AlexaStateInteractor provideInstance(EnrollmentLibraryModule enrollmentLibraryModule, Provider<AlexaStateTracker> provider, Provider<EnrollmentMetricsRecorder> provider2) {
        return proxyGetAlexaStateInteractor(enrollmentLibraryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static AlexaStateInteractor proxyGetAlexaStateInteractor(EnrollmentLibraryModule enrollmentLibraryModule, AlexaStateTracker alexaStateTracker, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return (AlexaStateInteractor) Preconditions.checkNotNull(enrollmentLibraryModule.getAlexaStateInteractor(alexaStateTracker, enrollmentMetricsRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaStateInteractor mo10268get() {
        return provideInstance(this.module, this.alexaStateTrackerProvider, this.metricsRecorderProvider);
    }
}
