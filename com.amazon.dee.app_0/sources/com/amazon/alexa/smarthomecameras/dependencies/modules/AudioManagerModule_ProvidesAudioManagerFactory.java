package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AudioManagerModule_ProvidesAudioManagerFactory implements Factory<AudioManager> {
    private final Provider<CamerasAppClient> appClientProvider;
    private final Provider<Context> contextProvider;

    public AudioManagerModule_ProvidesAudioManagerFactory(Provider<Context> provider, Provider<CamerasAppClient> provider2) {
        this.contextProvider = provider;
        this.appClientProvider = provider2;
    }

    public static AudioManagerModule_ProvidesAudioManagerFactory create(Provider<Context> provider, Provider<CamerasAppClient> provider2) {
        return new AudioManagerModule_ProvidesAudioManagerFactory(provider, provider2);
    }

    public static AudioManager provideInstance(Provider<Context> provider, Provider<CamerasAppClient> provider2) {
        return proxyProvidesAudioManager(provider.mo10268get(), provider2.mo10268get());
    }

    public static AudioManager proxyProvidesAudioManager(Context context, CamerasAppClient camerasAppClient) {
        return (AudioManager) Preconditions.checkNotNull(AudioManagerModule.providesAudioManager(context, camerasAppClient), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioManager mo10268get() {
        return provideInstance(this.contextProvider, this.appClientProvider);
    }
}
