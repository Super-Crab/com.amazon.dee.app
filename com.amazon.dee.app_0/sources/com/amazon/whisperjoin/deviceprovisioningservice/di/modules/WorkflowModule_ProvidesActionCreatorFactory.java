package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesActionCreatorFactory implements Factory<DeviceActionCreator> {
    private final WorkflowModule module;

    public WorkflowModule_ProvidesActionCreatorFactory(WorkflowModule workflowModule) {
        this.module = workflowModule;
    }

    public static WorkflowModule_ProvidesActionCreatorFactory create(WorkflowModule workflowModule) {
        return new WorkflowModule_ProvidesActionCreatorFactory(workflowModule);
    }

    public static DeviceActionCreator provideInstance(WorkflowModule workflowModule) {
        return proxyProvidesActionCreator(workflowModule);
    }

    public static DeviceActionCreator proxyProvidesActionCreator(WorkflowModule workflowModule) {
        return (DeviceActionCreator) Preconditions.checkNotNull(workflowModule.providesActionCreator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DeviceActionCreator mo10268get() {
        return provideInstance(this.module);
    }
}
