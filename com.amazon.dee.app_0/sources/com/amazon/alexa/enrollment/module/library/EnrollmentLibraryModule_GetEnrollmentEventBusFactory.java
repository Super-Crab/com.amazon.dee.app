package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetEnrollmentEventBusFactory implements Factory<EnrollmentEventBus> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetEnrollmentEventBusFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetEnrollmentEventBusFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetEnrollmentEventBusFactory(enrollmentLibraryModule);
    }

    public static EnrollmentEventBus provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetEnrollmentEventBus(enrollmentLibraryModule);
    }

    public static EnrollmentEventBus proxyGetEnrollmentEventBus(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentEventBus) Preconditions.checkNotNull(enrollmentLibraryModule.getEnrollmentEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentEventBus mo10268get() {
        return provideInstance(this.module);
    }
}
