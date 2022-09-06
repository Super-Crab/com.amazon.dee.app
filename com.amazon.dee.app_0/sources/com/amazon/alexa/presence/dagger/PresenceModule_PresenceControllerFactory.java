package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.PresenceSubComponents;
import com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_PresenceControllerFactory implements Factory<PresenceController> {
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<PersistentLocalStorage.PresenceDataStore> presenceDataStoreProvider;
    private final Provider<PresenceSubComponents> presenceSubComponentsProvider;
    private final Provider<RoamingClient> roamingClientProvider;
    private final Provider<PresenceForegroundServiceStateAdviser> serviceStateAdviserProvider;

    public PresenceModule_PresenceControllerFactory(Provider<RoamingClient> provider, Provider<PresenceSubComponents> provider2, Provider<PersistentLocalStorage.PresenceDataStore> provider3, Provider<PresenceForegroundServiceStateAdviser> provider4, Provider<MetricsServiceV2> provider5) {
        this.roamingClientProvider = provider;
        this.presenceSubComponentsProvider = provider2;
        this.presenceDataStoreProvider = provider3;
        this.serviceStateAdviserProvider = provider4;
        this.metricsServiceV2Provider = provider5;
    }

    public static PresenceModule_PresenceControllerFactory create(Provider<RoamingClient> provider, Provider<PresenceSubComponents> provider2, Provider<PersistentLocalStorage.PresenceDataStore> provider3, Provider<PresenceForegroundServiceStateAdviser> provider4, Provider<MetricsServiceV2> provider5) {
        return new PresenceModule_PresenceControllerFactory(provider, provider2, provider3, provider4, provider5);
    }

    public static PresenceController provideInstance(Provider<RoamingClient> provider, Provider<PresenceSubComponents> provider2, Provider<PersistentLocalStorage.PresenceDataStore> provider3, Provider<PresenceForegroundServiceStateAdviser> provider4, Provider<MetricsServiceV2> provider5) {
        return proxyPresenceController(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static PresenceController proxyPresenceController(RoamingClient roamingClient, PresenceSubComponents presenceSubComponents, PersistentLocalStorage.PresenceDataStore presenceDataStore, PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser, MetricsServiceV2 metricsServiceV2) {
        return (PresenceController) Preconditions.checkNotNull(PresenceModule.presenceController(roamingClient, presenceSubComponents, presenceDataStore, presenceForegroundServiceStateAdviser, metricsServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceController mo10268get() {
        return provideInstance(this.roamingClientProvider, this.presenceSubComponentsProvider, this.presenceDataStoreProvider, this.serviceStateAdviserProvider, this.metricsServiceV2Provider);
    }
}
