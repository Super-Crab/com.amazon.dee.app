package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_ProvidesIdentityServiceFactory implements Factory<IdentityService> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_ProvidesIdentityServiceFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_ProvidesIdentityServiceFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_ProvidesIdentityServiceFactory(enrollmentLibraryModule);
    }

    public static IdentityService provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyProvidesIdentityService(enrollmentLibraryModule);
    }

    public static IdentityService proxyProvidesIdentityService(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (IdentityService) Preconditions.checkNotNull(enrollmentLibraryModule.providesIdentityService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module);
    }
}
