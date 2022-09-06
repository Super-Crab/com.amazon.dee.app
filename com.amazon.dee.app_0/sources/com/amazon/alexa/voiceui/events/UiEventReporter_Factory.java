package com.amazon.alexa.voiceui.events;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class UiEventReporter_Factory implements Factory<UiEventReporter> {
    private final Provider<Context> contextProvider;

    public UiEventReporter_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static UiEventReporter_Factory create(Provider<Context> provider) {
        return new UiEventReporter_Factory(provider);
    }

    public static UiEventReporter newUiEventReporter(Context context) {
        return new UiEventReporter(context);
    }

    public static UiEventReporter provideInstance(Provider<Context> provider) {
        return new UiEventReporter(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UiEventReporter mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
