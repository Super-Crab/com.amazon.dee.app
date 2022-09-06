package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewModel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentViewModelModule_ProvideEnrollmentTrainingViewModelFactory implements Factory<EnrollmentTrainingViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentAPI> enrollmentAPIProvider;
    private final Provider<EnrollmentMetricsRecorder> metricsProvider;
    private final EnrollmentViewModelModule module;

    public EnrollmentViewModelModule_ProvideEnrollmentTrainingViewModelFactory(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        this.module = enrollmentViewModelModule;
        this.contextProvider = provider;
        this.enrollmentAPIProvider = provider2;
        this.metricsProvider = provider3;
    }

    public static EnrollmentViewModelModule_ProvideEnrollmentTrainingViewModelFactory create(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        return new EnrollmentViewModelModule_ProvideEnrollmentTrainingViewModelFactory(enrollmentViewModelModule, provider, provider2, provider3);
    }

    public static EnrollmentTrainingViewModel provideInstance(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<EnrollmentMetricsRecorder> provider3) {
        return proxyProvideEnrollmentTrainingViewModel(enrollmentViewModelModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static EnrollmentTrainingViewModel proxyProvideEnrollmentTrainingViewModel(EnrollmentViewModelModule enrollmentViewModelModule, Context context, EnrollmentAPI enrollmentAPI, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return (EnrollmentTrainingViewModel) Preconditions.checkNotNull(enrollmentViewModelModule.provideEnrollmentTrainingViewModel(context, enrollmentAPI, enrollmentMetricsRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentTrainingViewModel mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.enrollmentAPIProvider, this.metricsProvider);
    }
}
