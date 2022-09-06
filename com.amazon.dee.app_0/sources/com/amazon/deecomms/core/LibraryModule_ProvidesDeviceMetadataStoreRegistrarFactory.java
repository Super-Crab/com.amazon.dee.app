package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesDeviceMetadataStoreRegistrarFactory implements Factory<DeviceMetadataStoreRegistrar> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesDeviceMetadataStoreRegistrarFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CapabilitiesManager> provider3) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.capabilitiesManagerProvider = provider3;
    }

    public static LibraryModule_ProvidesDeviceMetadataStoreRegistrarFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CapabilitiesManager> provider3) {
        return new LibraryModule_ProvidesDeviceMetadataStoreRegistrarFactory(libraryModule, provider, provider2, provider3);
    }

    public static DeviceMetadataStoreRegistrar provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CapabilitiesManager> provider3) {
        return (DeviceMetadataStoreRegistrar) Preconditions.checkNotNull(libraryModule.providesDeviceMetadataStoreRegistrar(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceMetadataStoreRegistrar proxyProvidesDeviceMetadataStoreRegistrar(LibraryModule libraryModule, Context context, CommsIdentityManager commsIdentityManager, CapabilitiesManager capabilitiesManager) {
        return (DeviceMetadataStoreRegistrar) Preconditions.checkNotNull(libraryModule.providesDeviceMetadataStoreRegistrar(context, commsIdentityManager, capabilitiesManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceMetadataStoreRegistrar mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.commsIdentityManagerProvider, this.capabilitiesManagerProvider);
    }
}
