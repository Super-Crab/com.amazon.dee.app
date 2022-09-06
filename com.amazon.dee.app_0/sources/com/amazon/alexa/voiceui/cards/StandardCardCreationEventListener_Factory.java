package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.events.UiEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class StandardCardCreationEventListener_Factory implements Factory<StandardCardCreationEventListener> {
    private final Provider<UiEventReporter> uiEventReporterProvider;

    public StandardCardCreationEventListener_Factory(Provider<UiEventReporter> provider) {
        this.uiEventReporterProvider = provider;
    }

    public static StandardCardCreationEventListener_Factory create(Provider<UiEventReporter> provider) {
        return new StandardCardCreationEventListener_Factory(provider);
    }

    public static StandardCardCreationEventListener newStandardCardCreationEventListener(UiEventReporter uiEventReporter) {
        return new StandardCardCreationEventListener(uiEventReporter);
    }

    public static StandardCardCreationEventListener provideInstance(Provider<UiEventReporter> provider) {
        return new StandardCardCreationEventListener(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StandardCardCreationEventListener mo10268get() {
        return provideInstance(this.uiEventReporterProvider);
    }
}
