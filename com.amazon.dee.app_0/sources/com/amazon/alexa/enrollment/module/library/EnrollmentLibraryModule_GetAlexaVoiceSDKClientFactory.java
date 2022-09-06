package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.enrollment.voiceSDK.client.AlexaVoiceSDKClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_GetAlexaVoiceSDKClientFactory implements Factory<AlexaVoiceSDKClient> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_GetAlexaVoiceSDKClientFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_GetAlexaVoiceSDKClientFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_GetAlexaVoiceSDKClientFactory(enrollmentLibraryModule);
    }

    public static AlexaVoiceSDKClient provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyGetAlexaVoiceSDKClient(enrollmentLibraryModule);
    }

    public static AlexaVoiceSDKClient proxyGetAlexaVoiceSDKClient(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (AlexaVoiceSDKClient) Preconditions.checkNotNull(enrollmentLibraryModule.getAlexaVoiceSDKClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaVoiceSDKClient mo10268get() {
        return provideInstance(this.module);
    }
}
