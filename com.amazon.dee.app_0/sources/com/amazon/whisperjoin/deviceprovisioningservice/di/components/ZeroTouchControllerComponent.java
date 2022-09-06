package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ZeroTouchDependencyInjectorScope;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController;
import dagger.Component;
@Component(dependencies = {ProvisioningComponent.class}, modules = {ZeroTouchProvisioningModule.class})
@ZeroTouchDependencyInjectorScope
/* loaded from: classes13.dex */
public interface ZeroTouchControllerComponent {
    ZeroTouchWorkflowController getZeroTouchWorkflowController();
}
