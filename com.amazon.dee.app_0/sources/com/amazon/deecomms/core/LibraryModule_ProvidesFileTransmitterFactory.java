package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.media.photos.FileTransmitter;
import com.amazon.deecomms.media.photos.MAPAuthenticatedURLConnectionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesFileTransmitterFactory implements Factory<FileTransmitter> {
    private final Provider<AccountConfiguration> accountConfigurationProvider;
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<MAPAuthenticatedURLConnectionFactory> connectionFactoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EndpointsCache> endpointsCacheProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesFileTransmitterFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<CommsIdentityManager> provider3, Provider<ClientConfiguration> provider4, Provider<MAPAuthenticatedURLConnectionFactory> provider5, Provider<EndpointsCache> provider6, Provider<AccountConfiguration> provider7, Provider<EventBus> provider8) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.clientConfigurationProvider = provider4;
        this.connectionFactoryProvider = provider5;
        this.endpointsCacheProvider = provider6;
        this.accountConfigurationProvider = provider7;
        this.eventBusProvider = provider8;
    }

    public static LibraryModule_ProvidesFileTransmitterFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<CommsIdentityManager> provider3, Provider<ClientConfiguration> provider4, Provider<MAPAuthenticatedURLConnectionFactory> provider5, Provider<EndpointsCache> provider6, Provider<AccountConfiguration> provider7, Provider<EventBus> provider8) {
        return new LibraryModule_ProvidesFileTransmitterFactory(libraryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static FileTransmitter provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<CommsIdentityManager> provider3, Provider<ClientConfiguration> provider4, Provider<MAPAuthenticatedURLConnectionFactory> provider5, Provider<EndpointsCache> provider6, Provider<AccountConfiguration> provider7, Provider<EventBus> provider8) {
        return (FileTransmitter) Preconditions.checkNotNull(libraryModule.providesFileTransmitter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static FileTransmitter proxyProvidesFileTransmitter(LibraryModule libraryModule, Context context, IdentityService identityService, CommsIdentityManager commsIdentityManager, ClientConfiguration clientConfiguration, MAPAuthenticatedURLConnectionFactory mAPAuthenticatedURLConnectionFactory, EndpointsCache endpointsCache, AccountConfiguration accountConfiguration, EventBus eventBus) {
        return (FileTransmitter) Preconditions.checkNotNull(libraryModule.providesFileTransmitter(context, identityService, commsIdentityManager, clientConfiguration, mAPAuthenticatedURLConnectionFactory, endpointsCache, accountConfiguration, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileTransmitter mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider, this.commsIdentityManagerProvider, this.clientConfigurationProvider, this.connectionFactoryProvider, this.endpointsCacheProvider, this.accountConfigurationProvider, this.eventBusProvider);
    }
}
