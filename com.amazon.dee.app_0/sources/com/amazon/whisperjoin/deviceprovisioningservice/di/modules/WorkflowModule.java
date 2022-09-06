package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporterImpl;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.WorkflowScope;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.util.LocationPermissionsHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.wifi.CurrentWifiNetworkProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowStateStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.WorkflowUpdateProducer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionsExecutor;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionsExecutorBuilder;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.BlessDiscoveredWhisperJoinDeviceOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ConnectToDeviceOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.DisconnectFromDeviceOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.GetProvisioningDetailsOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ProvisionDeviceOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StartDiscoveryOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.StopDiscoveryOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.TerminateSetupOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.VerifyProvisioningOperation;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingDeviceEvents;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification.ProvisioningVerificationUsingRegistrationService;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.GetAvailableWifiNetworksFromDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.wifi.SaveWifiNetworkThroughDSS;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.SmartHomeProvisioningEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowResultLogger;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.connection.BasicBleDeviceFactory;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Single;
@Module
/* loaded from: classes13.dex */
public class WorkflowModule {
    private final Single<FFSArcusSettings> mFFSArcusSettingsSingle;

    public WorkflowModule(Single<FFSArcusSettings> single) {
        this.mFFSArcusSettingsSingle = single;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public DeviceActionCreator providesActionCreator() {
        return new DeviceActionCreator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public BasicBleDeviceFactory providesBasicBleFactory(Context context, BleManager bleManager) {
        return new BasicBleDeviceFactory(context, bleManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public BleManager providesBleManager(Context context) {
        BleManager bleManager = new BleManager();
        try {
            bleManager.initializeBluetoothLE(context, null);
            return bleManager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public ControlledSetupWorkflow providesControlledSetupWorkflow(WorkflowStateStream workflowStateStream, DeviceActionCreator deviceActionCreator, WorkflowUpdateProducer workflowUpdateProducer, WorkflowConfiguration workflowConfiguration, DSSClient dSSClient, TrustProvider.TrustState trustState) {
        return new ControlledSetupWorkflow(workflowStateStream, deviceActionCreator, workflowUpdateProducer, workflowConfiguration, dSSClient, trustState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public DeviceActionsExecutor providesDeviceActionsExecutor(MapAuthenticationProvider mapAuthenticationProvider, ProvisioningManagerProvider provisioningManagerProvider, DeviceEventStream deviceEventStream, DSSClient dSSClient, ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration, ProvisionerClientData provisionerClientData, DiscoverySettings discoverySettings, BluetoothSupportProvider bluetoothSupportProvider, LocationPermissionsHelper locationPermissionsHelper, MetricsRecorderProvider metricsRecorderProvider, Clock clock, WJDeviceSetupModeSupportedPredicate wJDeviceSetupModeSupportedPredicate, ProvisioningMethod provisioningMethod, GetAvailableWifiNetworksFromDSS getAvailableWifiNetworksFromDSS, SaveWifiNetworkThroughDSS saveWifiNetworkThroughDSS, CurrentWifiNetworkProvider currentWifiNetworkProvider, DeferredDiscoveryHandler deferredDiscoveryHandler, Single<FFSArcusSettings> single) {
        return new DeviceActionsExecutorBuilder().setStartDiscoveryOperation(new StartDiscoveryOperation(provisioningManagerProvider, dSSClient, provisionerClientData, discoverySettings, metricsRecorderProvider, clock, provisioningMethod, locationPermissionsHelper, bluetoothSupportProvider, wJDeviceSetupModeSupportedPredicate, workflowConfiguration, single)).setStopDiscoveryOperation(new StopDiscoveryOperation(provisioningManagerProvider)).setBlessDiscoveredDeviceOperation(new BlessDiscoveredWhisperJoinDeviceOperation(dSSClient, provisionerClientData, provisioningMethod, provisioningManagerProvider, deferredDiscoveryHandler)).setConnectToDeviceDeviceOperation(new ConnectToDeviceOperation(metricsRecorderProvider)).setDisconnectFromDeviceOperation(new DisconnectFromDeviceOperation()).setGetProvisioningDetailsOperation(new GetProvisioningDetailsOperation(deviceEventStream, getAvailableWifiNetworksFromDSS, metricsRecorderProvider, dSSClient)).setProvisionDeviceOperation(new ProvisionDeviceOperation(mapAuthenticationProvider, dSSClient, provisioningServiceConfiguration, metricsRecorderProvider)).setVerifyProvisioningOperation(new VerifyProvisioningOperation(new ProvisioningVerificationUsingDeviceEvents(deviceEventStream, metricsRecorderProvider, currentWifiNetworkProvider), new ProvisioningVerificationUsingRegistrationService(), metricsRecorderProvider, saveWifiNetworkThroughDSS)).setTerminateSetupDeviceOperation(new TerminateSetupOperation(provisioningManagerProvider)).createDeviceActionsExecutor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public Single<FFSArcusSettings> providesFFSArcusSettings() {
        return this.mFFSArcusSettingsSingle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public GetAvailableWifiNetworksFromDSS providesGetAvailableWifiNetworksFromDSS(DSSClient dSSClient, CurrentWifiNetworkProvider currentWifiNetworkProvider, ProvisioningMethod provisioningMethod, TrustProvider.TrustState trustState) {
        return new GetAvailableWifiNetworksFromDSS(dSSClient, currentWifiNetworkProvider, provisioningMethod, trustState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public PhilipsZigbeeBleWorkflow providesPhilipsZigbeeBleWorkflow(DSSClient dSSClient, ProvisionerInfo provisionerInfo, BasicBleDeviceFactory basicBleDeviceFactory, WorkflowIdProvider workflowIdProvider, DSHSSetCredentialsAPI dSHSSetCredentialsAPI, SmartHomeProvisioningEventReporter smartHomeProvisioningEventReporter, Single<FFSArcusSettings> single, Gson gson) {
        return new PhilipsZigbeeBleWorkflow(dSSClient, provisionerInfo, basicBleDeviceFactory, workflowIdProvider, dSHSSetCredentialsAPI, smartHomeProvisioningEventReporter, single, gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public SaveWifiNetworkThroughDSS providesSaveWifiNetworkToDSS(DSSClient dSSClient, ProvisioningMethod provisioningMethod, TrustProvider.TrustState trustState) {
        return new SaveWifiNetworkThroughDSS(dSSClient, provisioningMethod, trustState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public SmartHomeProvisioningEventReporter providesSmartHomeProvisioningEventReporter(ProvisionerInfo provisionerInfo, WJErrorMapper<Throwable> wJErrorMapper, DSSClient dSSClient) {
        return new SmartHomeProvisioningEventReporter(provisionerInfo, wJErrorMapper, dSSClient);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public WifiSimpleSetupZeroTouchWorkflow providesWifiSimpleSetupWorkflow(WorkflowStateStream workflowStateStream, DeviceActionCreator deviceActionCreator, TrustProvider.TrustState trustState, WorkflowIdProvider workflowIdProvider) {
        return new WifiSimpleSetupZeroTouchWorkflow(workflowStateStream, deviceActionCreator, trustState, workflowIdProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public WorkflowEventReporter providesWorkflowEventReporter(DSSClient dSSClient, ProvisionerInfo provisionerInfo, ProvisioningMethod provisioningMethod, WJErrorMapper<Throwable> wJErrorMapper) {
        return new WorkflowEventReporter(new ProvisionerEventReporterImpl(dSSClient, provisionerInfo), provisioningMethod, wJErrorMapper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public WorkflowIdProvider providesWorkflowIdProvider() {
        return new WorkflowIdProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public WorkflowResultLogger providesWorkflowResultLogger(ProvisioningMethod provisioningMethod, WJErrorMapper<Throwable> wJErrorMapper) {
        return new WorkflowResultLogger(provisioningMethod, wJErrorMapper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public WorkflowStateStream providesWorkflowStateStream(DeviceActionCreator deviceActionCreator, DeviceActionsExecutor deviceActionsExecutor, DeviceEventStream deviceEventStream, WorkflowEventReporter workflowEventReporter, WorkflowResultLogger workflowResultLogger) {
        return new WorkflowStateStream(deviceActionCreator.getDeviceActionStream(), deviceActionsExecutor, deviceEventStream, workflowEventReporter, workflowResultLogger);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WorkflowScope
    public WorkflowUpdateProducer providesWorkflowUpdateProducer(DeviceActionCreator deviceActionCreator, WJErrorMapper<Throwable> wJErrorMapper) {
        return new WorkflowUpdateProducer(deviceActionCreator, wJErrorMapper);
    }
}
