package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public final class ArcusModule_ProvidesRemoteConfigurationManagerFactory implements Factory<RemoteConfigurationManager> {
    private final Provider<Context> contextProvider;
    private final Provider<JSONObject> defaultConfigurationProvider;
    private final ArcusModule module;

    public ArcusModule_ProvidesRemoteConfigurationManagerFactory(ArcusModule arcusModule, Provider<Context> provider, Provider<JSONObject> provider2) {
        this.module = arcusModule;
        this.contextProvider = provider;
        this.defaultConfigurationProvider = provider2;
    }

    public static ArcusModule_ProvidesRemoteConfigurationManagerFactory create(ArcusModule arcusModule, Provider<Context> provider, Provider<JSONObject> provider2) {
        return new ArcusModule_ProvidesRemoteConfigurationManagerFactory(arcusModule, provider, provider2);
    }

    public static RemoteConfigurationManager provideInstance(ArcusModule arcusModule, Provider<Context> provider, Provider<JSONObject> provider2) {
        return proxyProvidesRemoteConfigurationManager(arcusModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static RemoteConfigurationManager proxyProvidesRemoteConfigurationManager(ArcusModule arcusModule, Context context, JSONObject jSONObject) {
        return (RemoteConfigurationManager) Preconditions.checkNotNull(arcusModule.providesRemoteConfigurationManager(context, jSONObject), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteConfigurationManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.defaultConfigurationProvider);
    }
}
