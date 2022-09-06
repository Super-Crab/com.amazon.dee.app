package com.amazon.alexa.alertsca;

import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlexaInteractionScheduler_Factory implements Factory<AlexaInteractionScheduler> {
    private final Provider<AlexaMobileFrameworkApis> alexaMobileFrameworkApisProvider;

    public AlexaInteractionScheduler_Factory(Provider<AlexaMobileFrameworkApis> provider) {
        this.alexaMobileFrameworkApisProvider = provider;
    }

    public static AlexaInteractionScheduler_Factory create(Provider<AlexaMobileFrameworkApis> provider) {
        return new AlexaInteractionScheduler_Factory(provider);
    }

    public static AlexaInteractionScheduler newAlexaInteractionScheduler(AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        return new AlexaInteractionScheduler(alexaMobileFrameworkApis);
    }

    public static AlexaInteractionScheduler provideInstance(Provider<AlexaMobileFrameworkApis> provider) {
        return new AlexaInteractionScheduler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaInteractionScheduler mo10268get() {
        return provideInstance(this.alexaMobileFrameworkApisProvider);
    }
}
