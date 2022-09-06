package com.amazon.deecomms.media.audio;

import android.media.AudioManager;
import com.amazon.deecomms.media.VideoStateController;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallMediaControlFacade_Factory implements Factory<CallMediaControlFacade> {
    private final Provider<AudioInputController> audioInputControllerProvider;
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<AudioOutputController> audioStateChangeEventProvider;
    private final Provider<VideoStateController> videoStateControllerProvider;

    public CallMediaControlFacade_Factory(Provider<AudioOutputController> provider, Provider<AudioInputController> provider2, Provider<VideoStateController> provider3, Provider<AudioManager> provider4) {
        this.audioStateChangeEventProvider = provider;
        this.audioInputControllerProvider = provider2;
        this.videoStateControllerProvider = provider3;
        this.audioManagerProvider = provider4;
    }

    public static CallMediaControlFacade_Factory create(Provider<AudioOutputController> provider, Provider<AudioInputController> provider2, Provider<VideoStateController> provider3, Provider<AudioManager> provider4) {
        return new CallMediaControlFacade_Factory(provider, provider2, provider3, provider4);
    }

    public static CallMediaControlFacade newCallMediaControlFacade(AudioOutputController audioOutputController, AudioInputController audioInputController, VideoStateController videoStateController, AudioManager audioManager) {
        return new CallMediaControlFacade(audioOutputController, audioInputController, videoStateController, audioManager);
    }

    public static CallMediaControlFacade provideInstance(Provider<AudioOutputController> provider, Provider<AudioInputController> provider2, Provider<VideoStateController> provider3, Provider<AudioManager> provider4) {
        return new CallMediaControlFacade(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallMediaControlFacade mo10268get() {
        return provideInstance(this.audioStateChangeEventProvider, this.audioInputControllerProvider, this.videoStateControllerProvider, this.audioManagerProvider);
    }
}
