package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentAudioRecorderWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetAudioRecorderWrapperFactory implements Factory<EnrollmentAudioRecorderWrapper> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetAudioRecorderWrapperFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetAudioRecorderWrapperFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetAudioRecorderWrapperFactory(enrollmentLibraryModule);
    }

    public static EnrollmentAudioRecorderWrapper provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetAudioRecorderWrapper(enrollmentLibraryModule);
    }

    public static EnrollmentAudioRecorderWrapper proxyGetAudioRecorderWrapper(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EnrollmentAudioRecorderWrapper) Preconditions.checkNotNull(enrollmentLibraryModule.getAudioRecorderWrapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentAudioRecorderWrapper mo10268get() {
        return provideInstance(this.module);
    }
}
