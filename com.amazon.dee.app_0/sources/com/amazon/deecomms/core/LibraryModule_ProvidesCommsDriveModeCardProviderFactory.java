package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.deecomms.alexa.voice.IEventSender;
import com.amazon.deecomms.drivemode.cards.CommsDriveModeCardProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsDriveModeCardProviderFactory implements Factory<CommsDriveModeCardProvider> {
    private final Provider<AlexaServicesConnection> alexaServicesConnectionProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<IEventSender> eventSenderProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsDriveModeCardProviderFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<IEventSender> provider2, Provider<AlexaServicesConnection> provider3, Provider<CapabilitiesManager> provider4) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.eventSenderProvider = provider2;
        this.alexaServicesConnectionProvider = provider3;
        this.capabilitiesManagerProvider = provider4;
    }

    public static LibraryModule_ProvidesCommsDriveModeCardProviderFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<IEventSender> provider2, Provider<AlexaServicesConnection> provider3, Provider<CapabilitiesManager> provider4) {
        return new LibraryModule_ProvidesCommsDriveModeCardProviderFactory(libraryModule, provider, provider2, provider3, provider4);
    }

    public static CommsDriveModeCardProvider provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<IEventSender> provider2, Provider<AlexaServicesConnection> provider3, Provider<CapabilitiesManager> provider4) {
        return (CommsDriveModeCardProvider) Preconditions.checkNotNull(libraryModule.providesCommsDriveModeCardProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsDriveModeCardProvider proxyProvidesCommsDriveModeCardProvider(LibraryModule libraryModule, Context context, IEventSender iEventSender, AlexaServicesConnection alexaServicesConnection, CapabilitiesManager capabilitiesManager) {
        return (CommsDriveModeCardProvider) Preconditions.checkNotNull(libraryModule.providesCommsDriveModeCardProvider(context, iEventSender, alexaServicesConnection, capabilitiesManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDriveModeCardProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.eventSenderProvider, this.alexaServicesConnectionProvider, this.capabilitiesManagerProvider);
    }
}
