package com.amazon.alexa.alertsca;

import android.content.Context;
import android.media.AudioManager;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeCalculatingAudioSink;
import com.amazon.alexa.alertsca.exoplayer.AmplitudeMultiplyingAudioProcessor;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class VolumeAdjuster_Factory implements Factory<VolumeAdjuster> {
    private final Provider<AmplitudeCalculatingAudioSink> amplitudeCalculatingAudioSinkProvider;
    private final Provider<AmplitudeMultiplyingAudioProcessor> amplitudeMultiplyingAudioProcessorProvider;
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<Context> contextProvider;

    public VolumeAdjuster_Factory(Provider<Context> provider, Provider<AudioManager> provider2, Provider<AmplitudeMultiplyingAudioProcessor> provider3, Provider<AmplitudeCalculatingAudioSink> provider4) {
        this.contextProvider = provider;
        this.audioManagerProvider = provider2;
        this.amplitudeMultiplyingAudioProcessorProvider = provider3;
        this.amplitudeCalculatingAudioSinkProvider = provider4;
    }

    public static VolumeAdjuster_Factory create(Provider<Context> provider, Provider<AudioManager> provider2, Provider<AmplitudeMultiplyingAudioProcessor> provider3, Provider<AmplitudeCalculatingAudioSink> provider4) {
        return new VolumeAdjuster_Factory(provider, provider2, provider3, provider4);
    }

    public static VolumeAdjuster newVolumeAdjuster(Context context, AudioManager audioManager, AmplitudeMultiplyingAudioProcessor amplitudeMultiplyingAudioProcessor, AmplitudeCalculatingAudioSink amplitudeCalculatingAudioSink) {
        return new VolumeAdjuster(context, audioManager, amplitudeMultiplyingAudioProcessor, amplitudeCalculatingAudioSink);
    }

    public static VolumeAdjuster provideInstance(Provider<Context> provider, Provider<AudioManager> provider2, Provider<AmplitudeMultiplyingAudioProcessor> provider3, Provider<AmplitudeCalculatingAudioSink> provider4) {
        return new VolumeAdjuster(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VolumeAdjuster mo10268get() {
        return provideInstance(this.contextProvider, this.audioManagerProvider, this.amplitudeMultiplyingAudioProcessorProvider, this.amplitudeCalculatingAudioSinkProvider);
    }
}
