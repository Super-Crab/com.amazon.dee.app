package com.amazon.alexa.enrollment.alexaservices;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AlexaStateInteractor_Factory implements Factory<AlexaStateInteractor> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<AlexaStateTracker> alexaStateTrackerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentMetricsRecorder> metricsRecorderProvider;

    public AlexaStateInteractor_Factory(Provider<Context> provider, Provider<AlexaStateTracker> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<AlexaServicesConnection> provider4) {
        this.contextProvider = provider;
        this.alexaStateTrackerProvider = provider2;
        this.metricsRecorderProvider = provider3;
        this.alexaServicesConnectionProvider = provider4;
    }

    public static AlexaStateInteractor_Factory create(Provider<Context> provider, Provider<AlexaStateTracker> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<AlexaServicesConnection> provider4) {
        return new AlexaStateInteractor_Factory(provider, provider2, provider3, provider4);
    }

    public static AlexaStateInteractor newAlexaStateInteractor(Context context, AlexaStateTracker alexaStateTracker, EnrollmentMetricsRecorder enrollmentMetricsRecorder, AlexaServicesConnection alexaServicesConnection) {
        return new AlexaStateInteractor(context, alexaStateTracker, enrollmentMetricsRecorder, alexaServicesConnection);
    }

    public static AlexaStateInteractor provideInstance(Provider<Context> provider, Provider<AlexaStateTracker> provider2, Provider<EnrollmentMetricsRecorder> provider3, Provider<AlexaServicesConnection> provider4) {
        return new AlexaStateInteractor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaStateInteractor mo10268get() {
        return provideInstance(this.contextProvider, this.alexaStateTrackerProvider, this.metricsRecorderProvider, this.alexaServicesConnectionProvider);
    }
}
