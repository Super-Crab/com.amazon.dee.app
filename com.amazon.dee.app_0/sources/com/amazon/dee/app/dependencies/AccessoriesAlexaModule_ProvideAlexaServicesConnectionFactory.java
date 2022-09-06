package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AccessoriesAlexaModule_ProvideAlexaServicesConnectionFactory implements Factory<AlexaServicesConnection> {
    private final Provider<Context> contextProvider;
    private final AccessoriesAlexaModule module;

    public AccessoriesAlexaModule_ProvideAlexaServicesConnectionFactory(AccessoriesAlexaModule accessoriesAlexaModule, Provider<Context> provider) {
        this.module = accessoriesAlexaModule;
        this.contextProvider = provider;
    }

    public static AccessoriesAlexaModule_ProvideAlexaServicesConnectionFactory create(AccessoriesAlexaModule accessoriesAlexaModule, Provider<Context> provider) {
        return new AccessoriesAlexaModule_ProvideAlexaServicesConnectionFactory(accessoriesAlexaModule, provider);
    }

    public static AlexaServicesConnection provideInstance(AccessoriesAlexaModule accessoriesAlexaModule, Provider<Context> provider) {
        return proxyProvideAlexaServicesConnection(accessoriesAlexaModule, provider.mo10268get());
    }

    public static AlexaServicesConnection proxyProvideAlexaServicesConnection(AccessoriesAlexaModule accessoriesAlexaModule, Context context) {
        return (AlexaServicesConnection) Preconditions.checkNotNull(accessoriesAlexaModule.provideAlexaServicesConnection(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaServicesConnection mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
