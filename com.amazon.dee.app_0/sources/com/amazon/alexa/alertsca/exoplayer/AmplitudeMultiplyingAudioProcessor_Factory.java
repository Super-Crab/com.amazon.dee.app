package com.amazon.alexa.alertsca.exoplayer;

import dagger.internal.Factory;
/* loaded from: classes6.dex */
public final class AmplitudeMultiplyingAudioProcessor_Factory implements Factory<AmplitudeMultiplyingAudioProcessor> {
    private static final AmplitudeMultiplyingAudioProcessor_Factory INSTANCE = new AmplitudeMultiplyingAudioProcessor_Factory();

    public static AmplitudeMultiplyingAudioProcessor_Factory create() {
        return INSTANCE;
    }

    public static AmplitudeMultiplyingAudioProcessor newAmplitudeMultiplyingAudioProcessor() {
        return new AmplitudeMultiplyingAudioProcessor();
    }

    public static AmplitudeMultiplyingAudioProcessor provideInstance() {
        return new AmplitudeMultiplyingAudioProcessor();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AmplitudeMultiplyingAudioProcessor mo10268get() {
        return provideInstance();
    }
}
