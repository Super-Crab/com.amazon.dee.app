package com.amazon.deecomms.core;

import android.content.Context;
import android.media.AudioManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesAudioManagerFactory implements Factory<AudioManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesAudioManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesAudioManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesAudioManagerFactory(androidModule, provider);
    }

    public static AudioManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (AudioManager) Preconditions.checkNotNull(androidModule.providesAudioManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AudioManager proxyProvidesAudioManager(AndroidModule androidModule, Context context) {
        return (AudioManager) Preconditions.checkNotNull(androidModule.providesAudioManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AudioManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
