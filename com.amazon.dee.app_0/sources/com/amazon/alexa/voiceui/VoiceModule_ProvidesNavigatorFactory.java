package com.amazon.alexa.voiceui;

import com.amazon.alexa.voice.ui.routing.Navigator;
import com.amazon.alexa.voiceui.cards.VoiceNavigator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceModule_ProvidesNavigatorFactory implements Factory<Navigator> {
    private final VoiceModule module;
    private final Provider<VoiceNavigator> voiceNavigatorProvider;

    public VoiceModule_ProvidesNavigatorFactory(VoiceModule voiceModule, Provider<VoiceNavigator> provider) {
        this.module = voiceModule;
        this.voiceNavigatorProvider = provider;
    }

    public static VoiceModule_ProvidesNavigatorFactory create(VoiceModule voiceModule, Provider<VoiceNavigator> provider) {
        return new VoiceModule_ProvidesNavigatorFactory(voiceModule, provider);
    }

    public static Navigator provideInstance(VoiceModule voiceModule, Provider<VoiceNavigator> provider) {
        return proxyProvidesNavigator(voiceModule, provider.mo10268get());
    }

    public static Navigator proxyProvidesNavigator(VoiceModule voiceModule, VoiceNavigator voiceNavigator) {
        return (Navigator) Preconditions.checkNotNull(voiceModule.providesNavigator(voiceNavigator), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Navigator mo10268get() {
        return provideInstance(this.module, this.voiceNavigatorProvider);
    }
}
