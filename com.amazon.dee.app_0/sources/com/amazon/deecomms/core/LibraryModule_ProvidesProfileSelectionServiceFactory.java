package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.ProfileSelectionService;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesProfileSelectionServiceFactory implements Factory<ProfileSelectionService> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceMetadataStoreRegistrar> deviceMetadataStoreRegistrarProvider;
    private final LibraryModule module;
    private final Provider<OOBEPersonManager> oobePersonManagerProvider;

    public LibraryModule_ProvidesProfileSelectionServiceFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3, Provider<DeviceMetadataStoreRegistrar> provider4, Provider<OOBEPersonManager> provider5) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.deviceMetadataStoreRegistrarProvider = provider4;
        this.oobePersonManagerProvider = provider5;
    }

    public static LibraryModule_ProvidesProfileSelectionServiceFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3, Provider<DeviceMetadataStoreRegistrar> provider4, Provider<OOBEPersonManager> provider5) {
        return new LibraryModule_ProvidesProfileSelectionServiceFactory(libraryModule, provider, provider2, provider3, provider4, provider5);
    }

    public static ProfileSelectionService provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3, Provider<DeviceMetadataStoreRegistrar> provider4, Provider<OOBEPersonManager> provider5) {
        return (ProfileSelectionService) Preconditions.checkNotNull(libraryModule.providesProfileSelectionService(provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ProfileSelectionService proxyProvidesProfileSelectionService(LibraryModule libraryModule, Context context, Lazy<CapabilitiesManager> lazy, Lazy<CommsIdentityManager> lazy2, Lazy<DeviceMetadataStoreRegistrar> lazy3, Lazy<OOBEPersonManager> lazy4) {
        return (ProfileSelectionService) Preconditions.checkNotNull(libraryModule.providesProfileSelectionService(context, lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ProfileSelectionService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.capabilitiesManagerProvider, this.commsIdentityManagerProvider, this.deviceMetadataStoreRegistrarProvider, this.oobePersonManagerProvider);
    }
}
