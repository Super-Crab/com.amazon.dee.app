package com.amazon.alexa.alertsca.exoplayer;

import dagger.internal.Factory;
/* loaded from: classes6.dex */
public final class AmplitudeCalculatingAudioSink_Factory implements Factory<AmplitudeCalculatingAudioSink> {
    private static final AmplitudeCalculatingAudioSink_Factory INSTANCE = new AmplitudeCalculatingAudioSink_Factory();

    public static AmplitudeCalculatingAudioSink_Factory create() {
        return INSTANCE;
    }

    public static AmplitudeCalculatingAudioSink newAmplitudeCalculatingAudioSink() {
        return new AmplitudeCalculatingAudioSink();
    }

    public static AmplitudeCalculatingAudioSink provideInstance() {
        return new AmplitudeCalculatingAudioSink();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AmplitudeCalculatingAudioSink mo10268get() {
        return provideInstance();
    }
}
