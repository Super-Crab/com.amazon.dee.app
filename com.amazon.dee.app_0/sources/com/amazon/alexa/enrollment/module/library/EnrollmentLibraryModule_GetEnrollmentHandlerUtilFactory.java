package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentHandlerUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetEnrollmentHandlerUtilFactory implements Factory<EnrollmentHandlerUtil> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetEnrollmentHandlerUtilFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetEnrollmentHandlerUtilFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetEnrollmentHandlerUtilFactory(enrollmentLibraryModule);
    }

    public static EnrollmentHandlerUtil provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetEnrollmentHandlerUtil(enrollmentLibraryModule);
    }

    public static EnrollmentHandlerUtil proxyGetEnrollmentHandlerUtil(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentHandlerUtil) Preconditions.checkNotNull(enrollmentLibraryModule.getEnrollmentHandlerUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentHandlerUtil mo10268get() {
        return provideInstance(this.module);
    }
}
