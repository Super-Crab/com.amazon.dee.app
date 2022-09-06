package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.SmartHomeProvisioningEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.connection.BasicBleDeviceFactory;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WorkflowModule_ProvidesPhilipsZigbeeBleWorkflowFactory implements Factory<PhilipsZigbeeBleWorkflow> {
    private final Provider<BasicBleDeviceFactory> basicBleDeviceFactoryProvider;
    private final Provider<DSHSSetCredentialsAPI> dshsSetCredentialsAPIProvider;
    private final Provider<DSSClient> dssClientProvider;
    private final Provider<Single<FFSArcusSettings>> ffsArcusSettingsSingleProvider;
    private final Provider<Gson> gsonProvider;
    private final WorkflowModule module;
    private final Provider<ProvisionerInfo> provisionerInfoProvider;
    private final Provider<SmartHomeProvisioningEventReporter> smartHomeProvisioningEventReporterProvider;
    private final Provider<WorkflowIdProvider> workflowIdProvider;

    public WorkflowModule_ProvidesPhilipsZigbeeBleWorkflowFactory(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisionerInfo> provider2, Provider<BasicBleDeviceFactory> provider3, Provider<WorkflowIdProvider> provider4, Provider<DSHSSetCredentialsAPI> provider5, Provider<SmartHomeProvisioningEventReporter> provider6, Provider<Single<FFSArcusSettings>> provider7, Provider<Gson> provider8) {
        this.module = workflowModule;
        this.dssClientProvider = provider;
        this.provisionerInfoProvider = provider2;
        this.basicBleDeviceFactoryProvider = provider3;
        this.workflowIdProvider = provider4;
        this.dshsSetCredentialsAPIProvider = provider5;
        this.smartHomeProvisioningEventReporterProvider = provider6;
        this.ffsArcusSettingsSingleProvider = provider7;
        this.gsonProvider = provider8;
    }

    public static WorkflowModule_ProvidesPhilipsZigbeeBleWorkflowFactory create(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisionerInfo> provider2, Provider<BasicBleDeviceFactory> provider3, Provider<WorkflowIdProvider> provider4, Provider<DSHSSetCredentialsAPI> provider5, Provider<SmartHomeProvisioningEventReporter> provider6, Provider<Single<FFSArcusSettings>> provider7, Provider<Gson> provider8) {
        return new WorkflowModule_ProvidesPhilipsZigbeeBleWorkflowFactory(workflowModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static PhilipsZigbeeBleWorkflow provideInstance(WorkflowModule workflowModule, Provider<DSSClient> provider, Provider<ProvisionerInfo> provider2, Provider<BasicBleDeviceFactory> provider3, Provider<WorkflowIdProvider> provider4, Provider<DSHSSetCredentialsAPI> provider5, Provider<SmartHomeProvisioningEventReporter> provider6, Provider<Single<FFSArcusSettings>> provider7, Provider<Gson> provider8) {
        return proxyProvidesPhilipsZigbeeBleWorkflow(workflowModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static PhilipsZigbeeBleWorkflow proxyProvidesPhilipsZigbeeBleWorkflow(WorkflowModule workflowModule, DSSClient dSSClient, ProvisionerInfo provisionerInfo, BasicBleDeviceFactory basicBleDeviceFactory, WorkflowIdProvider workflowIdProvider, DSHSSetCredentialsAPI dSHSSetCredentialsAPI, SmartHomeProvisioningEventReporter smartHomeProvisioningEventReporter, Single<FFSArcusSettings> single, Gson gson) {
        return (PhilipsZigbeeBleWorkflow) Preconditions.checkNotNull(workflowModule.providesPhilipsZigbeeBleWorkflow(dSSClient, provisionerInfo, basicBleDeviceFactory, workflowIdProvider, dSHSSetCredentialsAPI, smartHomeProvisioningEventReporter, single, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhilipsZigbeeBleWorkflow mo10268get() {
        return provideInstance(this.module, this.dssClientProvider, this.provisionerInfoProvider, this.basicBleDeviceFactoryProvider, this.workflowIdProvider, this.dshsSetCredentialsAPIProvider, this.smartHomeProvisioningEventReporterProvider, this.ffsArcusSettingsSingleProvider, this.gsonProvider);
    }
}
