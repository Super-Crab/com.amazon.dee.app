package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.events.UiEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class StandardCardMetricsInteractor_Factory implements Factory<StandardCardMetricsInteractor> {
    private final Provider<UiEventReporter> uiEventReporterProvider;

    public StandardCardMetricsInteractor_Factory(Provider<UiEventReporter> provider) {
        this.uiEventReporterProvider = provider;
    }

    public static StandardCardMetricsInteractor_Factory create(Provider<UiEventReporter> provider) {
        return new StandardCardMetricsInteractor_Factory(provider);
    }

    public static StandardCardMetricsInteractor newStandardCardMetricsInteractor(UiEventReporter uiEventReporter) {
        return new StandardCardMetricsInteractor(uiEventReporter);
    }

    public static StandardCardMetricsInteractor provideInstance(Provider<UiEventReporter> provider) {
        return new StandardCardMetricsInteractor(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public StandardCardMetricsInteractor mo10268get() {
        return provideInstance(this.uiEventReporterProvider);
    }
}
