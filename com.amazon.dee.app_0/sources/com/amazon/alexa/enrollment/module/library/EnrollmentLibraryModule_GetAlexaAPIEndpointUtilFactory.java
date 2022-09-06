package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.utils.AlexaAPIEndpointUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetAlexaAPIEndpointUtilFactory implements Factory<AlexaAPIEndpointUtil> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetAlexaAPIEndpointUtilFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetAlexaAPIEndpointUtilFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetAlexaAPIEndpointUtilFactory(enrollmentLibraryModule);
    }

    public static AlexaAPIEndpointUtil provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetAlexaAPIEndpointUtil(enrollmentLibraryModule);
    }

    public static AlexaAPIEndpointUtil proxyGetAlexaAPIEndpointUtil(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (AlexaAPIEndpointUtil) Preconditions.checkNotNull(enrollmentLibraryModule.getAlexaAPIEndpointUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaAPIEndpointUtil mo10268get() {
        return provideInstance(this.module);
    }
}
