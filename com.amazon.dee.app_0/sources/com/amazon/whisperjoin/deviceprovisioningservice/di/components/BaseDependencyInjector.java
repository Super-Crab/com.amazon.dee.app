package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.BaseScope;
import com.amazon.whisperjoin.deviceprovisioningservice.service.FFSBackgroundProvisioningServiceBinder;
import dagger.Component;
@Component(dependencies = {BaseComponent.class})
@BaseScope
/* loaded from: classes13.dex */
public interface BaseDependencyInjector {
    void inject(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder);
}
