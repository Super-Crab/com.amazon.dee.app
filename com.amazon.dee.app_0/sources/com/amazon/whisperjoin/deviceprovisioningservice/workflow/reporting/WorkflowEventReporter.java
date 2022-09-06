package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetupservice.reporting.Radio;
import com.amazon.devicesetupservice.v1.CredentialLockerUsageInfo;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionerEventReporter;
import com.amazon.whisperjoin.common.sharedtypes.reporting.ReportingDataTypesBuilder;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ConnectionFailureDuringSetup;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableRegistrationError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.util.dss.DSSTypesHelper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetwork;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ConnectionOperationStatusUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorUtils;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
/* loaded from: classes13.dex */
public class WorkflowEventReporter extends AbstractDeviceEventAccumulator<Void> implements Consumer<WJWorkflowStateStore> {
    private static final String TAG = "WorkflowEventReporter";
    private final ProvisionerEventReporter mProvisionerEventReporter;
    private final ProvisioningMethod mProvisioningMethod;
    private final WJErrorMapper<Throwable> mWJErrorMapper;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.WorkflowEventReporter$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$provisioning$type$ConnectionOperationStatusUpdate$State;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState = new int[DeviceSession.DeviceState.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State;

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.GETTING_PROVISIONING_DETAILS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.PROVISIONING_DETAILS_RECEIVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.PROVISIONING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.PROVISIONED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.VERIFYING_PROVISIONING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$DeviceSession$DeviceState[DeviceSession.DeviceState.VERIFIED_PROVISIONING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State = new int[Event.State.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$provisioning$type$ConnectionOperationStatusUpdate$State = new int[ConnectionOperationStatusUpdate.State.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$provisioning$type$ConnectionOperationStatusUpdate$State[ConnectionOperationStatusUpdate.State.CONNECTING_TO_DEVICE_FAILURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$provisioning$type$ConnectionOperationStatusUpdate$State[ConnectionOperationStatusUpdate.State.SECURE_CHANNEL_ESTABLISHMENT_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public WorkflowEventReporter(ProvisionerEventReporter provisionerEventReporter, ProvisioningMethod provisioningMethod, WJErrorMapper<Throwable> wJErrorMapper) {
        this.mProvisionerEventReporter = provisionerEventReporter;
        this.mProvisioningMethod = provisioningMethod;
        this.mWJErrorMapper = wJErrorMapper;
    }

    private ErrorInfo createErrorInfo(Throwable th, String str) {
        if (th instanceof DeviceError) {
            str = ((DeviceError) th).getOperation();
        }
        if (th instanceof ConnectionFailureDuringSetup) {
            str = ((ConnectionFailureDuringSetup) th).getDeviceSession().getState().name();
        }
        Throwable rootCause = WJErrorUtils.getRootCause(th);
        ErrorInfo buildErrorInfo = ReportingDataTypesBuilder.buildErrorInfo(str, rootCause, this.mWJErrorMapper.map(rootCause).getErrorCode());
        if (th instanceof ProvisionableRegistrationError) {
            buildErrorInfo.setDetails(th.toString());
        }
        return buildErrorInfo;
    }

    private CredentialLockerUsageInfo getCredentialLockerUsageInfo(WifiConfiguration wifiConfiguration, AvailableWifiNetworks availableWifiNetworks, boolean z) {
        Boolean bool;
        if (wifiConfiguration == null || availableWifiNetworks == null) {
            return null;
        }
        Boolean bool2 = Boolean.FALSE;
        List<AvailableWifiNetwork> configuredNetworks = availableWifiNetworks.getConfiguredNetworks();
        if (configuredNetworks != null && !configuredNetworks.isEmpty()) {
            Iterator<AvailableWifiNetwork> it2 = configuredNetworks.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    bool = bool2;
                    break;
                }
                AvailableWifiNetwork next = it2.next();
                if (StringUtils.equals(next.getWifiConfiguration().getSsid(), wifiConfiguration.getSsid()) && next.getWifiConfiguration().getKeyManagement() == wifiConfiguration.getKeyManagement()) {
                    bool2 = Boolean.TRUE;
                    bool = Boolean.valueOf(!next.getWifiConfiguration().equals(wifiConfiguration));
                    break;
                }
            }
            return ReportingDataTypesBuilder.buildCredentialLockerUsageInfo(Boolean.valueOf(z), bool, bool2);
        }
        return ReportingDataTypesBuilder.buildCredentialLockerUsageInfo(Boolean.valueOf(z), bool2, bool2);
    }

    private String getDeviceId(WJResult wJResult) {
        return wJResult.getWJProvisionee().getPeripheralDeviceDetails().getDeviceIdentity();
    }

    private String getKeyExchangeMethod(DeviceConnectionConfiguration deviceConnectionConfiguration) {
        return DSSTypesHelper.getKeyExchangeMethodFromTrustState(deviceConnectionConfiguration.getTrustState());
    }

    private ProvisionableInfo getProvisionableInfo(WJWorkflowStateStore wJWorkflowStateStore, WJProvisionee wJProvisionee) {
        WhisperJoinPeripheralDeviceDetails peripheralDeviceDetails = wJProvisionee.getPeripheralDeviceDetails();
        DeviceDetails deviceDetails = wJWorkflowStateStore.getSession(wJProvisionee).getProvisioningDetails().getDeviceDetails();
        if (deviceDetails != null) {
            return ReportingDataTypesBuilder.buildProvisionableInfo(peripheralDeviceDetails, deviceDetails);
        }
        return null;
    }

    private String getTrustMethod(DeviceConnectionConfiguration deviceConnectionConfiguration) {
        return DSSTypesHelper.getTrustMethodFromTrustState(deviceConnectionConfiguration.getTrustState());
    }

    private void logIgnoreReportingEvent(WJResult wJResult) {
        WJLog.v(TAG, String.format(Locale.ENGLISH, "Ignore reporting event: %s %s", wJResult.getClass().getSimpleName(), wJResult.getState().toString()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleWorkflowIdle  reason: collision with other method in class */
    public Void mo5715handleWorkflowIdle(WJWorkflowStateStore wJWorkflowStateStore, WJResult.WorkflowIdle workflowIdle) {
        return null;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public void accept(@NonNull WJWorkflowStateStore wJWorkflowStateStore) {
        mo5716accumulate(wJWorkflowStateStore.getLastWJResult(), wJWorkflowStateStore);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleConnectionEvent  reason: collision with other method in class */
    public Void mo5709handleConnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Connection connection) {
        DeviceConnectionConfiguration deviceConnectionConfiguration = connection.getData().getDeviceConnectionConfiguration();
        ConnectionOperationStatusUpdate.State state = connection.getData().getState();
        int ordinal = connection.getState().ordinal();
        if (ordinal != 2) {
            if (ordinal == 3) {
                this.mProvisionerEventReporter.reportSecureChannelEstablishedSuccess(getDeviceId(connection), this.mProvisioningMethod.toString(), getTrustMethod(deviceConnectionConfiguration), getKeyExchangeMethod(deviceConnectionConfiguration), Radio.BLUETOOTH_LOW_ENERGY);
            } else if (ordinal != 4) {
                logIgnoreReportingEvent(connection);
            } else {
                int ordinal2 = state.ordinal();
                if (ordinal2 == 2) {
                    this.mProvisionerEventReporter.reportConnectedFailure(getDeviceId(connection), this.mProvisioningMethod.toString(), getTrustMethod(deviceConnectionConfiguration), getKeyExchangeMethod(deviceConnectionConfiguration), Radio.BLUETOOTH_LOW_ENERGY, createErrorInfo(connection.getError(), WorkflowStep.CONNECTING_TO_DEVICE.name()));
                } else if (ordinal2 != 4) {
                    this.mProvisionerEventReporter.reportConnectedFailure(getDeviceId(connection), this.mProvisioningMethod.toString(), getTrustMethod(deviceConnectionConfiguration), getKeyExchangeMethod(deviceConnectionConfiguration), Radio.BLUETOOTH_LOW_ENERGY, createErrorInfo(connection.getError(), WorkflowStep.CONNECTING_TO_DEVICE.name()));
                } else {
                    this.mProvisionerEventReporter.reportSecureChannelEstablishedFailure(getDeviceId(connection), this.mProvisioningMethod.toString(), getTrustMethod(deviceConnectionConfiguration), getKeyExchangeMethod(deviceConnectionConfiguration), Radio.BLUETOOTH_LOW_ENERGY, createErrorInfo(connection.getError(), WorkflowStep.CONNECTING_TO_DEVICE.name()));
                }
            }
        } else if (!state.equals(ConnectionOperationStatusUpdate.State.CONNECTED_TO_DEVICE)) {
            return null;
        } else {
            this.mProvisionerEventReporter.reportConnectedSuccess(getDeviceId(connection), this.mProvisioningMethod.toString(), getTrustMethod(deviceConnectionConfiguration), getKeyExchangeMethod(deviceConnectionConfiguration), Radio.BLUETOOTH_LOW_ENERGY);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDisconnectionEvent  reason: collision with other method in class */
    public Void mo5710handleDisconnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Disconnection disconnection) {
        if (!disconnection.isState(Event.State.ERROR)) {
            this.mProvisionerEventReporter.removeReportingSession(getDeviceId(disconnection));
            return null;
        } else if (!(disconnection.getError() instanceof ConnectionFailureDuringSetup)) {
            WJLog.e(TAG, "Expected Disconnection Error to be ConnectionFailureDuringSetup Error");
            return null;
        } else {
            ConnectionFailureDuringSetup connectionFailureDuringSetup = (ConnectionFailureDuringSetup) disconnection.getError();
            DeviceSession deviceSession = connectionFailureDuringSetup.getDeviceSession();
            WJProvisionee wJProvisionee = disconnection.getWJProvisionee();
            int ordinal = deviceSession.getState().ordinal();
            if (ordinal != 1 && ordinal != 2) {
                if (ordinal != 4 && ordinal != 5) {
                    switch (ordinal) {
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            this.mProvisionerEventReporter.reportProvisionedFailure(getDeviceId(disconnection), this.mProvisioningMethod.toString(), createErrorInfo(disconnection.getError(), WorkflowStep.VERIFYING_PROVISIONING.name()), ReportingDataTypesBuilder.buildProvisionableInfo(wJProvisionee.getPeripheralDeviceDetails(), deviceSession.getProvisioningDetails().getDeviceDetails()));
                            break;
                        default:
                            String str = TAG;
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to report Disconnection Event due to unrecognized state: ");
                            outline107.append(deviceSession.getState());
                            WJLog.w(str, outline107.toString());
                            break;
                    }
                } else {
                    this.mProvisionerEventReporter.reportRetrievedProvisioningDetailsFailure(getDeviceId(disconnection), this.mProvisioningMethod.toString(), null, createErrorInfo(connectionFailureDuringSetup, WorkflowStep.GETTING_PROVISIONING_DETAILS.name()));
                }
            } else {
                this.mProvisionerEventReporter.reportConnectedFailure(getDeviceId(disconnection), this.mProvisioningMethod.toString(), getTrustMethod(deviceSession.getDeviceConnectionConfiguration()), getKeyExchangeMethod(deviceSession.getDeviceConnectionConfiguration()), Radio.BLUETOOTH_LOW_ENERGY, createErrorInfo(connectionFailureDuringSetup, WorkflowStep.CONNECTING_TO_DEVICE.name()));
            }
            this.mProvisionerEventReporter.removeReportingSession(getDeviceId(disconnection));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDiscoveryEvent  reason: collision with other method in class */
    public Void mo5711handleDiscoveryEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Discovery discovery) {
        if (!discovery.isState(Event.State.SUCCESS)) {
            logIgnoreReportingEvent(discovery);
            return null;
        }
        for (DiscoveredProvisionable discoveredProvisionable : discovery.getData()) {
            String deviceIdentity = discoveredProvisionable.getWJProvisionee().getPeripheralDeviceDetails().getDeviceIdentity();
            String str = TAG;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Registering a new reporting URL for device with ID ", deviceIdentity, " URL: ");
            outline115.append(discoveredProvisionable.getProvisionableReportingUrl());
            WJLog.d(str, outline115.toString());
            this.mProvisionerEventReporter.registerNewReportingSession(deviceIdentity, discoveredProvisionable.getProvisionerReportingUrl());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleGetProvisioningDetails  reason: collision with other method in class */
    public Void mo5712handleGetProvisioningDetails(WJWorkflowStateStore wJWorkflowStateStore, WJResult.GetProvisioningDetails getProvisioningDetails) {
        if (getProvisioningDetails.isState(Event.State.IN_PROGRESS)) {
            logIgnoreReportingEvent(getProvisioningDetails);
            return null;
        }
        ProvisionableInfo provisionableInfo = getProvisionableInfo(wJWorkflowStateStore, getProvisioningDetails.getWJProvisionee());
        int ordinal = getProvisioningDetails.getState().ordinal();
        if (ordinal == 3) {
            this.mProvisionerEventReporter.reportRetrievedProvisioningDetailsSuccess(getDeviceId(getProvisioningDetails), this.mProvisioningMethod.toString(), provisionableInfo, null, null);
        } else if (ordinal != 4) {
            logIgnoreReportingEvent(getProvisioningDetails);
        } else {
            this.mProvisionerEventReporter.reportRetrievedProvisioningDetailsFailure(getDeviceId(getProvisioningDetails), this.mProvisioningMethod.toString(), provisionableInfo, createErrorInfo(getProvisioningDetails.getError(), WorkflowStep.GETTING_PROVISIONING_DETAILS.name()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleProvisionDeviceEvent  reason: collision with other method in class */
    public Void mo5713handleProvisionDeviceEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.ProvisionDevice provisionDevice) {
        ProvisionableInfo provisionableInfo = getProvisionableInfo(wJWorkflowStateStore, provisionDevice.getWJProvisionee());
        int ordinal = provisionDevice.getState().ordinal();
        if (ordinal != 3) {
            if (ordinal != 4) {
                logIgnoreReportingEvent(provisionDevice);
                return null;
            }
            this.mProvisionerEventReporter.reportProvisionedFailure(getDeviceId(provisionDevice), this.mProvisioningMethod.toString(), createErrorInfo(provisionDevice.getError(), WorkflowStep.PROVISIONING_DEVICE.name()), provisionableInfo);
            return null;
        } else if (provisionDevice.getData() == null) {
            return null;
        } else {
            this.mProvisionerEventReporter.reportProvisionedSuccess(getDeviceId(provisionDevice), this.mProvisioningMethod.toString(), ReportingDataTypesBuilder.buildWifiNetworkInfo(provisionDevice.getData().getChosenWifiConfiguration()), provisionableInfo, getCredentialLockerUsageInfo(provisionDevice.getData().getChosenWifiConfiguration(), provisionDevice.getData().getAvailableWifiNetworks(), provisionDevice.getData().saveWifiNetworkToLocker()));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleVerifyProvisioningEvent  reason: collision with other method in class */
    public Void mo5714handleVerifyProvisioningEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.VerifyProvisioning verifyProvisioning) {
        if (!verifyProvisioning.isState(Event.State.ERROR)) {
            logIgnoreReportingEvent(verifyProvisioning);
            return null;
        }
        ProvisionableInfo provisionableInfo = getProvisionableInfo(wJWorkflowStateStore, verifyProvisioning.getWJProvisionee());
        Throwable error = verifyProvisioning.getError();
        if (error instanceof ProvisionableWifiNetworkConnectionError) {
            this.mProvisionerEventReporter.reportNetworkedFailure(getDeviceId(verifyProvisioning), this.mProvisioningMethod.toString(), ReportingDataTypesBuilder.buildWifiNetworkInfo(((ProvisionableWifiNetworkConnectionError) error).getWifiConnectionDetails()), createErrorInfo(error, WorkflowStep.VERIFYING_PROVISIONING.name()), provisionableInfo);
        } else if (error instanceof ProvisionableRegistrationError) {
            this.mProvisionerEventReporter.reportRegisteredFailure(getDeviceId(verifyProvisioning), this.mProvisioningMethod.toString(), ((ProvisionableRegistrationError) error).getState().name(), createErrorInfo(error, WorkflowStep.VERIFYING_PROVISIONING.name()), provisionableInfo);
        } else {
            this.mProvisionerEventReporter.reportProvisionedFailure(getDeviceId(verifyProvisioning), this.mProvisioningMethod.toString(), createErrorInfo(error, WorkflowStep.VERIFYING_PROVISIONING.name()), provisionableInfo);
        }
        return null;
    }
}
