package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioSinkWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetEnrollmentAudioSinkWrapperFactory implements Factory<EnrollmentAudioSinkWrapper> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetEnrollmentAudioSinkWrapperFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetEnrollmentAudioSinkWrapperFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetEnrollmentAudioSinkWrapperFactory(enrollmentLibraryModule);
    }

    public static EnrollmentAudioSinkWrapper provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetEnrollmentAudioSinkWrapper(enrollmentLibraryModule);
    }

    public static EnrollmentAudioSinkWrapper proxyGetEnrollmentAudioSinkWrapper(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentAudioSinkWrapper) Preconditions.checkNotNull(enrollmentLibraryModule.getEnrollmentAudioSinkWrapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentAudioSinkWrapper mo10268get() {
        return provideInstance(this.module);
    }
}
