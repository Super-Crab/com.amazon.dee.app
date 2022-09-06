package com.amazon.alexa.presence;

import com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceController_Factory implements Factory<PresenceController> {
    private final Provider<MetricsServiceV2> metricsServiceProvider;
    private final Provider<PersistentLocalStorage.PresenceDataStore> presenceDataStoreProvider;
    private final Provider<PresenceSubComponents> presenceSubComponentsProvider;
    private final Provider<RoamingClient> roamingClientProvider;
    private final Provider<PresenceForegroundServiceStateAdviser> serviceStateAdviserProvider;

    public PresenceController_Factory(Provider<RoamingClient> provider, Provider<PresenceSubComponents> provider2, Provider<PersistentLocalStorage.PresenceDataStore> provider3, Provider<PresenceForegroundServiceStateAdviser> provider4, Provider<MetricsServiceV2> provider5) {
        this.roamingClientProvider = provider;
        this.presenceSubComponentsProvider = provider2;
        this.presenceDataStoreProvider = provider3;
        this.serviceStateAdviserProvider = provider4;
        this.metricsServiceProvider = provider5;
    }

    public static PresenceController_Factory create(Provider<RoamingClient> provider, Provider<PresenceSubComponents> provider2, Provider<PersistentLocalStorage.PresenceDataStore> provider3, Provider<PresenceForegroundServiceStateAdviser> provider4, Provider<MetricsServiceV2> provider5) {
        return new PresenceController_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static PresenceController newPresenceController(RoamingClient roamingClient, PresenceSubComponents presenceSubComponents, PersistentLocalStorage.PresenceDataStore presenceDataStore, PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser, MetricsServiceV2 metricsServiceV2) {
        return new PresenceController(roamingClient, presenceSubComponents, presenceDataStore, presenceForegroundServiceStateAdviser, metricsServiceV2);
    }

    public static PresenceController provideInstance(Provider<RoamingClient> provider, Provider<PresenceSubComponents> provider2, Provider<PersistentLocalStorage.PresenceDataStore> provider3, Provider<PresenceForegroundServiceStateAdviser> provider4, Provider<MetricsServiceV2> provider5) {
        return new PresenceController(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceController mo10268get() {
        return provideInstance(this.roamingClientProvider, this.presenceSubComponentsProvider, this.presenceDataStoreProvider, this.serviceStateAdviserProvider, this.metricsServiceProvider);
    }
}
