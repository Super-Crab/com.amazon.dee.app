package com.amazon.whisperjoin.deviceprovisioningservice.workflow;

import android.util.Base64;
import com.amazon.devicesetupservice.smarthome.BLEDeviceCredential;
import com.amazon.devicesetupservice.smarthome.CredentialRequest;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsV2Output;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.amazon.devicesetupservice.smarthome.SmartHomeCredential;
import com.amazon.devicesetupservice.smarthome.SmartHomeDeviceCredentials;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.devices.ThirdPartyPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.BluetoothFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.data.ZigbeeFFSEntry;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.intent.DSHSSetCredentialsAPI;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceProvisioningDeferredException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.NoAssociatedDeviceCredentialsException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.UnableToEstablishConnectionException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.SmartHomeProvisioningEventReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflow;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredSmartHomeProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredSmartHomeProvisioneeResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsResponse;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.connection.BasicBleDeviceFactory;
import com.amazon.whisperjoin.util.rx.RxLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class PhilipsZigbeeBleWorkflow implements ZeroTouchWorkflow<ThirdPartyPeripheralDeviceDetails> {
    private static final long CONNECTION_RETRY_COUNT = 2;
    private static final long CONNECTION_TIMEOUT_SECONDS = 20;
    private static final String TAG = "PhilipsZigbeeBleWorkflow";
    private final BasicBleDeviceFactory mBasicBLEConnectionFactory;
    private final DSHSSetCredentialsAPI mDSHSSetCredentialsAPI;
    private final DSSClient mDSSClient;
    private final Single<FFSArcusSettings> mFFSArcusSettingsSingle;
    private final Gson mGson;
    private final ProvisionerInfo mProvisionerInfo;
    private final SmartHomeProvisioningEventReporter mSmartHomeProvisioningEventReporter;
    private final String mWorkflowId;
    private static final UUID PHILLIPS_CONFIGURATION_SERVICE_UUID = UUID.fromString("0000FE0F-0000-1000-8000-00805F9B34FB");
    private static final UUID ZIGBEE_ID_CHARACTERISTIC = UUID.fromString("97FE6561-0001-4F62-86E9-B71EE2DA3D22");

    public PhilipsZigbeeBleWorkflow(DSSClient dSSClient, ProvisionerInfo provisionerInfo, BasicBleDeviceFactory basicBleDeviceFactory, WorkflowIdProvider workflowIdProvider, DSHSSetCredentialsAPI dSHSSetCredentialsAPI, SmartHomeProvisioningEventReporter smartHomeProvisioningEventReporter, Single<FFSArcusSettings> single, Gson gson) {
        this.mDSSClient = dSSClient;
        this.mProvisionerInfo = provisionerInfo;
        this.mBasicBLEConnectionFactory = basicBleDeviceFactory;
        this.mWorkflowId = workflowIdProvider.createWorkflowId();
        this.mDSHSSetCredentialsAPI = dSHSSetCredentialsAPI;
        this.mSmartHomeProvisioningEventReporter = smartHomeProvisioningEventReporter;
        this.mFFSArcusSettingsSingle = single;
        this.mGson = gson;
    }

    private GetCustomerDevicesCredentialsRequest createGetCustomerDevicesCredentialsRequest(String str) {
        CredentialRequest credentialRequest = new CredentialRequest();
        credentialRequest.setProtocolType(ProtocolType.ZIGBEE);
        credentialRequest.setAuthMaterialIdList(Collections.singletonList(str));
        return new GetCustomerDevicesCredentialsRequest(Collections.singletonList(credentialRequest), this.mProvisionerInfo);
    }

    private Observable<ZeroTouchWorkflowUpdate> createUpdateObservable(ZeroTouchWorkflowUpdate.State state) {
        return Observable.just(createWorkflowUpdate(state));
    }

    private ZeroTouchWorkflowUpdate createWorkflowUpdate(ZeroTouchWorkflowUpdate.State state) {
        return createWorkflowUpdate(state, null);
    }

    private Single<BasicBLEDevice> establishConnection(final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        return Single.defer(new Supplier<SingleSource<BasicBLEDevice>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<BasicBLEDevice> mo10365get() throws Exception {
                final BasicBLEDevice createBleDevice = PhilipsZigbeeBleWorkflow.this.mBasicBLEConnectionFactory.createBleDevice(thirdPartyPeripheralDeviceDetails.getBluetoothDevice());
                return createBleDevice.waitForConnection(true).timeout(20L, TimeUnit.SECONDS).onErrorResumeNext(new Function<Throwable, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.6.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public CompletableSource mo10358apply(Throwable th) throws Exception {
                        WJLog.d(PhilipsZigbeeBleWorkflow.TAG, "Connecting timed out, disconnecting and cleaning up attempt.");
                        return createBleDevice.disconnect().andThen(Completable.error(new UnableToEstablishConnectionException(th)));
                    }
                }).andThen(Single.just(createBleDevice));
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Attempting to establish connection to device.")).retry(2L).doOnSuccess(new Consumer<BasicBLEDevice>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(BasicBLEDevice basicBLEDevice) throws Exception {
                WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "Connection Success");
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportConnectionSuccess();
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) throws Exception {
                WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "Connection Failure");
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportConnectionFailure(th);
            }
        });
    }

    private Observable<ZeroTouchWorkflowUpdate> executeWorkflow(final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        return establishConnection(thirdPartyPeripheralDeviceDetails).flatMapObservable(new Function<BasicBLEDevice, ObservableSource<ZeroTouchWorkflowUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<ZeroTouchWorkflowUpdate> mo10358apply(BasicBLEDevice basicBLEDevice) throws Exception {
                return PhilipsZigbeeBleWorkflow.this.provisionConnectedPhilipsDevice(basicBLEDevice, thirdPartyPeripheralDeviceDetails);
            }
        }).startWith(Observable.just(createWorkflowUpdate(ZeroTouchWorkflowUpdate.State.CONNECTING)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getMacAddressFromBytes(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int length = bArr.length - 1; length >= 0; length--) {
            sb.append(String.format(Locale.ENGLISH, "%02X", Byte.valueOf(bArr[length])));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String insertColonsIntoZigbeeMacString(String str) {
        return GeneratedOutlineSupport1.outline50(str.replaceAll("(.{2})", "$1:"), -1, 0);
    }

    private Observable<ZeroTouchWorkflowUpdate> prepareEstablishedConnection(BasicBLEDevice basicBLEDevice) {
        return basicBLEDevice.discoverServices().doOnSubscribe(RxLog.doOnSubscribe(TAG, "Preparing established connection by discovering services.")).andThen(Observable.empty());
    }

    private Observable<ZeroTouchWorkflowUpdate> prepareWorkflow(final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        return this.mDSSClient.discoveredSmartHomeProvisionee(new DiscoveredSmartHomeProvisioneeRequest(ProtocolType.BLE, thirdPartyPeripheralDeviceDetails.getMacAddress(), thirdPartyPeripheralDeviceDetails.getRSSI(), this.mProvisionerInfo)).flatMapCompletable(new Function<DiscoveredSmartHomeProvisioneeResponse, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(DiscoveredSmartHomeProvisioneeResponse discoveredSmartHomeProvisioneeResponse) throws Exception {
                if (discoveredSmartHomeProvisioneeResponse.isCanProceed()) {
                    PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.registerDeviceForEventReporting(discoveredSmartHomeProvisioneeResponse.getProvisionerReportUrl(), thirdPartyPeripheralDeviceDetails.getMacAddress());
                    return Completable.complete();
                }
                throw new DeviceProvisioningDeferredException(discoveredSmartHomeProvisioneeResponse.getWaitTimeMs());
            }
        }).startWith(Observable.just(createWorkflowUpdate(ZeroTouchWorkflowUpdate.State.PREPARING)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ObservableSource<ZeroTouchWorkflowUpdate> provisionConnectedPhilipsDevice(final BasicBLEDevice basicBLEDevice, ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        return Observable.concat(prepareEstablishedConnection(basicBLEDevice), provisionPhilipsDevice(basicBLEDevice, thirdPartyPeripheralDeviceDetails)).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Provisioning connected Philips device.")).doOnError(RxLog.doOnError(TAG, "Error occurred while provisioning")).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ZeroTouchWorkflowUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.7
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends ZeroTouchWorkflowUpdate> mo10358apply(Throwable th) throws Exception {
                return basicBLEDevice.disconnect().andThen(Observable.error(th));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void provisionDeviceCredentialsToDSHS(GetCustomerDevicesCredentialsResponse getCustomerDevicesCredentialsResponse, ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        List<SmartHomeCredential> associatedCredentials = getCustomerDevicesCredentialsResponse.getAssociatedCredentials();
        if (associatedCredentials.size() != 1) {
            WJLog.w(TAG, "More than one credentials returned from DSS, only using first one");
        }
        String authMaterialId = associatedCredentials.get(0).getAuthMaterialId();
        String authMaterialData = associatedCredentials.get(0).getAuthMaterialData();
        String macAddress = thirdPartyPeripheralDeviceDetails.getMacAddress();
        int rssi = thirdPartyPeripheralDeviceDetails.getRSSI();
        long timestampNanos = thirdPartyPeripheralDeviceDetails.getBleScanData().getTimestampNanos();
        byte[] scanRecord = thirdPartyPeripheralDeviceDetails.getScanRecord();
        WJLog.d(TAG, String.format(Locale.ENGLISH, "Calling DSHS Add Device API. ZMac [%s], ZInstall [%s], BLEMac [%s], RSSI [%d], timeStamp [%d], scanRecord[%s]", authMaterialId, authMaterialData, macAddress, Integer.valueOf(rssi), Long.valueOf(timestampNanos), Base64.encodeToString(scanRecord, 2)));
        this.mDSHSSetCredentialsAPI.setCredentials(new ZigbeeFFSEntry(Collections.singletonList(new ZigbeeFFSEntry.ZigbeeAuthMaterial(authMaterialId, authMaterialData))), new BluetoothFFSEntry(Collections.singletonList(new BluetoothFFSEntry.BluetoothAuthMaterial(macAddress, rssi, timestampNanos, scanRecord))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void provisionDeviceCredentialsToDSHSv2(ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        String macAddress = thirdPartyPeripheralDeviceDetails.getMacAddress();
        int rssi = thirdPartyPeripheralDeviceDetails.getRSSI();
        long timestampNanos = thirdPartyPeripheralDeviceDetails.getBleScanData().getTimestampNanos();
        byte[] scanRecord = thirdPartyPeripheralDeviceDetails.getScanRecord();
        WJLog.d(TAG, String.format(Locale.ENGLISH, "Calling DSHS Add Device API. BLEMac [%s], RSSI [%d], timeStamp [%d], scanRecord[%s]", macAddress, Integer.valueOf(rssi), Long.valueOf(timestampNanos), Base64.encodeToString(scanRecord, 2)));
        BLEDeviceCredential bLEDeviceCredential = new BLEDeviceCredential();
        bLEDeviceCredential.setMacAddress(macAddress);
        bLEDeviceCredential.setRssi(rssi);
        bLEDeviceCredential.setTimestamp((int) thirdPartyPeripheralDeviceDetails.getBleScanData().getTimestampNanos());
        bLEDeviceCredential.setScanRecord(Base64.encodeToString(scanRecord, 2));
        SmartHomeDeviceCredentials smartHomeDeviceCredentials = new SmartHomeDeviceCredentials();
        smartHomeDeviceCredentials.setBle(Collections.singletonList(bLEDeviceCredential));
        GetCustomerDevicesCredentialsV2Output getCustomerDevicesCredentialsV2Output = new GetCustomerDevicesCredentialsV2Output();
        getCustomerDevicesCredentialsV2Output.setCredentials(smartHomeDeviceCredentials);
        getCustomerDevicesCredentialsV2Output.setVersion("2");
        this.mDSHSSetCredentialsAPI.setCredentialsV2(this.mGson.toJson(getCustomerDevicesCredentialsV2Output));
    }

    private Observable<ZeroTouchWorkflowUpdate> provisionPhilipsDevice(final BasicBLEDevice basicBLEDevice, final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        return basicBLEDevice.readCharacteristic(PHILLIPS_CONFIGURATION_SERVICE_UUID, ZIGBEE_ID_CHARACTERISTIC).map(new Function<byte[], String>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.13
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public String mo10358apply(byte[] bArr) throws Exception {
                return PhilipsZigbeeBleWorkflow.getMacAddressFromBytes(bArr);
            }
        }).doOnSuccess(new Consumer<String>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.12
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(String str) throws Exception {
                WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "Retrieved provisioning details");
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportRetrievedProvisioningDetailsSuccess(PhilipsZigbeeBleWorkflow.insertColonsIntoZigbeeMacString(str));
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.11
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) throws Exception {
                WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "Failed to get provisioning details");
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportRetrievedProvisioningDetailsFailure(th);
            }
        }).flatMapCompletable(new Function<String, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.10
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(String str) throws Exception {
                return PhilipsZigbeeBleWorkflow.this.verifyAssociationAndProvision(str, basicBLEDevice, thirdPartyPeripheralDeviceDetails);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.9
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportProvisionedSuccess();
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportDoneSuccess();
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Throwable th) throws Exception {
                PhilipsZigbeeBleWorkflow.this.mSmartHomeProvisioningEventReporter.reportProvisionedFailure(th);
            }
        }).andThen(createUpdateObservable(ZeroTouchWorkflowUpdate.State.SUCCESS)).startWith(createUpdateObservable(ZeroTouchWorkflowUpdate.State.PROVISIONING));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CompletableSource provisionUsingV1API(GetCustomerDevicesCredentialsRequest getCustomerDevicesCredentialsRequest, BasicBLEDevice basicBLEDevice, final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        WJLog.d(TAG, "Using V1 API");
        return basicBLEDevice.disconnect().andThen(this.mDSSClient.getCustomerDevicesCredentials(getCustomerDevicesCredentialsRequest)).doOnSuccess(RxLog.doOnSuccess(TAG, "Retrieved Zigbee Credentials from DSS.")).flatMapCompletable(new Function<GetCustomerDevicesCredentialsResponse, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.15
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(GetCustomerDevicesCredentialsResponse getCustomerDevicesCredentialsResponse) throws Exception {
                if (!getCustomerDevicesCredentialsResponse.getAssociatedCredentials().isEmpty()) {
                    WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "Associated device credentials found");
                    PhilipsZigbeeBleWorkflow.this.provisionDeviceCredentialsToDSHS(getCustomerDevicesCredentialsResponse, thirdPartyPeripheralDeviceDetails);
                    return Completable.complete();
                }
                WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "No associated credentials found");
                throw new NoAssociatedDeviceCredentialsException();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CompletableSource provisionUsingV2API(GetCustomerDevicesCredentialsRequest getCustomerDevicesCredentialsRequest, BasicBLEDevice basicBLEDevice, final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        WJLog.d(TAG, "Using V2 API");
        return basicBLEDevice.disconnect().andThen(this.mDSSClient.getCustomerDevicesCredentialsV2(getCustomerDevicesCredentialsRequest)).doOnSuccess(RxLog.doOnSuccess(TAG, "Retrieved Zigbee Credentials from DSS.")).flatMapCompletable(new Function<GetCustomerDevicesCredentialsV2Output, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.16
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(GetCustomerDevicesCredentialsV2Output getCustomerDevicesCredentialsV2Output) throws Exception {
                if (!getCustomerDevicesCredentialsV2Output.getCredentials().getZigbee().isEmpty()) {
                    WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "Associated device credentials found");
                    PhilipsZigbeeBleWorkflow.this.provisionDeviceCredentialsToDSHSv2(thirdPartyPeripheralDeviceDetails);
                    return Completable.complete();
                }
                WJLog.i(PhilipsZigbeeBleWorkflow.TAG, "No associated credentials found");
                throw new NoAssociatedDeviceCredentialsException();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CompletableSource verifyAssociationAndProvision(String str, final BasicBLEDevice basicBLEDevice, final ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        final GetCustomerDevicesCredentialsRequest createGetCustomerDevicesCredentialsRequest = createGetCustomerDevicesCredentialsRequest(str);
        return this.mFFSArcusSettingsSingle.flatMapCompletable(new Function<FFSArcusSettings, CompletableSource>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.14
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(FFSArcusSettings fFSArcusSettings) throws Exception {
                return fFSArcusSettings.getZigbeeArcusSettings().useV2Api() ? PhilipsZigbeeBleWorkflow.this.provisionUsingV2API(createGetCustomerDevicesCredentialsRequest, basicBLEDevice, thirdPartyPeripheralDeviceDetails) : PhilipsZigbeeBleWorkflow.this.provisionUsingV1API(createGetCustomerDevicesCredentialsRequest, basicBLEDevice, thirdPartyPeripheralDeviceDetails);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ZeroTouchWorkflowUpdate createWorkflowUpdate(ZeroTouchWorkflowUpdate.State state, Throwable th) {
        return new ZeroTouchWorkflowUpdate.Builder().setLocalWorkflowIdentifier(this.mWorkflowId).setWorkflowType(ZeroTouchWorkflowUpdate.WorkflowType.PHILIPS_ZIGBEE).setRadio(ZeroTouchWorkflowUpdate.Radio.BLE).setState(state).setThrowable(th).createZeroTouchWorkflowUpdate();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflow
    public Observable<ZeroTouchWorkflowUpdate> run(ThirdPartyPeripheralDeviceDetails thirdPartyPeripheralDeviceDetails) {
        return Observable.concat(prepareWorkflow(thirdPartyPeripheralDeviceDetails), executeWorkflow(thirdPartyPeripheralDeviceDetails)).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Starting Philips Workflow")).doOnNext(RxLog.doOnNext(TAG, "ZeroTouchWorkflowUpdate")).doOnError(RxLog.doOnError(TAG, "An error occurred while executing Philips Workflow")).doOnComplete(RxLog.doOnComplete(TAG, "Workflow Completed")).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ZeroTouchWorkflowUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.PhilipsZigbeeBleWorkflow.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends ZeroTouchWorkflowUpdate> mo10358apply(Throwable th) throws Exception {
                WJLog.e(PhilipsZigbeeBleWorkflow.TAG, "An error occurred while executing workflow", th);
                return Observable.just(PhilipsZigbeeBleWorkflow.this.createWorkflowUpdate(ZeroTouchWorkflowUpdate.State.FAILURE, th));
            }
        });
    }
}
