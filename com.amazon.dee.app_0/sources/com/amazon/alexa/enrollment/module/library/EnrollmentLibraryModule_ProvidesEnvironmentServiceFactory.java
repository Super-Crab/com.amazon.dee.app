package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_ProvidesEnvironmentServiceFactory implements Factory<EnvironmentService> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_ProvidesEnvironmentServiceFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_ProvidesEnvironmentServiceFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_ProvidesEnvironmentServiceFactory(enrollmentLibraryModule);
    }

    public static EnvironmentService provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyProvidesEnvironmentService(enrollmentLibraryModule);
    }

    public static EnvironmentService proxyProvidesEnvironmentService(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnvironmentService) Preconditions.checkNotNull(enrollmentLibraryModule.providesEnvironmentService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnvironmentService mo10268get() {
        return provideInstance(this.module);
    }
}
