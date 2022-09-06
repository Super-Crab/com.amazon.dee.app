package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voiceui.events.UiEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DriveModeCardCreationEventListener_Factory implements Factory<DriveModeCardCreationEventListener> {
    private final Provider<UiEventReporter> uiEventReporterProvider;

    public DriveModeCardCreationEventListener_Factory(Provider<UiEventReporter> provider) {
        this.uiEventReporterProvider = provider;
    }

    public static DriveModeCardCreationEventListener_Factory create(Provider<UiEventReporter> provider) {
        return new DriveModeCardCreationEventListener_Factory(provider);
    }

    public static DriveModeCardCreationEventListener newDriveModeCardCreationEventListener(UiEventReporter uiEventReporter) {
        return new DriveModeCardCreationEventListener(uiEventReporter);
    }

    public static DriveModeCardCreationEventListener provideInstance(Provider<UiEventReporter> provider) {
        return new DriveModeCardCreationEventListener(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeCardCreationEventListener mo10268get() {
        return provideInstance(this.uiEventReporterProvider);
    }
}
