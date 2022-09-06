package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.devices.AbstractPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ThrottleSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.error.HighRateOfDssRequestFailures;
import com.amazon.whisperjoin.deviceprovisioningservice.error.HighRateOfWorkflowFailures;
import com.amazon.whisperjoin.deviceprovisioningservice.error.UnfavorableWorkflowBehavior;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryEvent;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.util.RetryAfterExponentialBackOffIfKnownError;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ControllerUpdate;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.OveractiveBleActivityDetector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
/* loaded from: classes13.dex */
public class ZeroTouchWorkflowController {
    private static final String TAG = "ZeroTouchWorkflowController";
    private final Scheduler mBackgroundScheduler = Schedulers.single();
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final DeferredDiscoveryHandler mDeferredDiscoveryHandler;
    private final DeviceDiscoveryStream mDeviceDiscoveryStream;
    private final DiscoverySettings mDiscoverySettings;
    private final FFSProvisioningServiceMetricsRecorder mFFSProvisioningServiceMetricsRecorder;
    private boolean mIsDiscovering;
    private final ThrottleSettings mThrottleSettings;
    private final ZeroTouchWorkflowRouter mWorkflowRouter;
    private final ZeroTouchWorkflowCoexHandler mZeroTouchWorkflowCoexHandler;
    private final ZeroTouchWorkflowFailureUpdateHandler mZeroTouchWorkflowFailureUpdateHandler;
    private final ZeroTouchWorkflowMetricsReporter mZeroTouchWorkflowMetricsReporter;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController$9  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State = new int[Event.State.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State;

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
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State = new int[ZeroTouchWorkflowUpdate.State.values().length];
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State[ZeroTouchWorkflowUpdate.State.PREPARING.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State[ZeroTouchWorkflowUpdate.State.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$ZeroTouchWorkflowUpdate$State[ZeroTouchWorkflowUpdate.State.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public ZeroTouchWorkflowController(Context context, ZeroTouchWorkflowRouter zeroTouchWorkflowRouter, DeviceDiscoveryStream deviceDiscoveryStream, ZeroTouchWorkflowMetricsReporter zeroTouchWorkflowMetricsReporter, FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder, DiscoverySettings discoverySettings, ZeroTouchWorkflowFailureUpdateHandler zeroTouchWorkflowFailureUpdateHandler, DeferredDiscoveryHandler deferredDiscoveryHandler, ThrottleSettings throttleSettings) {
        this.mWorkflowRouter = zeroTouchWorkflowRouter;
        this.mDeviceDiscoveryStream = deviceDiscoveryStream;
        this.mZeroTouchWorkflowMetricsReporter = zeroTouchWorkflowMetricsReporter;
        this.mFFSProvisioningServiceMetricsRecorder = fFSProvisioningServiceMetricsRecorder;
        this.mDiscoverySettings = discoverySettings;
        this.mZeroTouchWorkflowFailureUpdateHandler = zeroTouchWorkflowFailureUpdateHandler;
        this.mDeferredDiscoveryHandler = deferredDiscoveryHandler;
        this.mThrottleSettings = throttleSettings;
        this.mZeroTouchWorkflowCoexHandler = new ZeroTouchWorkflowCoexHandler(context);
    }

    private Action cleanup() {
        return new Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.8
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                WJLog.i(ZeroTouchWorkflowController.TAG, "Cleanup");
                ZeroTouchWorkflowController.this.stopDiscovery();
            }
        };
    }

    private Function<Throwable, ObservableSource<? extends ControllerUpdate>> flagUnfavorableWorkflowBehaviorErrorsToTriggerBackOff() {
        return new Function<Throwable, ObservableSource<? extends ControllerUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.6
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends ControllerUpdate> mo10358apply(Throwable th) throws Exception {
                if ((th instanceof HighRateOfDssRequestFailures) || (th instanceof OveractiveBleActivityDetector.OveractivityDetected) || (th instanceof HighRateOfWorkflowFailures)) {
                    WJLog.i(ZeroTouchWorkflowController.TAG, "Known error encountered, triggering retry with backoff");
                    ZeroTouchWorkflowController.this.mFFSProvisioningServiceMetricsRecorder.onWorkflowBackoff(th.getClass().getSimpleName());
                    Observable error = Observable.error(new UnfavorableWorkflowBehavior());
                    ControllerUpdate.Event event = ControllerUpdate.Event.BACKING_OFF;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Workflow is backing off for a period of time due to failures: ");
                    outline107.append(th.getClass().getSimpleName());
                    return error.startWithItem(new ControllerUpdate(event, outline107.toString()));
                }
                WJLog.e(ZeroTouchWorkflowController.TAG, "Expected error occurred in controller", th);
                Observable error2 = Observable.error(th);
                ControllerUpdate.Event event2 = ControllerUpdate.Event.TERMINATED;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Terminating due to unhandled failure ");
                outline1072.append(th.getClass().getSimpleName());
                return error2.startWithItem(new ControllerUpdate(event2, outline1072.toString(), th));
            }
        };
    }

    private Observable<ControllerUpdate> getDiscoveryControllerEvents() {
        return this.mDeviceDiscoveryStream.getEventStream().flatMap(new Function<DeviceDiscoveryEvent, ObservableSource<ControllerUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.5
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<ControllerUpdate> mo10358apply(DeviceDiscoveryEvent deviceDiscoveryEvent) throws Exception {
                int ordinal = deviceDiscoveryEvent.getState().ordinal();
                if (ordinal != 0) {
                    if (ordinal == 1) {
                        return Observable.just(new ControllerUpdate(ControllerUpdate.Event.DISCOVERY_STARTED, "Discovery Started"));
                    }
                    if (ordinal != 4) {
                        return Observable.empty();
                    }
                    return Observable.error(deviceDiscoveryEvent.getError());
                }
                return Observable.just(new ControllerUpdate(ControllerUpdate.Event.DISCOVERY_STOPPED, "Discovery Stopped"));
            }
        });
    }

    private Observable<ControllerUpdate> getWorkflowControllerEvents() {
        return this.mDeviceDiscoveryStream.getEventStream().filter(new Predicate<DeviceDiscoveryEvent>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public boolean test(DeviceDiscoveryEvent deviceDiscoveryEvent) throws Exception {
                if (deviceDiscoveryEvent.getState().equals(Event.State.SUCCESS)) {
                    return true;
                }
                return ZeroTouchWorkflowController.this.mDeferredDiscoveryHandler.canProceed(deviceDiscoveryEvent);
            }
        }).map(new Function<DeviceDiscoveryEvent, AbstractPeripheralDeviceDetails>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public AbstractPeripheralDeviceDetails mo10358apply(DeviceDiscoveryEvent deviceDiscoveryEvent) throws Exception {
                return deviceDiscoveryEvent.getData();
            }
        }).compose(this.mWorkflowRouter).doOnNext(updateDeviceDiscoveryState()).doOnNext(this.mZeroTouchWorkflowCoexHandler).doOnNext(this.mZeroTouchWorkflowMetricsReporter).compose(this.mZeroTouchWorkflowFailureUpdateHandler).flatMap(new Function<ZeroTouchWorkflowUpdate, ObservableSource<ControllerUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<ControllerUpdate> mo10358apply(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
                ControllerUpdate controllerUpdate = new ControllerUpdate(ControllerUpdate.Event.WORKFLOW_UPDATE, zeroTouchWorkflowUpdate.getFriendlyMessage());
                String str = zeroTouchWorkflowUpdate.getWorkflowType().toString();
                int ordinal = zeroTouchWorkflowUpdate.getState().ordinal();
                if (ordinal == 0) {
                    String str2 = ZeroTouchWorkflowController.TAG;
                    WJLog.i(str2, "Workflow Started: " + str);
                    return Observable.just(new ControllerUpdate(ControllerUpdate.Event.WORKFLOW_STARTED), controllerUpdate);
                } else if (ordinal == 3) {
                    String str3 = ZeroTouchWorkflowController.TAG;
                    WJLog.i(str3, "Workflow Success: " + str);
                    return Observable.just(controllerUpdate, new ControllerUpdate(ControllerUpdate.Event.WORKFLOW_SUCCESS));
                } else if (ordinal == 4) {
                    String str4 = ZeroTouchWorkflowController.TAG;
                    WJLog.i(str4, "Workflow Failure: " + str);
                    return Observable.just(controllerUpdate, new ControllerUpdate(ControllerUpdate.Event.WORKFLOW_FAILURE));
                } else {
                    return Observable.just(controllerUpdate);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseDiscovery() {
        if (!this.mIsDiscovering) {
            WJLog.d(TAG, "Not Discovering");
            return;
        }
        WJLog.i(TAG, "Pausing Discovery");
        this.mDeviceDiscoveryStream.pause();
        this.mIsDiscovering = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeDiscovery() {
        if (this.mIsDiscovering) {
            WJLog.d(TAG, "Already discovering");
            return;
        }
        WJLog.i(TAG, "Resuming Discovery");
        this.mDeviceDiscoveryStream.resume();
        this.mIsDiscovering = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDiscovery() {
        if (this.mIsDiscovering) {
            WJLog.d(TAG, "Already discovering");
            return;
        }
        WJLog.i(TAG, "Starting Discovery");
        this.mDeviceDiscoveryStream.start(this.mDiscoverySettings);
        this.mIsDiscovering = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopDiscovery() {
        if (!this.mIsDiscovering) {
            WJLog.d(TAG, "Not Discovering");
            return;
        }
        WJLog.i(TAG, "Stopping Discovery");
        this.mDeviceDiscoveryStream.stop();
        this.mIsDiscovering = false;
    }

    private Consumer<ZeroTouchWorkflowUpdate> updateDeviceDiscoveryState() {
        return new Consumer<ZeroTouchWorkflowUpdate>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate) throws Exception {
                ZeroTouchWorkflowUpdate.State state = zeroTouchWorkflowUpdate.getState();
                ZeroTouchWorkflowUpdate.Radio radio = zeroTouchWorkflowUpdate.getRadio();
                if (ZeroTouchWorkflowUpdate.State.CONNECTING.equals(state) && ZeroTouchWorkflowUpdate.Radio.BLE.equals(radio)) {
                    WJLog.d(ZeroTouchWorkflowController.TAG, "Pausing discovery due to BLE Workflow attempting to connect");
                    ZeroTouchWorkflowController.this.pauseDiscovery();
                    return;
                }
                if (!(ZeroTouchWorkflowUpdate.State.SUCCESS.equals(state) || ZeroTouchWorkflowUpdate.State.FAILURE.equals(state)) || !ZeroTouchWorkflowUpdate.Radio.BLE.equals(radio)) {
                    return;
                }
                WJLog.d(ZeroTouchWorkflowController.TAG, "Resume discovery due to BLE Workflow completing");
                ZeroTouchWorkflowController.this.resumeDiscovery();
            }
        };
    }

    public Observable<ControllerUpdate> start() {
        return Observable.merge(getDiscoveryControllerEvents(), getWorkflowControllerEvents()).doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                WJLog.i(ZeroTouchWorkflowController.TAG, "Controller subscribed to. Starting...");
                ZeroTouchWorkflowController.this.startDiscovery();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).doFinally(cleanup()).onErrorResumeNext(flagUnfavorableWorkflowBehaviorErrorsToTriggerBackOff()).retryWhen(new RetryAfterExponentialBackOffIfKnownError(UnfavorableWorkflowBehavior.class, this.mThrottleSettings.getRetryBackoffAttemptCount(), Iso8601TimeConverter.convertDurationToMs(this.mThrottleSettings.getRetryBackoffBaseDuration()) / 1000));
    }
}
