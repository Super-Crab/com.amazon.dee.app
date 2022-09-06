package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesPermissionsCheckerFactory implements Factory<AndroidPermissionsChecker> {
    private final Provider<DefaultAndroidPermissionsHandler> defaultAndroidPermissionsHandlerProvider;
    private final VoiceModule module;

    public VoiceModule_ProvidesPermissionsCheckerFactory(VoiceModule voiceModule, Provider<DefaultAndroidPermissionsHandler> provider) {
        this.module = voiceModule;
        this.defaultAndroidPermissionsHandlerProvider = provider;
    }

    public static VoiceModule_ProvidesPermissionsCheckerFactory create(VoiceModule voiceModule, Provider<DefaultAndroidPermissionsHandler> provider) {
        return new VoiceModule_ProvidesPermissionsCheckerFactory(voiceModule, provider);
    }

    public static AndroidPermissionsChecker provideInstance(VoiceModule voiceModule, Provider<DefaultAndroidPermissionsHandler> provider) {
        return proxyProvidesPermissionsChecker(voiceModule, provider.mo10268get());
    }

    public static AndroidPermissionsChecker proxyProvidesPermissionsChecker(VoiceModule voiceModule, Object obj) {
        return (AndroidPermissionsChecker) Preconditions.checkNotNull(voiceModule.providesPermissionsChecker((DefaultAndroidPermissionsHandler) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AndroidPermissionsChecker mo10268get() {
        return provideInstance(this.module, this.defaultAndroidPermissionsHandlerProvider);
    }
}
