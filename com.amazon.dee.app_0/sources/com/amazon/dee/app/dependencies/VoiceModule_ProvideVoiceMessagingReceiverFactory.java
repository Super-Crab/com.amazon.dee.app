package com.amazon.dee.app.dependencies;

import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.voice.model.VoiceService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceModule_ProvideVoiceMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final VoiceModule module;
    private final Provider<VoiceService> voiceServiceProvider;

    public VoiceModule_ProvideVoiceMessagingReceiverFactory(VoiceModule voiceModule, Provider<VoiceService> provider) {
        this.module = voiceModule;
        this.voiceServiceProvider = provider;
    }

    public static VoiceModule_ProvideVoiceMessagingReceiverFactory create(VoiceModule voiceModule, Provider<VoiceService> provider) {
        return new VoiceModule_ProvideVoiceMessagingReceiverFactory(voiceModule, provider);
    }

    public static MessagingReceiver provideInstance(VoiceModule voiceModule, Provider<VoiceService> provider) {
        return proxyProvideVoiceMessagingReceiver(voiceModule, provider.mo10268get());
    }

    public static MessagingReceiver proxyProvideVoiceMessagingReceiver(VoiceModule voiceModule, VoiceService voiceService) {
        return (MessagingReceiver) Preconditions.checkNotNull(voiceModule.provideVoiceMessagingReceiver(voiceService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.module, this.voiceServiceProvider);
    }
}
