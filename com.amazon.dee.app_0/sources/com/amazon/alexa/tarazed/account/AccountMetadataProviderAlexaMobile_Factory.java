package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AccountMetadataProviderAlexaMobile_Factory implements Factory<AccountMetadataProviderAlexaMobile> {
    private final Provider<DeviceInformation> alexaMobileDeviceInformationProvider;
    private final Provider<IdentityService> identityServiceProvider;

    public AccountMetadataProviderAlexaMobile_Factory(Provider<IdentityService> provider, Provider<DeviceInformation> provider2) {
        this.identityServiceProvider = provider;
        this.alexaMobileDeviceInformationProvider = provider2;
    }

    public static AccountMetadataProviderAlexaMobile_Factory create(Provider<IdentityService> provider, Provider<DeviceInformation> provider2) {
        return new AccountMetadataProviderAlexaMobile_Factory(provider, provider2);
    }

    public static AccountMetadataProviderAlexaMobile newAccountMetadataProviderAlexaMobile(IdentityService identityService, DeviceInformation deviceInformation) {
        return new AccountMetadataProviderAlexaMobile(identityService, deviceInformation);
    }

    public static AccountMetadataProviderAlexaMobile provideInstance(Provider<IdentityService> provider, Provider<DeviceInformation> provider2) {
        return new AccountMetadataProviderAlexaMobile(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccountMetadataProviderAlexaMobile mo10268get() {
        return provideInstance(this.identityServiceProvider, this.alexaMobileDeviceInformationProvider);
    }
}
