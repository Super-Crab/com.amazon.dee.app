package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.sharing.ContentSharingService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesContentSharingServiceFactory implements Factory<ContentSharingService> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CommsIdentityManager> identityManagerProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesContentSharingServiceFactory(LibraryModule libraryModule, Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<IdentityService> provider3, Provider<Context> provider4) {
        this.module = libraryModule;
        this.identityManagerProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.identityServiceProvider = provider3;
        this.contextProvider = provider4;
    }

    public static LibraryModule_ProvidesContentSharingServiceFactory create(LibraryModule libraryModule, Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<IdentityService> provider3, Provider<Context> provider4) {
        return new LibraryModule_ProvidesContentSharingServiceFactory(libraryModule, provider, provider2, provider3, provider4);
    }

    public static ContentSharingService provideInstance(LibraryModule libraryModule, Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<IdentityService> provider3, Provider<Context> provider4) {
        return (ContentSharingService) Preconditions.checkNotNull(libraryModule.providesContentSharingService(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ContentSharingService proxyProvidesContentSharingService(LibraryModule libraryModule, Lazy<CommsIdentityManager> lazy, Lazy<CapabilitiesManager> lazy2, Lazy<IdentityService> lazy3, Lazy<Context> lazy4) {
        return (ContentSharingService) Preconditions.checkNotNull(libraryModule.providesContentSharingService(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ContentSharingService mo10268get() {
        return provideInstance(this.module, this.identityManagerProvider, this.capabilitiesManagerProvider, this.identityServiceProvider, this.contextProvider);
    }
}
