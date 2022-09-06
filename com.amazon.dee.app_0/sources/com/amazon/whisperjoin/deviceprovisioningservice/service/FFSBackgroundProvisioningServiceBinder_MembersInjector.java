package com.amazon.whisperjoin.deviceprovisioningservice.service;

import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class FFSBackgroundProvisioningServiceBinder_MembersInjector implements MembersInjector<FFSBackgroundProvisioningServiceBinder> {
    private final Provider<MapAuthenticationProvider> mMapAuthenticationProvider;
    private final Provider<SharedPreferencesProvider> mSharedPreferencesProvider;

    public FFSBackgroundProvisioningServiceBinder_MembersInjector(Provider<SharedPreferencesProvider> provider, Provider<MapAuthenticationProvider> provider2) {
        this.mSharedPreferencesProvider = provider;
        this.mMapAuthenticationProvider = provider2;
    }

    public static MembersInjector<FFSBackgroundProvisioningServiceBinder> create(Provider<SharedPreferencesProvider> provider, Provider<MapAuthenticationProvider> provider2) {
        return new FFSBackgroundProvisioningServiceBinder_MembersInjector(provider, provider2);
    }

    public static void injectMMapAuthenticationProvider(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder, MapAuthenticationProvider mapAuthenticationProvider) {
        fFSBackgroundProvisioningServiceBinder.mMapAuthenticationProvider = mapAuthenticationProvider;
    }

    public static void injectMSharedPreferencesProvider(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder, SharedPreferencesProvider sharedPreferencesProvider) {
        fFSBackgroundProvisioningServiceBinder.mSharedPreferencesProvider = sharedPreferencesProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder) {
        injectMSharedPreferencesProvider(fFSBackgroundProvisioningServiceBinder, this.mSharedPreferencesProvider.mo10268get());
        injectMMapAuthenticationProvider(fFSBackgroundProvisioningServiceBinder, this.mMapAuthenticationProvider.mo10268get());
    }
}
