package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class AhfModule_ProvideComponentRegistryFactory implements Factory<ComponentRegistry> {
    private final AhfModule module;

    public AhfModule_ProvideComponentRegistryFactory(AhfModule ahfModule) {
        this.module = ahfModule;
    }

    public static AhfModule_ProvideComponentRegistryFactory create(AhfModule ahfModule) {
        return new AhfModule_ProvideComponentRegistryFactory(ahfModule);
    }

    public static ComponentRegistry provideInstance(AhfModule ahfModule) {
        return proxyProvideComponentRegistry(ahfModule);
    }

    public static ComponentRegistry proxyProvideComponentRegistry(AhfModule ahfModule) {
        return (ComponentRegistry) Preconditions.checkNotNull(ahfModule.provideComponentRegistry(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ComponentRegistry mo10268get() {
        return provideInstance(this.module);
    }
}
