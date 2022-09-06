package com.amazon.alexa.voice.events;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class EventsModule_ProvideVoiceUiEventBroadcastReceiverFactory implements Factory<VoiceUiEventBroadcastReceiver> {
    private static final EventsModule_ProvideVoiceUiEventBroadcastReceiverFactory INSTANCE = new EventsModule_ProvideVoiceUiEventBroadcastReceiverFactory();

    public static EventsModule_ProvideVoiceUiEventBroadcastReceiverFactory create() {
        return INSTANCE;
    }

    public static VoiceUiEventBroadcastReceiver provideInstance() {
        return proxyProvideVoiceUiEventBroadcastReceiver();
    }

    public static VoiceUiEventBroadcastReceiver proxyProvideVoiceUiEventBroadcastReceiver() {
        return (VoiceUiEventBroadcastReceiver) Preconditions.checkNotNull(EventsModule.provideVoiceUiEventBroadcastReceiver(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceUiEventBroadcastReceiver mo10268get() {
        return provideInstance();
    }
}
