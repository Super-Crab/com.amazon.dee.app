package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory implements Factory<EnrollmentMetricsRecorder> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetEnrollmentMetricsRecorderFactory(enrollmentLibraryModule);
    }

    public static EnrollmentMetricsRecorder provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetEnrollmentMetricsRecorder(enrollmentLibraryModule);
    }

    public static EnrollmentMetricsRecorder proxyGetEnrollmentMetricsRecorder(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentMetricsRecorder) Preconditions.checkNotNull(enrollmentLibraryModule.getEnrollmentMetricsRecorder(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentMetricsRecorder mo10268get() {
        return provideInstance(this.module);
    }
}
