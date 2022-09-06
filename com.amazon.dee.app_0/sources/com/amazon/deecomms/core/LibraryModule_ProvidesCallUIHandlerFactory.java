package com.amazon.deecomms.core;

import com.amazon.deecomms.calling.core.CallInitiationAuthority;
import com.amazon.deecomms.calling.receiver.CallUIHandler;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCallUIHandlerFactory implements Factory<CallUIHandler> {
    private final Provider<ActivitiesManager> activitiesManagerProvider;
    private final Provider<CallInitiationAuthority> callInitiationAuthorityProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCallUIHandlerFactory(LibraryModule libraryModule, Provider<ActivitiesManager> provider, Provider<CallInitiationAuthority> provider2) {
        this.module = libraryModule;
        this.activitiesManagerProvider = provider;
        this.callInitiationAuthorityProvider = provider2;
    }

    public static LibraryModule_ProvidesCallUIHandlerFactory create(LibraryModule libraryModule, Provider<ActivitiesManager> provider, Provider<CallInitiationAuthority> provider2) {
        return new LibraryModule_ProvidesCallUIHandlerFactory(libraryModule, provider, provider2);
    }

    public static CallUIHandler provideInstance(LibraryModule libraryModule, Provider<ActivitiesManager> provider, Provider<CallInitiationAuthority> provider2) {
        return (CallUIHandler) Preconditions.checkNotNull(libraryModule.providesCallUIHandler(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CallUIHandler proxyProvidesCallUIHandler(LibraryModule libraryModule, ActivitiesManager activitiesManager, CallInitiationAuthority callInitiationAuthority) {
        return (CallUIHandler) Preconditions.checkNotNull(libraryModule.providesCallUIHandler(activitiesManager, callInitiationAuthority), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallUIHandler mo10268get() {
        return provideInstance(this.module, this.activitiesManagerProvider, this.callInitiationAuthorityProvider);
    }
}
