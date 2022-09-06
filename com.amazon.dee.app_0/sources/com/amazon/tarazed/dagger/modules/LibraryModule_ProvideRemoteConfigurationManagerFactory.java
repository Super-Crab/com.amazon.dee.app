package com.amazon.tarazed.dagger.modules;

import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class LibraryModule_ProvideRemoteConfigurationManagerFactory implements Factory<RemoteConfigurationManager> {
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoUtilityProvider;
    private final LibraryModule module;

    public LibraryModule_ProvideRemoteConfigurationManagerFactory(LibraryModule libraryModule, Provider<DeviceInfoUtilityAndroid> provider) {
        this.module = libraryModule;
        this.deviceInfoUtilityProvider = provider;
    }

    public static LibraryModule_ProvideRemoteConfigurationManagerFactory create(LibraryModule libraryModule, Provider<DeviceInfoUtilityAndroid> provider) {
        return new LibraryModule_ProvideRemoteConfigurationManagerFactory(libraryModule, provider);
    }

    public static RemoteConfigurationManager provideInstance(LibraryModule libraryModule, Provider<DeviceInfoUtilityAndroid> provider) {
        return proxyProvideRemoteConfigurationManager(libraryModule, provider.mo10268get());
    }

    public static RemoteConfigurationManager proxyProvideRemoteConfigurationManager(LibraryModule libraryModule, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        return (RemoteConfigurationManager) Preconditions.checkNotNull(libraryModule.provideRemoteConfigurationManager(deviceInfoUtilityAndroid), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteConfigurationManager mo10268get() {
        return provideInstance(this.module, this.deviceInfoUtilityProvider);
    }
}
