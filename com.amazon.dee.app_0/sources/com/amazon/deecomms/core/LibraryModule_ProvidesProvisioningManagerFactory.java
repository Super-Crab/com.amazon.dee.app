package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.common.service.ProvisioningManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesProvisioningManagerFactory implements Factory<ProvisioningManager> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesProvisioningManagerFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<CapabilitiesManager> provider3) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
        this.capabilitiesManagerProvider = provider3;
    }

    public static LibraryModule_ProvidesProvisioningManagerFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<CapabilitiesManager> provider3) {
        return new LibraryModule_ProvidesProvisioningManagerFactory(libraryModule, provider, provider2, provider3);
    }

    public static ProvisioningManager provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<EventBus> provider2, Provider<CapabilitiesManager> provider3) {
        return (ProvisioningManager) Preconditions.checkNotNull(libraryModule.providesProvisioningManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ProvisioningManager proxyProvidesProvisioningManager(LibraryModule libraryModule, Context context, EventBus eventBus, CapabilitiesManager capabilitiesManager) {
        return (ProvisioningManager) Preconditions.checkNotNull(libraryModule.providesProvisioningManager(context, eventBus, capabilitiesManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProvisioningManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventBusProvider, this.capabilitiesManagerProvider);
    }
}
