package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.ConversationService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesOobeServiceFactory implements Factory<OobeService> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsDeviceSupport> commsDeviceSupportProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ConversationService> conversationServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesOobeServiceFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CommsDeviceSupport> provider3, Provider<ConversationService> provider4, Provider<CommsManager> provider5, Provider<CapabilitiesManager> provider6, Provider<EventBus> provider7) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.commsDeviceSupportProvider = provider3;
        this.conversationServiceProvider = provider4;
        this.commsManagerProvider = provider5;
        this.capabilitiesManagerProvider = provider6;
        this.eventBusProvider = provider7;
    }

    public static LibraryModule_ProvidesOobeServiceFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CommsDeviceSupport> provider3, Provider<ConversationService> provider4, Provider<CommsManager> provider5, Provider<CapabilitiesManager> provider6, Provider<EventBus> provider7) {
        return new LibraryModule_ProvidesOobeServiceFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static OobeService provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CommsDeviceSupport> provider3, Provider<ConversationService> provider4, Provider<CommsManager> provider5, Provider<CapabilitiesManager> provider6, Provider<EventBus> provider7) {
        return (OobeService) Preconditions.checkNotNull(libraryModule.providesOobeService(provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static OobeService proxyProvidesOobeService(LibraryModule libraryModule, Context context, Lazy<CommsIdentityManager> lazy, Lazy<CommsDeviceSupport> lazy2, Lazy<ConversationService> lazy3, Lazy<CommsManager> lazy4, Lazy<CapabilitiesManager> lazy5, Lazy<EventBus> lazy6) {
        return (OobeService) Preconditions.checkNotNull(libraryModule.providesOobeService(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public OobeService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.commsIdentityManagerProvider, this.commsDeviceSupportProvider, this.conversationServiceProvider, this.commsManagerProvider, this.capabilitiesManagerProvider, this.eventBusProvider);
    }
}
