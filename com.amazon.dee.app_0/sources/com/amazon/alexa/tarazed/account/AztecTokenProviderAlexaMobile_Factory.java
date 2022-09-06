package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AztecTokenProviderAlexaMobile_Factory implements Factory<AztecTokenProviderAlexaMobile> {
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;

    public AztecTokenProviderAlexaMobile_Factory(Provider<IdentityService> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        this.identityServiceProvider = provider;
        this.loggerProvider = provider2;
        this.metricsHelperProvider = provider3;
    }

    public static AztecTokenProviderAlexaMobile_Factory create(Provider<IdentityService> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        return new AztecTokenProviderAlexaMobile_Factory(provider, provider2, provider3);
    }

    public static AztecTokenProviderAlexaMobile newAztecTokenProviderAlexaMobile(IdentityService identityService, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return new AztecTokenProviderAlexaMobile(identityService, tarazedSessionLogger, tarazedMetricsHelper);
    }

    public static AztecTokenProviderAlexaMobile provideInstance(Provider<IdentityService> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedMetricsHelper> provider3) {
        return new AztecTokenProviderAlexaMobile(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AztecTokenProviderAlexaMobile mo10268get() {
        return provideInstance(this.identityServiceProvider, this.loggerProvider, this.metricsHelperProvider);
    }
}
