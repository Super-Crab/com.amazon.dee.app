package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetEnrollmentUserSpeechProviderFactory implements Factory<EnrollmentUserSpeechProvider> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetEnrollmentUserSpeechProviderFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetEnrollmentUserSpeechProviderFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetEnrollmentUserSpeechProviderFactory(enrollmentLibraryModule);
    }

    public static EnrollmentUserSpeechProvider provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetEnrollmentUserSpeechProvider(enrollmentLibraryModule);
    }

    public static EnrollmentUserSpeechProvider proxyGetEnrollmentUserSpeechProvider(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentUserSpeechProvider) Preconditions.checkNotNull(enrollmentLibraryModule.getEnrollmentUserSpeechProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentUserSpeechProvider mo10268get() {
        return provideInstance(this.module);
    }
}
