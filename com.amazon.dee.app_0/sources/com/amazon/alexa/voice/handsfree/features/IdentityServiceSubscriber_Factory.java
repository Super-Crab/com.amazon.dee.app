package com.amazon.alexa.voice.handsfree.features;

import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.voice.handsfree.initialization.VoiceAppProfileRemover;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class IdentityServiceSubscriber_Factory implements Factory<IdentityServiceSubscriber> {
    private final Provider<AmokSignInNotifier> amokSignInNotifierProvider;
    private final Provider<HandsFreeUserIdentityProvider> handsFreeUserIdentityProvider;
    private final Provider<IdentityServiceProvider> identityServiceProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private final Provider<VoiceAppProfileRemover> voiceAppProfileRemoverProvider;

    public IdentityServiceSubscriber_Factory(Provider<IdentityServiceProvider> provider, Provider<HandsFreeUserIdentityProvider> provider2, Provider<AmokSignInNotifier> provider3, Provider<MetricsBuilderProvider> provider4, Provider<VoiceAppProfileRemover> provider5) {
        this.identityServiceProvider = provider;
        this.handsFreeUserIdentityProvider = provider2;
        this.amokSignInNotifierProvider = provider3;
        this.metricsBuilderProvider = provider4;
        this.voiceAppProfileRemoverProvider = provider5;
    }

    public static IdentityServiceSubscriber_Factory create(Provider<IdentityServiceProvider> provider, Provider<HandsFreeUserIdentityProvider> provider2, Provider<AmokSignInNotifier> provider3, Provider<MetricsBuilderProvider> provider4, Provider<VoiceAppProfileRemover> provider5) {
        return new IdentityServiceSubscriber_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static IdentityServiceSubscriber newIdentityServiceSubscriber(IdentityServiceProvider identityServiceProvider, HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, AmokSignInNotifier amokSignInNotifier, MetricsBuilderProvider metricsBuilderProvider, VoiceAppProfileRemover voiceAppProfileRemover) {
        return new IdentityServiceSubscriber(identityServiceProvider, handsFreeUserIdentityProvider, amokSignInNotifier, metricsBuilderProvider, voiceAppProfileRemover);
    }

    public static IdentityServiceSubscriber provideInstance(Provider<IdentityServiceProvider> provider, Provider<HandsFreeUserIdentityProvider> provider2, Provider<AmokSignInNotifier> provider3, Provider<MetricsBuilderProvider> provider4, Provider<VoiceAppProfileRemover> provider5) {
        return new IdentityServiceSubscriber(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityServiceSubscriber mo10268get() {
        return provideInstance(this.identityServiceProvider, this.handsFreeUserIdentityProvider, this.amokSignInNotifierProvider, this.metricsBuilderProvider, this.voiceAppProfileRemoverProvider);
    }
}
