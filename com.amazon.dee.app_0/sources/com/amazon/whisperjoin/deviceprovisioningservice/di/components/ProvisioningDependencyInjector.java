package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisioningInjectorScope;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenter;
import dagger.Component;
@Component(dependencies = {ProvisioningComponent.class})
@ProvisioningInjectorScope
/* loaded from: classes13.dex */
public interface ProvisioningDependencyInjector {
    void inject(AutoDiscoveryPresenter autoDiscoveryPresenter);

    void inject(ControlledSetupPresenter controlledSetupPresenter);
}
