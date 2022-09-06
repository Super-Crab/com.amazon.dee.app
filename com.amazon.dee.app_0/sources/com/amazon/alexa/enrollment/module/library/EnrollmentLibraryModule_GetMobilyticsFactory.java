package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetMobilyticsFactory implements Factory<Mobilytics> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetMobilyticsFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetMobilyticsFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetMobilyticsFactory(enrollmentLibraryModule);
    }

    public static Mobilytics provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetMobilytics(enrollmentLibraryModule);
    }

    public static Mobilytics proxyGetMobilytics(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (Mobilytics) Preconditions.checkNotNull(enrollmentLibraryModule.getMobilytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module);
    }
}
