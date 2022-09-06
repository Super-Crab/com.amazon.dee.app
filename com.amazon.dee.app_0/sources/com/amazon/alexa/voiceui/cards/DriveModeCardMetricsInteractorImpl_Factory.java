package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.events.UiEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DriveModeCardMetricsInteractorImpl_Factory implements Factory<DriveModeCardMetricsInteractorImpl> {
    private final Provider<UiEventReporter> uiEventReporterProvider;

    public DriveModeCardMetricsInteractorImpl_Factory(Provider<UiEventReporter> provider) {
        this.uiEventReporterProvider = provider;
    }

    public static DriveModeCardMetricsInteractorImpl_Factory create(Provider<UiEventReporter> provider) {
        return new DriveModeCardMetricsInteractorImpl_Factory(provider);
    }

    public static DriveModeCardMetricsInteractorImpl newDriveModeCardMetricsInteractorImpl(UiEventReporter uiEventReporter) {
        return new DriveModeCardMetricsInteractorImpl(uiEventReporter);
    }

    public static DriveModeCardMetricsInteractorImpl provideInstance(Provider<UiEventReporter> provider) {
        return new DriveModeCardMetricsInteractorImpl(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeCardMetricsInteractorImpl mo10268get() {
        return provideInstance(this.uiEventReporterProvider);
    }
}
