package com.amazon.whisperjoin.deviceprovisioningservice.workflow;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ConnectionFailureDuringSetup;
import com.amazon.whisperjoin.deviceprovisioningservice.error.HighRateOfDssRequestFailures;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.WorkflowPresentationUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.WorkflowUpdateProducer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetProvisioningDetailsOptions;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.RecordDevicePossessionIntentInnerBarcodeRequest;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.OveractiveBleActivityDetector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
/* loaded from: classes13.dex */
public class ControlledSetupWorkflow extends AbstractDeviceEventAccumulator<WorkflowPresentationUpdate> {
    protected static final WorkflowPresentationUpdate NO_UPDATE = null;
    private static final String TAG = "ControlledSetupWorkflow";
    protected final DeviceActionCreator mActionCreator;
    private Disposable mDisposable;
    private final DSSClient mDssClient;
    private Observable<WorkflowPresentationUpdate> mPresentationStream;
    private final TrustProvider.TrustState mTrustState;
    private final WorkflowConfiguration mWorkflowConfiguration;
    protected final WorkflowUpdateProducer mWorkflowProducer;
    private final WorkflowStateStream mWorkflowStateStream;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow$4  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass4 {
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
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.UPDATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public ControlledSetupWorkflow(WorkflowStateStream workflowStateStream, DeviceActionCreator deviceActionCreator, WorkflowUpdateProducer workflowUpdateProducer, WorkflowConfiguration workflowConfiguration, DSSClient dSSClient, TrustProvider.TrustState trustState) {
        this.mWorkflowStateStream = workflowStateStream;
        this.mActionCreator = deviceActionCreator;
        this.mWorkflowProducer = workflowUpdateProducer;
        this.mWorkflowConfiguration = workflowConfiguration;
        this.mDssClient = dSSClient;
        this.mTrustState = trustState;
    }

    private WorkflowStep getLastKnownWorkflowStep(WJResult.Disconnection disconnection) {
        if (!(disconnection.getError() instanceof ConnectionFailureDuringSetup)) {
            return WorkflowStep.SETUP_FAILURE;
        }
        int ordinal = ((ConnectionFailureDuringSetup) disconnection.getError()).getDeviceSession().getState().ordinal();
        if (ordinal == 1 || ordinal == 2) {
            return WorkflowStep.CONNECTING_TO_DEVICE;
        }
        if (ordinal == 4) {
            return WorkflowStep.GETTING_PROVISIONING_DETAILS;
        }
        if (ordinal != 5) {
            switch (ordinal) {
                case 8:
                case 9:
                    return WorkflowStep.PROVISIONING_DEVICE;
                case 10:
                case 11:
                    return WorkflowStep.VERIFYING_PROVISIONING;
                default:
                    return WorkflowStep.SETUP_FAILURE;
            }
        }
        return WorkflowStep.AWAITING_PROVISIONING_DATA;
    }

    private void recordBarcodePossesionIntentIfExists() {
        if (this.mWorkflowConfiguration.getBarcodeString() != null) {
            WJLog.d(TAG, "Calling RecordDevicePossessionIntent for 2D Barcode");
            this.mDssClient.recordDevicePossessionIntentInnerBarcode(new RecordDevicePossessionIntentInnerBarcodeRequest(this.mWorkflowConfiguration.getBarcodeString())).subscribe(new CompletableObserver() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow.3
                @Override // io.reactivex.rxjava3.core.CompletableObserver
                public void onComplete() {
                    WJLog.d(ControlledSetupWorkflow.TAG, "DevicePossessionIntent for 2D Barcode completed.");
                }

                @Override // io.reactivex.rxjava3.core.CompletableObserver
                public void onError(Throwable th) {
                    WJLog.e(ControlledSetupWorkflow.TAG, "Error in recording DevicePossessionIntent for 2D barcode.", th);
                }

                @Override // io.reactivex.rxjava3.core.CompletableObserver
                public void onSubscribe(Disposable disposable) {
                }
            });
        }
    }

    public Observable<WorkflowPresentationUpdate> getPresentationStream() {
        return this.mPresentationStream;
    }

    protected WorkflowPresentationUpdate onUnhandledEvent(WJResult wJResult) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unhandled Event ");
        outline107.append(wJResult.getClass().toString());
        outline107.append(" State: ");
        outline107.append(wJResult.getState());
        throw new IllegalStateException(outline107.toString());
    }

    public void start() {
        WJLog.i(TAG, "Starting Workflow");
        recordBarcodePossesionIntentIfExists();
        this.mPresentationStream = this.mWorkflowStateStream.getStream().flatMap(new Function<WJWorkflowStateStore, ObservableSource<WorkflowPresentationUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WorkflowPresentationUpdate> mo10358apply(@NonNull WJWorkflowStateStore wJWorkflowStateStore) {
                WorkflowPresentationUpdate mo5716accumulate = ControlledSetupWorkflow.this.mo5716accumulate(wJWorkflowStateStore.getLastWJResult(), wJWorkflowStateStore);
                return mo5716accumulate != null ? Observable.just(mo5716accumulate) : Observable.never();
            }
        }).replay(1).autoConnect(1, new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Disposable disposable) {
                WJLog.d(ControlledSetupWorkflow.TAG, DriveModeMetrics.ConnectionStatus.CONNECTED);
                ControlledSetupWorkflow.this.mDisposable = disposable;
            }
        });
    }

    public void stop() {
        WJLog.i(TAG, "Stopping Workflow");
        if (this.mDisposable != null) {
            WJLog.d(TAG, "Disposing of Presentation Stream");
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleConnectionEvent */
    public WorkflowPresentationUpdate mo5709handleConnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Connection connection) {
        int ordinal = connection.getState().ordinal();
        if (ordinal == 1) {
            this.mActionCreator.stopDiscovery();
            return this.mWorkflowProducer.inProgress(wJWorkflowStateStore, WorkflowStep.CONNECTING_TO_DEVICE);
        } else if (ordinal == 2) {
            return NO_UPDATE;
        } else {
            if (ordinal != 3) {
                if (ordinal != 4) {
                    return onUnhandledEvent(connection);
                }
                return this.mWorkflowProducer.setupFailure(wJWorkflowStateStore, connection.getError(), WorkflowStep.CONNECTING_TO_DEVICE);
            }
            DeviceSession session = wJWorkflowStateStore.getSession(connection.getWJProvisionee());
            this.mActionCreator.getProvisioningDetails(connection.getWJProvisionee(), GetProvisioningDetailsOptions.getAllDetails(session.getDiscoverySessionToken(), session.getDeviceDetails()));
            return NO_UPDATE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDisconnectionEvent */
    public WorkflowPresentationUpdate mo5710handleDisconnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Disconnection disconnection) {
        int ordinal = disconnection.getState().ordinal();
        if (ordinal != 3) {
            if (ordinal != 4) {
                return onUnhandledEvent(disconnection);
            }
            return this.mWorkflowProducer.setupFailure(wJWorkflowStateStore, disconnection.getError(), getLastKnownWorkflowStep(disconnection));
        }
        return NO_UPDATE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDiscoveryEvent */
    public WorkflowPresentationUpdate mo5711handleDiscoveryEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Discovery discovery) {
        int ordinal = discovery.getState().ordinal();
        if (ordinal == 0) {
            WJLog.d(TAG, "Ignoring idle event");
            return NO_UPDATE;
        } else if (ordinal == 1) {
            return this.mWorkflowProducer.inProgress(wJWorkflowStateStore, WorkflowStep.DISCOVERING);
        } else {
            if (ordinal == 3) {
                List<DiscoveredProvisionable> data = discovery.getData();
                if (this.mWorkflowConfiguration.hasBarcodeData()) {
                    WJLog.d(TAG, "Barcode setup. Connecting to device.");
                    this.mActionCreator.connectToDevice(data.get(0).getWJProvisionee(), DeviceConnectionConfiguration.builder().withTrustState(this.mTrustState).withPin(this.mWorkflowConfiguration.getPin()).withPublicKey(this.mWorkflowConfiguration.getPublicKey()).build());
                    return NO_UPDATE;
                }
                return this.mWorkflowProducer.awaitingDeviceSelection(wJWorkflowStateStore, this.mTrustState);
            } else if (ordinal != 4) {
                return onUnhandledEvent(discovery);
            } else {
                Throwable error = discovery.getError();
                if (!(error instanceof OveractiveBleActivityDetector.OveractivityDetected) && !(error instanceof HighRateOfDssRequestFailures)) {
                    this.mActionCreator.stopDiscovery();
                    return this.mWorkflowProducer.setupFailure(wJWorkflowStateStore, discovery.getError(), WorkflowStep.SETUP_FAILURE);
                }
                WJLog.d(TAG, "Ignoring OveractivityDetected & HighRateOfDssRequestFailures events");
                return NO_UPDATE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleGetProvisioningDetails */
    public WorkflowPresentationUpdate mo5712handleGetProvisioningDetails(WJWorkflowStateStore wJWorkflowStateStore, WJResult.GetProvisioningDetails getProvisioningDetails) {
        int ordinal = getProvisioningDetails.getState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 3) {
                return this.mWorkflowProducer.awaitingProvisioningData(wJWorkflowStateStore, getProvisioningDetails.getWJProvisionee());
            }
            if (ordinal != 4) {
                return onUnhandledEvent(getProvisioningDetails);
            }
            this.mActionCreator.disconnect(getProvisioningDetails.getWJProvisionee());
            return this.mWorkflowProducer.setupFailure(wJWorkflowStateStore, getProvisioningDetails.getError(), WorkflowStep.GETTING_PROVISIONING_DETAILS);
        }
        return this.mWorkflowProducer.inProgress(wJWorkflowStateStore, WorkflowStep.GETTING_PROVISIONING_DETAILS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleProvisionDeviceEvent */
    public WorkflowPresentationUpdate mo5713handleProvisionDeviceEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.ProvisionDevice provisionDevice) {
        int ordinal = provisionDevice.getState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 3) {
                this.mActionCreator.verifyProvisioning(provisionDevice.getWJProvisionee(), provisionDevice.getData());
                return NO_UPDATE;
            } else if (ordinal != 4) {
                return onUnhandledEvent(provisionDevice);
            } else {
                this.mActionCreator.disconnect(provisionDevice.getWJProvisionee());
                return this.mWorkflowProducer.setupFailure(wJWorkflowStateStore, provisionDevice.getError(), WorkflowStep.PROVISIONING_DEVICE);
            }
        }
        return this.mWorkflowProducer.inProgress(wJWorkflowStateStore, WorkflowStep.PROVISIONING_DEVICE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleVerifyProvisioningEvent */
    public WorkflowPresentationUpdate mo5714handleVerifyProvisioningEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.VerifyProvisioning verifyProvisioning) {
        int ordinal = verifyProvisioning.getState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 3) {
                this.mActionCreator.disconnect(verifyProvisioning.getWJProvisionee());
                return this.mWorkflowProducer.setupSuccess(wJWorkflowStateStore);
            } else if (ordinal != 4) {
                return onUnhandledEvent(verifyProvisioning);
            } else {
                if (verifyProvisioning.getError() instanceof ProvisionableWifiNetworkConnectionError) {
                    return this.mWorkflowProducer.fixupWifiConnectionError(wJWorkflowStateStore, verifyProvisioning.getWJProvisionee());
                }
                this.mActionCreator.disconnect(verifyProvisioning.getWJProvisionee());
                return this.mWorkflowProducer.setupFailure(wJWorkflowStateStore, verifyProvisioning.getError(), WorkflowStep.VERIFYING_PROVISIONING);
            }
        }
        return this.mWorkflowProducer.inProgress(wJWorkflowStateStore, WorkflowStep.VERIFYING_PROVISIONING);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleWorkflowIdle */
    public WorkflowPresentationUpdate mo5715handleWorkflowIdle(WJWorkflowStateStore wJWorkflowStateStore, WJResult.WorkflowIdle workflowIdle) {
        return this.mWorkflowProducer.idle(wJWorkflowStateStore);
    }
}
