package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_ProvidesEnrollmentServiceFactory implements Factory<EnrollmentGateway> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_ProvidesEnrollmentServiceFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_ProvidesEnrollmentServiceFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_ProvidesEnrollmentServiceFactory(enrollmentLibraryModule);
    }

    public static EnrollmentGateway provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyProvidesEnrollmentService(enrollmentLibraryModule);
    }

    public static EnrollmentGateway proxyProvidesEnrollmentService(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentGateway) Preconditions.checkNotNull(enrollmentLibraryModule.providesEnrollmentService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentGateway mo10268get() {
        return provideInstance(this.module);
    }
}
