package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncJobService;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisionerServicesInjectorScope;
import com.amazon.whisperjoin.deviceprovisioningservice.service.FFSProvisioningService;
import com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.FFSWhiteListJobService;
import com.amazon.whisperjoin.deviceprovisioningservice.smarthome.AssociatedCredentialsSyncService;
import dagger.Component;
@Component(dependencies = {ProvisionerServicesComponent.class})
@ProvisionerServicesInjectorScope
/* loaded from: classes13.dex */
public interface ProvisionerServicesDependencyInjector {
    void inject(FFSArcusSyncJobService fFSArcusSyncJobService);

    void inject(FFSProvisioningService fFSProvisioningService);

    void inject(FFSWhiteListJobService fFSWhiteListJobService);

    void inject(AssociatedCredentialsSyncService associatedCredentialsSyncService);
}
