package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesPermissionsRequesterFactory implements Factory<AndroidPermissionsRequester> {
    private final Provider<DefaultAndroidPermissionsHandler> defaultAndroidPermissionsHandlerProvider;
    private final VoiceModule module;

    public VoiceModule_ProvidesPermissionsRequesterFactory(VoiceModule voiceModule, Provider<DefaultAndroidPermissionsHandler> provider) {
        this.module = voiceModule;
        this.defaultAndroidPermissionsHandlerProvider = provider;
    }

    public static VoiceModule_ProvidesPermissionsRequesterFactory create(VoiceModule voiceModule, Provider<DefaultAndroidPermissionsHandler> provider) {
        return new VoiceModule_ProvidesPermissionsRequesterFactory(voiceModule, provider);
    }

    public static AndroidPermissionsRequester provideInstance(VoiceModule voiceModule, Provider<DefaultAndroidPermissionsHandler> provider) {
        return proxyProvidesPermissionsRequester(voiceModule, provider.mo10268get());
    }

    public static AndroidPermissionsRequester proxyProvidesPermissionsRequester(VoiceModule voiceModule, Object obj) {
        return (AndroidPermissionsRequester) Preconditions.checkNotNull(voiceModule.providesPermissionsRequester((DefaultAndroidPermissionsHandler) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AndroidPermissionsRequester mo10268get() {
        return provideInstance(this.module, this.defaultAndroidPermissionsHandlerProvider);
    }
}
