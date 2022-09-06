package com.amazon.whisperjoin.deviceprovisioningservice.workflow;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetProvisioningDetailsOptions;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.RegistrationRequest;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.WorkflowIdProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflow;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
/* loaded from: classes13.dex */
public class WifiSimpleSetupZeroTouchWorkflow extends AbstractDeviceEventAccumulator<Observable<ZeroTouchWorkflowUpdate>> implements ZeroTouchWorkflow<WhisperJoinPeripheralDeviceDetails> {
    private static final String TAG = "WifiSimpleSetupZeroTouchWorkflow";
    protected final DeviceActionCreator mDeviceActionCreator;
    private final TrustProvider.TrustState mTrustState;
    private final String mWorkflowId;
    private final WorkflowStateStream mWorkflowStateStream;
    private Disposable mWorkflowStateStreamDisposable;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow$6  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State = new int[Event.State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.UPDATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class TerminalWorkflowUpdateStateDetected implements Predicate<ZeroTouchWorkflowUpdate> {
        private TerminalWorkflowUpdateStateDetected() {
        }

        @Override // io.reactivex.rxjava3.functions.Predicate
        public boolean test(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
            boolean z = zeroTouchWorkflowUpdate.getState().equals(ZeroTouchWorkflowUpdate.State.SUCCESS) || zeroTouchWorkflowUpdate.getState().equals(ZeroTouchWorkflowUpdate.State.FAILURE);
            if (z) {
                WJLog.d(WifiSimpleSetupZeroTouchWorkflow.TAG, "Terminal State observed, completing workflow");
            }
            return z;
        }
    }

    public WifiSimpleSetupZeroTouchWorkflow(WorkflowStateStream workflowStateStream, DeviceActionCreator deviceActionCreator, TrustProvider.TrustState trustState, WorkflowIdProvider workflowIdProvider) {
        this.mWorkflowStateStream = workflowStateStream;
        this.mDeviceActionCreator = deviceActionCreator;
        this.mTrustState = trustState;
        this.mWorkflowId = workflowIdProvider.createWorkflowId();
    }

    private ZeroTouchWorkflowUpdate createWorkflowUpdate(ZeroTouchWorkflowUpdate.State state) {
        return createWorkflowUpdate(state, null);
    }

    private Observable<ZeroTouchWorkflowUpdate> emitUpdateAndThenPerformNextAction(ZeroTouchWorkflowUpdate.State state, Consumer<ZeroTouchWorkflowUpdate> consumer) {
        return Observable.just(createWorkflowUpdate(state)).doAfterNext(consumer);
    }

    Disposable getWorkflowStateStreamDisposable() {
        return this.mWorkflowStateStreamDisposable;
    }

    protected Observable<ZeroTouchWorkflowUpdate> noUpdate() {
        return Observable.empty();
    }

    protected void onUnhandledEvent(WJResult wJResult) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unhandled Event ");
        outline107.append(wJResult.getClass().toString());
        outline107.append(" State: ");
        outline107.append(wJResult.getState());
        throw new IllegalStateException(outline107.toString());
    }

    protected Observable<ZeroTouchWorkflowUpdate> workflowFailureUpdate(WJResult wJResult) {
        return Observable.just(createWorkflowUpdate(ZeroTouchWorkflowUpdate.State.FAILURE, wJResult.getError()));
    }

    private ZeroTouchWorkflowUpdate createWorkflowUpdate(ZeroTouchWorkflowUpdate.State state, Throwable th) {
        return new ZeroTouchWorkflowUpdate.Builder().setLocalWorkflowIdentifier(this.mWorkflowId).setWorkflowType(ZeroTouchWorkflowUpdate.WorkflowType.WSS).setRadio(ZeroTouchWorkflowUpdate.Radio.BLE).setState(state).setThrowable(th).createZeroTouchWorkflowUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleConnectionEvent */
    public Observable<ZeroTouchWorkflowUpdate> mo5709handleConnectionEvent(final WJWorkflowStateStore wJWorkflowStateStore, final WJResult.Connection connection) {
        int ordinal = connection.getState().ordinal();
        if (ordinal != 1 && ordinal != 2) {
            if (ordinal == 3) {
                return emitUpdateAndThenPerformNextAction(ZeroTouchWorkflowUpdate.State.PROVISIONING, new Consumer<ZeroTouchWorkflowUpdate>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow.5
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public void accept(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
                        WJLog.d(WifiSimpleSetupZeroTouchWorkflow.TAG, "Getting Provisioning Details");
                        DeviceSession session = wJWorkflowStateStore.getSession(connection.getWJProvisionee());
                        WifiSimpleSetupZeroTouchWorkflow.this.mDeviceActionCreator.getProvisioningDetails(connection.getWJProvisionee(), GetProvisioningDetailsOptions.getAllDetails(session.getDiscoverySessionToken(), session.getDeviceDetails()));
                    }
                });
            }
            if (ordinal != 4) {
                onUnhandledEvent(connection);
            } else {
                WJLog.d(TAG, "An error occurred while connecting");
                return workflowFailureUpdate(connection);
            }
        }
        return noUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDisconnectionEvent */
    public Observable<ZeroTouchWorkflowUpdate> mo5710handleDisconnectionEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.Disconnection disconnection) {
        int ordinal = disconnection.getState().ordinal();
        if (ordinal != 3) {
            if (ordinal != 4) {
                onUnhandledEvent(disconnection);
            } else {
                WJLog.d(TAG, "A unexpected disconnection occurred");
                return workflowFailureUpdate(disconnection);
            }
        }
        return noUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleDiscoveryEvent */
    public Observable<ZeroTouchWorkflowUpdate> mo5711handleDiscoveryEvent(WJWorkflowStateStore wJWorkflowStateStore, final WJResult.Discovery discovery) {
        int ordinal = discovery.getState().ordinal();
        if (ordinal != 0 && ordinal != 1) {
            if (ordinal == 3) {
                return emitUpdateAndThenPerformNextAction(ZeroTouchWorkflowUpdate.State.CONNECTING, new Consumer<ZeroTouchWorkflowUpdate>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow.4
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public void accept(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
                        WJLog.d(WifiSimpleSetupZeroTouchWorkflow.TAG, "Connecting to Device");
                        WifiSimpleSetupZeroTouchWorkflow.this.mDeviceActionCreator.connectToDevice(discovery.getData().get(0).getWJProvisionee(), DeviceConnectionConfiguration.builder().withTrustState(WifiSimpleSetupZeroTouchWorkflow.this.mTrustState).build());
                    }
                });
            }
            if (ordinal != 4) {
                onUnhandledEvent(discovery);
            } else {
                return workflowFailureUpdate(discovery);
            }
        }
        return noUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleGetProvisioningDetails */
    public Observable<ZeroTouchWorkflowUpdate> mo5712handleGetProvisioningDetails(WJWorkflowStateStore wJWorkflowStateStore, WJResult.GetProvisioningDetails getProvisioningDetails) {
        int ordinal = getProvisioningDetails.getState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 3) {
                WJProvisionee wJProvisionee = getProvisioningDetails.getWJProvisionee();
                this.mDeviceActionCreator.provisionDevice(wJProvisionee, new ProvisionableConfiguration.Builder().setAvailableNetworks(getProvisioningDetails.getData().getAvailableWifiNetworks()).setDeviceDetails(getProvisioningDetails.getData().getDeviceDetails()).setRegistrationRequest(new RegistrationRequest(RegistrationRequest.Scheme.CODE_BASED_LINKING)).setProvisionableReportUrl(wJWorkflowStateStore.getSession(wJProvisionee).getProvisionableReportUrl()).create());
            } else if (ordinal != 4) {
                onUnhandledEvent(getProvisioningDetails);
            } else {
                this.mDeviceActionCreator.disconnect(getProvisioningDetails.getWJProvisionee());
                return workflowFailureUpdate(getProvisioningDetails);
            }
        }
        return noUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleProvisionDeviceEvent */
    public Observable<ZeroTouchWorkflowUpdate> mo5713handleProvisionDeviceEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.ProvisionDevice provisionDevice) {
        int ordinal = provisionDevice.getState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 3) {
                this.mDeviceActionCreator.verifyProvisioning(provisionDevice.getWJProvisionee(), provisionDevice.getData());
            } else if (ordinal != 4) {
                onUnhandledEvent(provisionDevice);
            } else {
                this.mDeviceActionCreator.disconnect(provisionDevice.getWJProvisionee());
                return workflowFailureUpdate(provisionDevice);
            }
        }
        return noUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleVerifyProvisioningEvent */
    public Observable<ZeroTouchWorkflowUpdate> mo5714handleVerifyProvisioningEvent(WJWorkflowStateStore wJWorkflowStateStore, WJResult.VerifyProvisioning verifyProvisioning) {
        int ordinal = verifyProvisioning.getState().ordinal();
        if (ordinal != 1) {
            if (ordinal == 3) {
                this.mDeviceActionCreator.disconnect(verifyProvisioning.getWJProvisionee());
                return Observable.just(createWorkflowUpdate(ZeroTouchWorkflowUpdate.State.SUCCESS));
            } else if (ordinal != 4) {
                onUnhandledEvent(verifyProvisioning);
            } else {
                this.mDeviceActionCreator.disconnect(verifyProvisioning.getWJProvisionee());
                return workflowFailureUpdate(verifyProvisioning);
            }
        }
        return noUpdate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.AbstractDeviceEventAccumulator
    /* renamed from: handleWorkflowIdle */
    public Observable<ZeroTouchWorkflowUpdate> mo5715handleWorkflowIdle(WJWorkflowStateStore wJWorkflowStateStore, WJResult.WorkflowIdle workflowIdle) {
        return noUpdate();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflow
    public Observable<ZeroTouchWorkflowUpdate> run(final WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        WJLog.i(TAG, "Starting Workflow");
        if (whisperJoinPeripheralDeviceDetails != null) {
            Disposable disposable = this.mWorkflowStateStreamDisposable;
            if (disposable != null && disposable.isDisposed()) {
                throw new IllegalStateException("Already disposed");
            }
            if (this.mWorkflowStateStreamDisposable == null) {
                return this.mWorkflowStateStream.getStream().doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow.3
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public void accept(Disposable disposable2) throws Exception {
                        WifiSimpleSetupZeroTouchWorkflow.this.mDeviceActionCreator.blessDiscoveredDevice(whisperJoinPeripheralDeviceDetails);
                        WifiSimpleSetupZeroTouchWorkflow.this.mWorkflowStateStreamDisposable = disposable2;
                    }
                }).flatMap(new Function<WJWorkflowStateStore, ObservableSource<ZeroTouchWorkflowUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow.2
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public ObservableSource<ZeroTouchWorkflowUpdate> mo10358apply(WJWorkflowStateStore wJWorkflowStateStore) throws Exception {
                        return WifiSimpleSetupZeroTouchWorkflow.this.mo5716accumulate(wJWorkflowStateStore.getLastWJResult(), wJWorkflowStateStore);
                    }
                }).startWithItem(createWorkflowUpdate(ZeroTouchWorkflowUpdate.State.PREPARING)).takeUntil(new TerminalWorkflowUpdateStateDetected()).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.WifiSimpleSetupZeroTouchWorkflow.1
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        if (WifiSimpleSetupZeroTouchWorkflow.this.mWorkflowStateStreamDisposable != null) {
                            WifiSimpleSetupZeroTouchWorkflow.this.mWorkflowStateStreamDisposable.dispose();
                        }
                    }
                });
            }
            WJLog.d(TAG, "Already started");
            throw new IllegalStateException("Already disposed");
        }
        throw new IllegalArgumentException("Device details can not be null");
    }
}
