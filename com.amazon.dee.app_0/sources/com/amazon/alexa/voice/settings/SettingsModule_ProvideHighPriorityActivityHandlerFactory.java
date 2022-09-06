package com.amazon.alexa.voice.settings;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.sdk.AlexaStateTracker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideHighPriorityActivityHandlerFactory implements Factory<HighPriorityActivityHandler> {
    private final Provider<AlexaStateTracker> alexaStateTrackerProvider;
    private final Provider<EventBus> eventBusProvider;

    public SettingsModule_ProvideHighPriorityActivityHandlerFactory(Provider<EventBus> provider, Provider<AlexaStateTracker> provider2) {
        this.eventBusProvider = provider;
        this.alexaStateTrackerProvider = provider2;
    }

    public static SettingsModule_ProvideHighPriorityActivityHandlerFactory create(Provider<EventBus> provider, Provider<AlexaStateTracker> provider2) {
        return new SettingsModule_ProvideHighPriorityActivityHandlerFactory(provider, provider2);
    }

    public static HighPriorityActivityHandler provideInstance(Provider<EventBus> provider, Provider<AlexaStateTracker> provider2) {
        return proxyProvideHighPriorityActivityHandler(provider.mo10268get(), provider2.mo10268get());
    }

    public static HighPriorityActivityHandler proxyProvideHighPriorityActivityHandler(EventBus eventBus, AlexaStateTracker alexaStateTracker) {
        return (HighPriorityActivityHandler) Preconditions.checkNotNull(SettingsModule.provideHighPriorityActivityHandler(eventBus, alexaStateTracker), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HighPriorityActivityHandler mo10268get() {
        return provideInstance(this.eventBusProvider, this.alexaStateTrackerProvider);
    }
}
