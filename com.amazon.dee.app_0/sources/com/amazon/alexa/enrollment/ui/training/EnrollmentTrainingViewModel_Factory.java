package com.amazon.alexa.enrollment.ui.training;

import android.content.Context;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentTrainingViewModel_Factory implements Factory<EnrollmentTrainingViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentAPI> enrollmentAPIProvider;
    private final Provider<EnrollmentMetricsRecorder> metricsRecorderProvider;

    public EnrollmentTrainingViewModel_Factory(Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        this.contextProvider = provider;
        this.enrollmentAPIProvider = provider2;
        this.metricsRecorderProvider = provider3;
    }

    public static EnrollmentTrainingViewModel_Factory create(Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        return new EnrollmentTrainingViewModel_Factory(provider, provider2, provider3);
    }

    public static EnrollmentTrainingViewModel newEnrollmentTrainingViewModel(Context context, EnrollmentAPI enrollmentAPI, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return new EnrollmentTrainingViewModel(context, enrollmentAPI, enrollmentMetricsRecorder);
    }

    public static EnrollmentTrainingViewModel provideInstance(Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        return new EnrollmentTrainingViewModel(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentTrainingViewModel mo10268get() {
        return provideInstance(this.contextProvider, this.enrollmentAPIProvider, this.metricsRecorderProvider);
    }
}
