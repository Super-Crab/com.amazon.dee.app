package com.amazon.alexa.voice.handsfree.dependencies;

import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AhfModule_ProvideIdentityServiceFactory implements Factory<IdentityService> {
    private final Provider<ComponentRegistry> componentRegistryProvider;
    private final AhfModule module;

    public AhfModule_ProvideIdentityServiceFactory(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        this.module = ahfModule;
        this.componentRegistryProvider = provider;
    }

    public static AhfModule_ProvideIdentityServiceFactory create(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        return new AhfModule_ProvideIdentityServiceFactory(ahfModule, provider);
    }

    @Nullable
    public static IdentityService provideInstance(AhfModule ahfModule, Provider<ComponentRegistry> provider) {
        return proxyProvideIdentityService(ahfModule, provider.mo10268get());
    }

    @Nullable
    public static IdentityService proxyProvideIdentityService(AhfModule ahfModule, ComponentRegistry componentRegistry) {
        return ahfModule.provideIdentityService(componentRegistry);
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public IdentityService mo10268get() {
        return provideInstance(this.module, this.componentRegistryProvider);
    }
}
