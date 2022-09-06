package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.tta.metrics.EventClock;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TypingPresenter_Factory implements Factory<TypingPresenter> {
    private final Provider<EventClock> eventClockProvider;
    private final Provider<MetricEventProcessingService> eventProcessingServiceProvider;
    private final Provider<PersistentStorage> persistentStorageProvider;
    private final Provider<TtaInteractorProvider> ttaInteractorProvider;
    private final Provider<TypingView> typingViewProvider;

    public TypingPresenter_Factory(Provider<TypingView> provider, Provider<PersistentStorage> provider2, Provider<MetricEventProcessingService> provider3, Provider<EventClock> provider4, Provider<TtaInteractorProvider> provider5) {
        this.typingViewProvider = provider;
        this.persistentStorageProvider = provider2;
        this.eventProcessingServiceProvider = provider3;
        this.eventClockProvider = provider4;
        this.ttaInteractorProvider = provider5;
    }

    public static TypingPresenter_Factory create(Provider<TypingView> provider, Provider<PersistentStorage> provider2, Provider<MetricEventProcessingService> provider3, Provider<EventClock> provider4, Provider<TtaInteractorProvider> provider5) {
        return new TypingPresenter_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static TypingPresenter newTypingPresenter(TypingView typingView, PersistentStorage persistentStorage, MetricEventProcessingService metricEventProcessingService, EventClock eventClock, TtaInteractorProvider ttaInteractorProvider) {
        return new TypingPresenter(typingView, persistentStorage, metricEventProcessingService, eventClock, ttaInteractorProvider);
    }

    public static TypingPresenter provideInstance(Provider<TypingView> provider, Provider<PersistentStorage> provider2, Provider<MetricEventProcessingService> provider3, Provider<EventClock> provider4, Provider<TtaInteractorProvider> provider5) {
        return new TypingPresenter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TypingPresenter mo10268get() {
        return provideInstance(this.typingViewProvider, this.persistentStorageProvider, this.eventProcessingServiceProvider, this.eventClockProvider, this.ttaInteractorProvider);
    }
}
