package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class PresenceModule_RoamingClientFactory implements Factory<RoamingClient> {
    private static final PresenceModule_RoamingClientFactory INSTANCE = new PresenceModule_RoamingClientFactory();

    public static PresenceModule_RoamingClientFactory create() {
        return INSTANCE;
    }

    public static RoamingClient provideInstance() {
        return proxyRoamingClient();
    }

    public static RoamingClient proxyRoamingClient() {
        return (RoamingClient) Preconditions.checkNotNull(PresenceModule.roamingClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RoamingClient mo10268get() {
        return provideInstance();
    }
}
