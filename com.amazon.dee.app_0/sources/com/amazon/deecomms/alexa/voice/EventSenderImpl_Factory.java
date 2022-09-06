package com.amazon.deecomms.alexa.voice;

import com.amazon.deecomms.alexa.voice.usecase.EventSenderUseCase;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.perms.VoicePermissionsAuthority;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class EventSenderImpl_Factory implements Factory<EventSenderImpl> {
    private final Provider<EventSenderUseCase> eventSenderUseCaseProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionAuthorityProvider;

    public EventSenderImpl_Factory(Provider<EventSenderUseCase> provider, Provider<VoicePermissionsAuthority> provider2, Provider<MetricsService> provider3) {
        this.eventSenderUseCaseProvider = provider;
        this.voicePermissionAuthorityProvider = provider2;
        this.metricsServiceProvider = provider3;
    }

    public static EventSenderImpl_Factory create(Provider<EventSenderUseCase> provider, Provider<VoicePermissionsAuthority> provider2, Provider<MetricsService> provider3) {
        return new EventSenderImpl_Factory(provider, provider2, provider3);
    }

    public static EventSenderImpl newEventSenderImpl(EventSenderUseCase eventSenderUseCase, VoicePermissionsAuthority voicePermissionsAuthority, MetricsService metricsService) {
        return new EventSenderImpl(eventSenderUseCase, voicePermissionsAuthority, metricsService);
    }

    public static EventSenderImpl provideInstance(Provider<EventSenderUseCase> provider, Provider<VoicePermissionsAuthority> provider2, Provider<MetricsService> provider3) {
        return new EventSenderImpl(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventSenderImpl mo10268get() {
        return provideInstance(this.eventSenderUseCaseProvider, this.voicePermissionAuthorityProvider, this.metricsServiceProvider);
    }
}
