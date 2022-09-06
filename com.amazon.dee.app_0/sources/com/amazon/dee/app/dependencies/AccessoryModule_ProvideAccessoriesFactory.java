package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AccessoryModule_ProvideAccessoriesFactory implements Factory<Accessories> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<Context> contextProvider;
    private final AccessoryModule module;

    public AccessoryModule_ProvideAccessoriesFactory(AccessoryModule accessoryModule, Provider<Context> provider, Provider<AlexaServicesConnection> provider2) {
        this.module = accessoryModule;
        this.contextProvider = provider;
        this.alexaServicesConnectionProvider = provider2;
    }

    public static AccessoryModule_ProvideAccessoriesFactory create(AccessoryModule accessoryModule, Provider<Context> provider, Provider<AlexaServicesConnection> provider2) {
        return new AccessoryModule_ProvideAccessoriesFactory(accessoryModule, provider, provider2);
    }

    public static Accessories provideInstance(AccessoryModule accessoryModule, Provider<Context> provider, Provider<AlexaServicesConnection> provider2) {
        return proxyProvideAccessories(accessoryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static Accessories proxyProvideAccessories(AccessoryModule accessoryModule, Context context, AlexaServicesConnection alexaServicesConnection) {
        return (Accessories) Preconditions.checkNotNull(accessoryModule.provideAccessories(context, alexaServicesConnection), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Accessories mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.alexaServicesConnectionProvider);
    }
}
