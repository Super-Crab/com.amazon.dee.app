package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.RouterDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesBackButtonPressHandlerFactory implements Factory<BackButtonPressHandler> {
    private final VoiceChromeModule module;
    private final Provider<RouterDelegate> routerDelegateProvider;

    public VoiceChromeModule_ProvidesBackButtonPressHandlerFactory(VoiceChromeModule voiceChromeModule, Provider<RouterDelegate> provider) {
        this.module = voiceChromeModule;
        this.routerDelegateProvider = provider;
    }

    public static VoiceChromeModule_ProvidesBackButtonPressHandlerFactory create(VoiceChromeModule voiceChromeModule, Provider<RouterDelegate> provider) {
        return new VoiceChromeModule_ProvidesBackButtonPressHandlerFactory(voiceChromeModule, provider);
    }

    public static BackButtonPressHandler provideInstance(VoiceChromeModule voiceChromeModule, Provider<RouterDelegate> provider) {
        return proxyProvidesBackButtonPressHandler(voiceChromeModule, provider.mo10268get());
    }

    public static BackButtonPressHandler proxyProvidesBackButtonPressHandler(VoiceChromeModule voiceChromeModule, RouterDelegate routerDelegate) {
        return (BackButtonPressHandler) Preconditions.checkNotNull(voiceChromeModule.providesBackButtonPressHandler(routerDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BackButtonPressHandler mo10268get() {
        return provideInstance(this.module, this.routerDelegateProvider);
    }
}
