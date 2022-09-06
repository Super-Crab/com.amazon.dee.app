package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesVoiceChromeRouterDelegateFactory implements Factory<RouterDelegate> {
    private final VoiceChromeModule module;
    private final Provider<Router> routerProvider;

    public VoiceChromeModule_ProvidesVoiceChromeRouterDelegateFactory(VoiceChromeModule voiceChromeModule, Provider<Router> provider) {
        this.module = voiceChromeModule;
        this.routerProvider = provider;
    }

    public static VoiceChromeModule_ProvidesVoiceChromeRouterDelegateFactory create(VoiceChromeModule voiceChromeModule, Provider<Router> provider) {
        return new VoiceChromeModule_ProvidesVoiceChromeRouterDelegateFactory(voiceChromeModule, provider);
    }

    public static RouterDelegate provideInstance(VoiceChromeModule voiceChromeModule, Provider<Router> provider) {
        return proxyProvidesVoiceChromeRouterDelegate(voiceChromeModule, provider.mo10268get());
    }

    public static RouterDelegate proxyProvidesVoiceChromeRouterDelegate(VoiceChromeModule voiceChromeModule, Router router) {
        return (RouterDelegate) Preconditions.checkNotNull(voiceChromeModule.providesVoiceChromeRouterDelegate(router), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RouterDelegate mo10268get() {
        return provideInstance(this.module, this.routerProvider);
    }
}
