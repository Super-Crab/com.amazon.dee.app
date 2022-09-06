package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_ProvidesRoutingServiceFactory implements Factory<RoutingService> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_ProvidesRoutingServiceFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_ProvidesRoutingServiceFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_ProvidesRoutingServiceFactory(enrollmentLibraryModule);
    }

    public static RoutingService provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyProvidesRoutingService(enrollmentLibraryModule);
    }

    public static RoutingService proxyProvidesRoutingService(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (RoutingService) Preconditions.checkNotNull(enrollmentLibraryModule.providesRoutingService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoutingService mo10268get() {
        return provideInstance(this.module);
    }
}
