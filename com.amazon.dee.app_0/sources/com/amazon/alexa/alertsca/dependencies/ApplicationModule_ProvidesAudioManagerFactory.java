package com.amazon.alexa.alertsca.dependencies;

import android.media.AudioManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesAudioManagerFactory implements Factory<AudioManager> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAudioManagerFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvidesAudioManagerFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesAudioManagerFactory(applicationModule);
    }

    public static AudioManager provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesAudioManager(applicationModule);
    }

    public static AudioManager proxyProvidesAudioManager(ApplicationModule applicationModule) {
        return (AudioManager) Preconditions.checkNotNull(applicationModule.providesAudioManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioManager mo10268get() {
        return provideInstance(this.module);
    }
}
