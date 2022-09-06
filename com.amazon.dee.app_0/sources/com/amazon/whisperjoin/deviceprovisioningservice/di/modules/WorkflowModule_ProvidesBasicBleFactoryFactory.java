package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.connection.BasicBleDeviceFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesBasicBleFactoryFactory implements Factory<BasicBleDeviceFactory> {
    private final Provider<BleManager> bleManagerProvider;
    private final Provider<Context> contextProvider;
    private final WorkflowModule module;

    public WorkflowModule_ProvidesBasicBleFactoryFactory(WorkflowModule workflowModule, Provider<Context> provider, Provider<BleManager> provider2) {
        this.module = workflowModule;
        this.contextProvider = provider;
        this.bleManagerProvider = provider2;
    }

    public static WorkflowModule_ProvidesBasicBleFactoryFactory create(WorkflowModule workflowModule, Provider<Context> provider, Provider<BleManager> provider2) {
        return new WorkflowModule_ProvidesBasicBleFactoryFactory(workflowModule, provider, provider2);
    }

    public static BasicBleDeviceFactory provideInstance(WorkflowModule workflowModule, Provider<Context> provider, Provider<BleManager> provider2) {
        return proxyProvidesBasicBleFactory(workflowModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static BasicBleDeviceFactory proxyProvidesBasicBleFactory(WorkflowModule workflowModule, Context context, BleManager bleManager) {
        return (BasicBleDeviceFactory) Preconditions.checkNotNull(workflowModule.providesBasicBleFactory(context, bleManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BasicBleDeviceFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.bleManagerProvider);
    }
}
