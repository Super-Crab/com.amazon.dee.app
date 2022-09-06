package com.amazon.alexa.enrollment.voiceSDK.client;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders.EnrollmentUserSpeechProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class AlexaVoiceSDKClient_Factory implements Factory<AlexaVoiceSDKClient> {
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<EnrollmentUserSpeechProvider> enrollmentUserSpeechProvider;
    private final Provider<EnrollmentEventBus> eventBusProvider;

    public AlexaVoiceSDKClient_Factory(Provider<EnrollmentMetricsRecorder> provider, Provider<EnrollmentEventBus> provider2, Provider<EnrollmentUserSpeechProvider> provider3) {
        this.enrollmentMetricsRecorderProvider = provider;
        this.eventBusProvider = provider2;
        this.enrollmentUserSpeechProvider = provider3;
    }

    public static AlexaVoiceSDKClient_Factory create(Provider<EnrollmentMetricsRecorder> provider, Provider<EnrollmentEventBus> provider2, Provider<EnrollmentUserSpeechProvider> provider3) {
        return new AlexaVoiceSDKClient_Factory(provider, provider2, provider3);
    }

    public static AlexaVoiceSDKClient newAlexaVoiceSDKClient(EnrollmentMetricsRecorder enrollmentMetricsRecorder, EnrollmentEventBus enrollmentEventBus, EnrollmentUserSpeechProvider enrollmentUserSpeechProvider) {
        return new AlexaVoiceSDKClient(enrollmentMetricsRecorder, enrollmentEventBus, enrollmentUserSpeechProvider);
    }

    public static AlexaVoiceSDKClient provideInstance(Provider<EnrollmentMetricsRecorder> provider, Provider<EnrollmentEventBus> provider2, Provider<EnrollmentUserSpeechProvider> provider3) {
        return new AlexaVoiceSDKClient(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaVoiceSDKClient mo10268get() {
        return provideInstance(this.enrollmentMetricsRecorderProvider, this.eventBusProvider, this.enrollmentUserSpeechProvider);
    }
}
