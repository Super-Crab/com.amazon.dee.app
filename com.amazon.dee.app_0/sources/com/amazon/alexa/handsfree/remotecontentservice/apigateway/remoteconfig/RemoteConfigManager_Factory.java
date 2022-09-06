package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeStateProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.RemoteConfigProvider;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class RemoteConfigManager_Factory implements Factory<RemoteConfigManager> {
    private final Provider<ApplicationInformationProvider> appInfoProviderLazyProvider;
    private final Provider<HandsFreeStateProvider> handsFreeStateProvider;
    private final Provider<RemoteConfigDeserializer> remoteConfigDeserializerProvider;
    private final Provider<RemoteConfigProvider> remoteConfigProvider;

    public RemoteConfigManager_Factory(Provider<RemoteConfigProvider> provider, Provider<RemoteConfigDeserializer> provider2, Provider<HandsFreeStateProvider> provider3, Provider<ApplicationInformationProvider> provider4) {
        this.remoteConfigProvider = provider;
        this.remoteConfigDeserializerProvider = provider2;
        this.handsFreeStateProvider = provider3;
        this.appInfoProviderLazyProvider = provider4;
    }

    public static RemoteConfigManager_Factory create(Provider<RemoteConfigProvider> provider, Provider<RemoteConfigDeserializer> provider2, Provider<HandsFreeStateProvider> provider3, Provider<ApplicationInformationProvider> provider4) {
        return new RemoteConfigManager_Factory(provider, provider2, provider3, provider4);
    }

    public static RemoteConfigManager newRemoteConfigManager(RemoteConfigProvider remoteConfigProvider, RemoteConfigDeserializer remoteConfigDeserializer, HandsFreeStateProvider handsFreeStateProvider, Lazy<ApplicationInformationProvider> lazy) {
        return new RemoteConfigManager(remoteConfigProvider, remoteConfigDeserializer, handsFreeStateProvider, lazy);
    }

    public static RemoteConfigManager provideInstance(Provider<RemoteConfigProvider> provider, Provider<RemoteConfigDeserializer> provider2, Provider<HandsFreeStateProvider> provider3, Provider<ApplicationInformationProvider> provider4) {
        return new RemoteConfigManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), DoubleCheck.lazy(provider4));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteConfigManager mo10268get() {
        return provideInstance(this.remoteConfigProvider, this.remoteConfigDeserializerProvider, this.handsFreeStateProvider, this.appInfoProviderLazyProvider);
    }
}
