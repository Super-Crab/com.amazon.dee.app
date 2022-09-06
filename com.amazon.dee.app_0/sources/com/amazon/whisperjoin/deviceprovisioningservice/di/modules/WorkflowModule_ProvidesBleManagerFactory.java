package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperbridge.ble.BleManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesBleManagerFactory implements Factory<BleManager> {
    private final Provider<Context> contextProvider;
    private final WorkflowModule module;

    public WorkflowModule_ProvidesBleManagerFactory(WorkflowModule workflowModule, Provider<Context> provider) {
        this.module = workflowModule;
        this.contextProvider = provider;
    }

    public static WorkflowModule_ProvidesBleManagerFactory create(WorkflowModule workflowModule, Provider<Context> provider) {
        return new WorkflowModule_ProvidesBleManagerFactory(workflowModule, provider);
    }

    public static BleManager provideInstance(WorkflowModule workflowModule, Provider<Context> provider) {
        return proxyProvidesBleManager(workflowModule, provider.mo10268get());
    }

    public static BleManager proxyProvidesBleManager(WorkflowModule workflowModule, Context context) {
        return (BleManager) Preconditions.checkNotNull(workflowModule.providesBleManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BleManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
