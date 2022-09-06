package com.amazon.alexa.voiceui.chrome;

import android.view.ViewGroup;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeModule_ProvidesVoiceChromeRouterFactory implements Factory<Router> {
    private final Provider<Component> componentProvider;
    private final Provider<ViewGroup> containerProvider;
    private final VoiceChromeModule module;

    public VoiceChromeModule_ProvidesVoiceChromeRouterFactory(VoiceChromeModule voiceChromeModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        this.module = voiceChromeModule;
        this.containerProvider = provider;
        this.componentProvider = provider2;
    }

    public static VoiceChromeModule_ProvidesVoiceChromeRouterFactory create(VoiceChromeModule voiceChromeModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        return new VoiceChromeModule_ProvidesVoiceChromeRouterFactory(voiceChromeModule, provider, provider2);
    }

    public static Router provideInstance(VoiceChromeModule voiceChromeModule, Provider<ViewGroup> provider, Provider<Component> provider2) {
        return proxyProvidesVoiceChromeRouter(voiceChromeModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static Router proxyProvidesVoiceChromeRouter(VoiceChromeModule voiceChromeModule, ViewGroup viewGroup, Component component) {
        return (Router) Preconditions.checkNotNull(voiceChromeModule.providesVoiceChromeRouter(viewGroup, component), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Router mo10268get() {
        return provideInstance(this.module, this.containerProvider, this.componentProvider);
    }
}
