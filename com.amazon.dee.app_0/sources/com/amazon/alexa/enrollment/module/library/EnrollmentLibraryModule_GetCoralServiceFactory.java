package com.amazon.alexa.enrollment.module.library;

import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetCoralServiceFactory implements Factory<CoralService> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetCoralServiceFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetCoralServiceFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetCoralServiceFactory(enrollmentLibraryModule);
    }

    public static CoralService provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetCoralService(enrollmentLibraryModule);
    }

    public static CoralService proxyGetCoralService(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (CoralService) Preconditions.checkNotNull(enrollmentLibraryModule.getCoralService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CoralService mo10268get() {
        return provideInstance(this.module);
    }
}
