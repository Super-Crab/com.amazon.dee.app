package com.amazon.alexa.enrollment.voiceSDK.userSpeechProviders;

import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.voiceSDK.audio.EnrollmentAudioSinkWrapper;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.EnrollmentEventBus;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentAudioRecorderWrapper;
import com.amazon.alexa.enrollment.voiceSDK.util.EnrollmentHandlerUtil;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentUserSpeechProvider_Factory implements Factory<EnrollmentUserSpeechProvider> {
    private final Provider<EnrollmentAudioRecorderWrapper> audioRecorderWrapperProvider;
    private final Provider<EnrollmentAudioSinkWrapper> enrollmentAudioSinkWrapperProvider;
    private final Provider<EnrollmentEventBus> enrollmentEventBusProvider;
    private final Provider<EnrollmentHandlerUtil> enrollmentHandlerUtilProvider;
    private final Provider<EnrollmentMetricsRecorder> enrollmentMetricsRecorderProvider;
    private final Provider<ExecutorService> executorServiceProvider;

    public EnrollmentUserSpeechProvider_Factory(Provider<EnrollmentEventBus> provider, Provider<ExecutorService> provider2, Provider<EnrollmentAudioSinkWrapper> provider3, Provider<EnrollmentHandlerUtil> provider4, Provider<EnrollmentAudioRecorderWrapper> provider5, Provider<EnrollmentMetricsRecorder> provider6) {
        this.enrollmentEventBusProvider = provider;
        this.executorServiceProvider = provider2;
        this.enrollmentAudioSinkWrapperProvider = provider3;
        this.enrollmentHandlerUtilProvider = provider4;
        this.audioRecorderWrapperProvider = provider5;
        this.enrollmentMetricsRecorderProvider = provider6;
    }

    public static EnrollmentUserSpeechProvider_Factory create(Provider<EnrollmentEventBus> provider, Provider<ExecutorService> provider2, Provider<EnrollmentAudioSinkWrapper> provider3, Provider<EnrollmentHandlerUtil> provider4, Provider<EnrollmentAudioRecorderWrapper> provider5, Provider<EnrollmentMetricsRecorder> provider6) {
        return new EnrollmentUserSpeechProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static EnrollmentUserSpeechProvider newEnrollmentUserSpeechProvider(EnrollmentEventBus enrollmentEventBus, ExecutorService executorService, EnrollmentAudioSinkWrapper enrollmentAudioSinkWrapper, EnrollmentHandlerUtil enrollmentHandlerUtil, EnrollmentAudioRecorderWrapper enrollmentAudioRecorderWrapper, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return new EnrollmentUserSpeechProvider(enrollmentEventBus, executorService, enrollmentAudioSinkWrapper, enrollmentHandlerUtil, enrollmentAudioRecorderWrapper, enrollmentMetricsRecorder);
    }

    public static EnrollmentUserSpeechProvider provideInstance(Provider<EnrollmentEventBus> provider, Provider<ExecutorService> provider2, Provider<EnrollmentAudioSinkWrapper> provider3, Provider<EnrollmentHandlerUtil> provider4, Provider<EnrollmentAudioRecorderWrapper> provider5, Provider<EnrollmentMetricsRecorder> provider6) {
        return new EnrollmentUserSpeechProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentUserSpeechProvider mo10268get() {
        return provideInstance(this.enrollmentEventBusProvider, this.executorServiceProvider, this.enrollmentAudioSinkWrapperProvider, this.enrollmentHandlerUtilProvider, this.audioRecorderWrapperProvider, this.enrollmentMetricsRecorderProvider);
    }
}
