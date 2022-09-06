package com.amazon.alexa.voice.ui;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class UiModule_ProvideVoiceEventBusRebroadcastSenderFactory implements Factory<VoiceEventBusRebroadcastSender> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;

    public UiModule_ProvideVoiceEventBusRebroadcastSenderFactory(Provider<Context> provider, Provider<EventBus> provider2) {
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
    }

    public static UiModule_ProvideVoiceEventBusRebroadcastSenderFactory create(Provider<Context> provider, Provider<EventBus> provider2) {
        return new UiModule_ProvideVoiceEventBusRebroadcastSenderFactory(provider, provider2);
    }

    public static VoiceEventBusRebroadcastSender provideInstance(Provider<Context> provider, Provider<EventBus> provider2) {
        return proxyProvideVoiceEventBusRebroadcastSender(provider.mo10268get(), provider2.mo10268get());
    }

    public static VoiceEventBusRebroadcastSender proxyProvideVoiceEventBusRebroadcastSender(Context context, EventBus eventBus) {
        return (VoiceEventBusRebroadcastSender) Preconditions.checkNotNull(UiModule.provideVoiceEventBusRebroadcastSender(context, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceEventBusRebroadcastSender mo10268get() {
        return provideInstance(this.contextProvider, this.eventBusProvider);
    }
}
