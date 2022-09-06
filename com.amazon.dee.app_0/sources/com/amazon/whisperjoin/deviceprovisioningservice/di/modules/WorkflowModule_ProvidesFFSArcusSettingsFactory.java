package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesFFSArcusSettingsFactory implements Factory<Single<FFSArcusSettings>> {
    private final WorkflowModule module;

    public WorkflowModule_ProvidesFFSArcusSettingsFactory(WorkflowModule workflowModule) {
        this.module = workflowModule;
    }

    public static WorkflowModule_ProvidesFFSArcusSettingsFactory create(WorkflowModule workflowModule) {
        return new WorkflowModule_ProvidesFFSArcusSettingsFactory(workflowModule);
    }

    public static Single<FFSArcusSettings> provideInstance(WorkflowModule workflowModule) {
        return proxyProvidesFFSArcusSettings(workflowModule);
    }

    public static Single<FFSArcusSettings> proxyProvidesFFSArcusSettings(WorkflowModule workflowModule) {
        return (Single) Preconditions.checkNotNull(workflowModule.providesFFSArcusSettings(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Single<FFSArcusSettings> mo10268get() {
        return provideInstance(this.module);
    }
}
