package com.amazon.deecomms.core;

import com.amazon.deecomms.alexa.voice.IEventSender;
import com.amazon.deecomms.alexa.voice.usecase.EventSenderUseCase;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.perms.VoicePermissionsAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaModule_ProvidesEventSenderFactory implements Factory<IEventSender> {
    private final Provider<EventSenderUseCase> eventSenderUseCaseProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final AlexaModule module;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;

    public AlexaModule_ProvidesEventSenderFactory(AlexaModule alexaModule, Provider<EventSenderUseCase> provider, Provider<VoicePermissionsAuthority> provider2, Provider<MetricsService> provider3) {
        this.module = alexaModule;
        this.eventSenderUseCaseProvider = provider;
        this.voicePermissionsAuthorityProvider = provider2;
        this.metricsServiceProvider = provider3;
    }

    public static AlexaModule_ProvidesEventSenderFactory create(AlexaModule alexaModule, Provider<EventSenderUseCase> provider, Provider<VoicePermissionsAuthority> provider2, Provider<MetricsService> provider3) {
        return new AlexaModule_ProvidesEventSenderFactory(alexaModule, provider, provider2, provider3);
    }

    public static IEventSender provideInstance(AlexaModule alexaModule, Provider<EventSenderUseCase> provider, Provider<VoicePermissionsAuthority> provider2, Provider<MetricsService> provider3) {
        return (IEventSender) Preconditions.checkNotNull(alexaModule.providesEventSender(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static IEventSender proxyProvidesEventSender(AlexaModule alexaModule, EventSenderUseCase eventSenderUseCase, VoicePermissionsAuthority voicePermissionsAuthority, MetricsService metricsService) {
        return (IEventSender) Preconditions.checkNotNull(alexaModule.providesEventSender(eventSenderUseCase, voicePermissionsAuthority, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IEventSender mo10268get() {
        return provideInstance(this.module, this.eventSenderUseCaseProvider, this.voicePermissionsAuthorityProvider, this.metricsServiceProvider);
    }
}
