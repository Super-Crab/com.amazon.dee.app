package com.amazon.alexa.presence.bleconn.identity.clients;

import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.service.AuthTokenProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
/* loaded from: classes9.dex */
public final class PresenceIdentityClient_Factory implements Factory<PresenceIdentityClient> {
    private final Provider<OkHttpClient> httpClientProvider;
    private final Provider<AuthTokenProvider> identityProvider;
    private final Provider<String> identityTokenEndpointProvider;
    private final Provider<PersonIdProvider> personIdProvider;

    public PresenceIdentityClient_Factory(Provider<OkHttpClient> provider, Provider<String> provider2, Provider<AuthTokenProvider> provider3, Provider<PersonIdProvider> provider4) {
        this.httpClientProvider = provider;
        this.identityTokenEndpointProvider = provider2;
        this.identityProvider = provider3;
        this.personIdProvider = provider4;
    }

    public static PresenceIdentityClient_Factory create(Provider<OkHttpClient> provider, Provider<String> provider2, Provider<AuthTokenProvider> provider3, Provider<PersonIdProvider> provider4) {
        return new PresenceIdentityClient_Factory(provider, provider2, provider3, provider4);
    }

    public static PresenceIdentityClient newPresenceIdentityClient(OkHttpClient okHttpClient, String str, AuthTokenProvider authTokenProvider, PersonIdProvider personIdProvider) {
        return new PresenceIdentityClient(okHttpClient, str, authTokenProvider, personIdProvider);
    }

    public static PresenceIdentityClient provideInstance(Provider<OkHttpClient> provider, Provider<String> provider2, Provider<AuthTokenProvider> provider3, Provider<PersonIdProvider> provider4) {
        return new PresenceIdentityClient(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceIdentityClient mo10268get() {
        return provideInstance(this.httpClientProvider, this.identityTokenEndpointProvider, this.identityProvider, this.personIdProvider);
    }
}
