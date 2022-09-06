package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesSaveInstanceStateHandlerFactory implements Factory<SaveInstanceStateHandler> {
    private final VoiceChromeModule module;
    private final Provider<RouterDelegate> routerDelegateProvider;

    public VoiceChromeModule_ProvidesSaveInstanceStateHandlerFactory(VoiceChromeModule voiceChromeModule, Provider<RouterDelegate> provider) {
        this.module = voiceChromeModule;
        this.routerDelegateProvider = provider;
    }

    public static VoiceChromeModule_ProvidesSaveInstanceStateHandlerFactory create(VoiceChromeModule voiceChromeModule, Provider<RouterDelegate> provider) {
        return new VoiceChromeModule_ProvidesSaveInstanceStateHandlerFactory(voiceChromeModule, provider);
    }

    public static SaveInstanceStateHandler provideInstance(VoiceChromeModule voiceChromeModule, Provider<RouterDelegate> provider) {
        return proxyProvidesSaveInstanceStateHandler(voiceChromeModule, provider.mo10268get());
    }

    public static SaveInstanceStateHandler proxyProvidesSaveInstanceStateHandler(VoiceChromeModule voiceChromeModule, RouterDelegate routerDelegate) {
        return (SaveInstanceStateHandler) Preconditions.checkNotNull(voiceChromeModule.providesSaveInstanceStateHandler(routerDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SaveInstanceStateHandler mo10268get() {
        return provideInstance(this.module, this.routerDelegateProvider);
    }
}
