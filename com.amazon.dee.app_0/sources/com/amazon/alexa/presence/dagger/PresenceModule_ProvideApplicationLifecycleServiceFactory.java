package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideApplicationLifecycleServiceFactory implements Factory<ApplicationLifecycleService> {
    private final Provider<ComponentRegistry> applicationComponentsProvider;

    public PresenceModule_ProvideApplicationLifecycleServiceFactory(Provider<ComponentRegistry> provider) {
        this.applicationComponentsProvider = provider;
    }

    public static PresenceModule_ProvideApplicationLifecycleServiceFactory create(Provider<ComponentRegistry> provider) {
        return new PresenceModule_ProvideApplicationLifecycleServiceFactory(provider);
    }

    public static ApplicationLifecycleService provideInstance(Provider<ComponentRegistry> provider) {
        return proxyProvideApplicationLifecycleService(provider.mo10268get());
    }

    public static ApplicationLifecycleService proxyProvideApplicationLifecycleService(ComponentRegistry componentRegistry) {
        return (ApplicationLifecycleService) Preconditions.checkNotNull(PresenceModule.provideApplicationLifecycleService(componentRegistry), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApplicationLifecycleService mo10268get() {
        return provideInstance(this.applicationComponentsProvider);
    }
}
