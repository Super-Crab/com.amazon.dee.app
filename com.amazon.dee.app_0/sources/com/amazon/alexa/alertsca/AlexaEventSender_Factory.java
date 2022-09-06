package com.amazon.alexa.alertsca;

import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlexaEventSender_Factory implements Factory<AlexaEventSender> {
    private final Provider<AlexaMobileFrameworkApis> alexaMobileFrameworkApisProvider;

    public AlexaEventSender_Factory(Provider<AlexaMobileFrameworkApis> provider) {
        this.alexaMobileFrameworkApisProvider = provider;
    }

    public static AlexaEventSender_Factory create(Provider<AlexaMobileFrameworkApis> provider) {
        return new AlexaEventSender_Factory(provider);
    }

    public static AlexaEventSender newAlexaEventSender(AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        return new AlexaEventSender(alexaMobileFrameworkApis);
    }

    public static AlexaEventSender provideInstance(Provider<AlexaMobileFrameworkApis> provider) {
        return new AlexaEventSender(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaEventSender mo10268get() {
        return provideInstance(this.alexaMobileFrameworkApisProvider);
    }
}
